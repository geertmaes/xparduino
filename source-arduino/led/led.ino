#include <legopowerfunctions.h>

String incomingStringBuffer;

int LedPin = 8;

const byte numChars = 32;
char receivedChars[numChars];
boolean newCommand = false;
LEGOPowerFunctions lego(2);

void setup() {
  Serial.begin(9600);
  pinMode(8, OUTPUT);
  pinMode(9, OUTPUT);
}

void loop() {
  receiveCommand();
  executeCommand();
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

void handleCommand(String command) {
  int seperator = command.indexOf(",");
  String component = command.substring(1, seperator);
  String action = command.substring(seperator + 1, command.length() - 1);
  int type = component.substring(1, seperator).toInt();
  int pin = component.substring(seperator + 1, component.length() - 1).toInt();
  if (component.startsWith("0")) {
    handleBaseLEDCommand(pin, action);
  }else if(component.startsWith("2")) {
    handleInfraredCommand(pin, action);
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

void handleInfraredCommand(int pin, String action){
  int seperator = action.indexOf(":");
  int seperator2 = action.lastIndexOf(":");
  int color = action.substring(0, seperator).toInt();
  int channel = action.substring(seperator+1, seperator2).toInt();
  int speed = action.substring(seperator2+1, action.length()-1).toInt();
  Serial.print(color);
  Serial.print(channel);
  Serial.print(speed);
  lego.SingleOutput(0, speed, color, channel);
  Serial.print(createEvent(2,pin,2,"true"));
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
