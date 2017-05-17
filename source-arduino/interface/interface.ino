#include <legopowerfunctions.h>

String REDCOMMAND = "R";
String STATUSCOMMAND = "STAT";
String CHECKCOMMAND = "CHECK";

String command = "";
String commandMask = "jta";
String stat = " status light: ";

int REDLED = 8;

boolean commandIsReceived = false;


void setup() {
	Serial.begin(9600);
	command.reserve(200);
	pinMode(REDLED, OUTPUT);
}

void loop() {
	if (commandIsReceived) {
		Serial.println(checkCommand());
		command = "";
		commandIsReceived = false;
	}
}

boolean checkCommand() {
	String commandPart = command.substring(3);
	if (command.startsWith(commandMask)) {
		if (commandPart == REDCOMMAND) {
			setLight(REDLED);
			return true;
		} else if (commandPart == CHECKCOMMAND) {
			checkStatus();
			return true;
		} 		
		Serial.println("Command not recognised: " + commandPart);
		command = "";
		return false;
	}
	Serial.println("Prefix not recognised in: " + command);
	command = "";
	return false;
}

void setLight(int light) {
	digitalWrite(REDLED, LOW);
	switch (light) {
	    case 8:
	    	digitalWrite(REDLED, HIGH);
	      	break;
	}
}

void checkStatus() {
	Serial.println("debug status information");
	String debugInfo = "Red: ";
	debugInfo += digitalRead(REDLED);
	debugInfo += stat;
	Serial.println(debugInfo);
}

void serialEvent() {
	while(Serial.available()){
		char inChar = (char)Serial.read();
		if (inChar == '\n') {
			commandIsReceived = true;
			return;
		}
		command += inChar;
	}
}