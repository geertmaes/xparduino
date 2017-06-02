#include <Servo.h>
#include <legopowerfunctions.h>
#include <SimpleTimer.h>


String incomingStringBuffer;

int LedPin = 8;
int photoSensor = A0;
int obstacleSensor = 3;
int switchThree = 4;

LEGOPowerFunctions lego(2);

Servo motor;


const byte numChars = 32;
char receivedChars[numChars];
boolean newCommand = false;

SimpleTimer timer;

void setup() {
  Serial.begin(9600);
  pinMode(8, OUTPUT);
  pinMode(9, OUTPUT);
  pinMode(3, INPUT);
  motor.attach(switchThree);
  timer.setInterval(100, checkModules);
}

void loop() {
  receiveCommand();
  executeCommand();
  timer.run();
}

void receiveCommand() {
    static byte index = 0;
    char endMarker = '>';
    char input;

    if (Serial.available() > 0) {
        input = Serial.read();

        if (input != endMarker) {
            receivedChars[index] = input;
            index++;
            if (index >= numChars) {
                index = numChars - 1;
            }
        }
        else {
            receivedChars[index] = '>';
            receivedChars[index + 1] = '\0'; // terminate the string
            index = 0;
            newCommand = true;
        }
    }
}

void executeCommand() {
    if (newCommand == true) {
        handleCommand(receivedChars);
        newCommand = false;
    }
}

void checkModules() {
  checkPhotoSensor();
  checkObstacleSensor();
}

void handleCommand(String command) {
  int seperator = command.indexOf(",");
  String component = command.substring(1, seperator);
  String action = command.substring(seperator + 1, command.length() - 1);
  int pin = component.substring(seperator + 1, component.length() - 1).toInt();
  if (component.startsWith("0")) {
    handleBaseLEDCommand(pin, action);
  }
  else if (component.startsWith("2")) {
    handleInfraredCommand(pin, action);
  } else if (component.startsWith("4")) {
    handleSwitchCommand(pin, action);
  }
}

void handleBaseLEDCommand(int pin, String action) {
  if (action.equals("OFF")) {
    digitalWrite(pin, LOW);
    Serial.print(createEvent(0,pin,0,"false"));
  } else if (action.equals("ON")) {
    digitalWrite(pin, HIGH);
    Serial.print(createEvent(0,pin,0,"true"));
  }
}
void handleInfraredCommand(int pin, String action){
  int seperator = action.indexOf(":");
  int seperator2 = action.lastIndexOf(":");
  int color = action.substring(0, seperator).toInt();
  int channel = action.substring(seperator+1, seperator2).toInt();
  int speed = action.substring(seperator2+1, action.length()).toInt();
  lego.SingleOutput(0, speed, color, channel);
  Serial.print(createEvent(2,pin,2,"true"));
}

void handleSwitchCommand(int pin, String action) {
  if (action.equals("LEFT")) {
      motor.write(180);
      Serial.print(createEvent(4,pin,4,"LEFT"));
    } else if (action.equals("RIGHT")) {
      motor.write(0);
      Serial.print(createEvent(4,pin,4,"RIGHT"));
    }
}

String createEvent(int componentType, int pin, int eventCode, String body) {
  String event = "<";
  event += componentType;
  event += ":";
  event += pin;
  event += ",";
  event += eventCode; 
  event += ",";
  event += body;
  event += ">";
  return event;
}

void checkPhotoSensor() {
    int photoSensorValue = analogRead(photoSensor);
    Serial.print(createEvent(1,photoSensor,1,String(photoSensorValue)));
}

void checkObstacleSensor() {
    int obstacleSensorValue = digitalRead(obstacleSensor);
    Serial.print(createEvent(3,obstacleSensor,3,String(obstacleSensorValue)));
}






