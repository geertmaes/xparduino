#include <SimpleTimer.h>

String incomingStringBuffer;

int LedPin = 8;
int photoSensor = A0;

const byte numChars = 32;
char receivedChars[numChars];
boolean newCommand = false;

SimpleTimer timer;

void setup() {
  Serial.begin(9600);
  pinMode(8, OUTPUT);
  pinMode(9, OUTPUT);
  timer.setInterval(50, checkModules);
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
  int photoSensorValue = analogRead(photoSensor);
  Serial.print(createEvent(1,photoSensor,1,String(photoSensorValue)));
}

void handleCommand(String command) {
  int seperator = command.indexOf(",");
  String component = command.substring(1, seperator);
  String action = command.substring(seperator + 1, command.length() - 1);

  if (component.startsWith("0")) {
    int componentSeperator = component.indexOf(":");
    int type = component.substring(1, seperator).toInt();
    int pin = component.substring(seperator + 1, component.length() - 1).toInt();
    handleBaseLEDCommand(pin, action);
  }
}

void handleBaseLEDCommand(int pin, String action) {
  if (action.equals("OFF") > 0) {
    digitalWrite(pin, LOW);
    Serial.print(createEvent(0,pin,0,"false"));
  } else if (action.equals("ON") > 0) {
    digitalWrite(pin, HIGH);
    Serial.print(createEvent(0,pin,0,"true"));
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
