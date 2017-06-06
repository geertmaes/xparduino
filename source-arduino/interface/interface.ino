#include <Servo.h>
#include <legopowerfunctions.h>
#include <SoftwareSerial.h>
#include <SimpleTimer.h>

String incomingStringBuffer;
int rfidValue = 0;

int photoSensor = A0;

int switchOne = A1;
int switchTwo = A2;
int switchThree = A3;
int switchFour = A4;

int ledPinOne = 4;
int ledPinTwo = 5;

int obstacleSensorOne = 2;
int obstacleSensorTwo = 3;
// int obstacleSensorThree = 4;
// int obstacleSensorFour = 5;

int rfidOneTx = 10;
int rfidOneRx = 11;
int rfidTwoTx = 12;
int rfidTwoRx = 13;

LEGOPowerFunctions lego(A5);
SoftwareSerial rfidOne(10,11);
SoftwareSerial rfidTwo(12,13);

Servo switch1;
Servo switch2;
Servo switch3;
Servo switch4;

const byte numChars = 32;
char receivedChars[numChars];
boolean newCommand = false;

SimpleTimer timer;

void setup() {
  Serial.begin(9600);
  pinMode(4, OUTPUT);
  pinMode(5, OUTPUT);
  pinMode(2, INPUT);
  pinMode(3, INPUT);
//  pinMode(4, INPUT);
//  pinMode(5, INPUT);
  rfidOne.begin(9600);
  rfidTwo.begin(9600);
  switch1.attach(switchOne);
  switch2.attach(switchTwo);
  switch3.attach(switchThree);
  switch4.attach(switchFour);
  timer.setInterval(100, checkModules);
}

void loop() {
  receiveCommand();
  executeCommand();
  timer.run();
}

void checkModules() {
  checkObstacleSensor();
  checkRfidReader(rfidOne, rfidOneTx);
  checkRfidReader(rfidTwo, rfidTwoTx);

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
    switch (pin){
      case 19:
        sendInfraRedSignal(lego, pin, action);
        break;
      default:
        break;
    }
}

void sendInfraRedSignal(LEGOPowerFunctions &legoLed, int pin, String action) {
  int seperator = action.indexOf(":");
    int seperator2 = action.lastIndexOf(":");
    int color = action.substring(0, seperator).toInt();
    int channel = action.substring(seperator+1, seperator2).toInt();
    int speed = action.substring(seperator2+1, action.length()).toInt();
    legoLed.SingleOutput(0, speed, color, channel);
    Serial.print(createEvent(2,pin,2,"true"));
}

void handleSwitchCommand(int pin, String action) {
  switch (pin) {
    case 15:
        moveSwitch(switch1, pin, action);
        break;
      case 16:
        moveSwitch(switch2, pin, action);
        break;
      case 17:
        moveSwitch(switch3, pin, action);
        break;
      case 18:
        moveSwitch(switch4, pin, action);
        break;
    default:
      break;
  }
}

void moveSwitch(Servo &theSwitch, int pin, String action) {
  if (action.equals("LEFT")) {
    theSwitch.write(180);
    Serial.print(createEvent(4,pin,4,"LEFT"));
  } else if (action.equals("RIGHT")) {
      theSwitch.write(0);
      Serial.print(createEvent(4,pin,4,"RIGHT"));
  }
}


void checkPhotoSensor() {
    int photoSensorValue = analogRead(photoSensor);
    Serial.print(createEvent(1,photoSensor,1,String(photoSensorValue)));
}

void checkObstacleSensor() {
    int obstacleSensorValue = digitalRead(obstacleSensorOne);
    Serial.print(createEvent(3,obstacleSensorOne,3,String(obstacleSensorValue)));
    obstacleSensorValue = digitalRead(obstacleSensorTwo);
    Serial.print(createEvent(3,obstacleSensorTwo,3,String(obstacleSensorValue)));
    // obstacleSensorValue = digitalRead(obstacleSensorThree);
    // Serial.print(createEvent(3,obstacleSensorThree,3,String(obstacleSensorValue)));
    // obstacleSensorValue = digitalRead(obstacleSensorFour);
    // Serial.print(createEvent(3,obstacleSensorFour,3,String(obstacleSensorValue)));
}

void checkRfidReader(SoftwareSerial &rfidReader, int rfidTx) {
    while (rfidReader.available() >= 14 ) {
        String body = "";
        for(int i = 0; i < 14; i++){
            body += rfidReader.read();
        }
        Serial.println(createEvent(5,rfidTx,5,body));
    }
}