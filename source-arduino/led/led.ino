String incomingStringBuffer;

void setup() {
  Serial.begin(9600);
  pinMode(8, OUTPUT);
}

void loop() {
  if (Serial.available() > 0) {
    incomingStringBuffer += Serial.readString();
    if (incomingStringBuffer.startsWith("<")
        && incomingStringBuffer.endsWith(">")) {

      handleCommand(incomingStringBuffer);
      incomingStringBuffer = "";
    }
  }
}

void handleCommand(String command) {
  int separator = command.indexOf(",");
  String component = command.substring(1, separator);
  String action = command.substring(separator + 1, command.length() - 1);

  if (component.startsWith("0")) {
    handleBaseLEDCommand(action);
  }
}

void handleBaseLEDCommand(String action) {
  if (action.equals("OFF") > 0) {
    digitalWrite(8, LOW);
  } else if (action.equals("ON") > 0) {
    digitalWrite(8, HIGH);
  }
}
