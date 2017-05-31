#include <legopowerfunctions.h>

String incomingStringBuffer;
LEGOPowerFunctions lego(9);

void setup() {
	Serial.begin(9600);
	pinMode(8, OUTPUT);
}

void loop() {
	receiveCommand();
}

void receiveCommand() {
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

void moveTrain(int inputSpeed, int inputChannel) {
	int color;
	int speed;
	int channel;
	switch (inputSpeed) {
		case -4:
		speed = 0xC;
		break;
		case -3:
		speed = 0xD;
		break;
		case -2:
		speed = 0xE;
		break;
		case -1:
		speed = 0xF;
		break;
		case 0:
		speed = 0x0;
		break;
		case 1:
		speed = 0x1;
		break;
		case 2:
		speed = 0x2;
		break;
		case 3:
		speed = 0x3;
		break;
		case 4:
		speed = 0x4;
		break;
		default:
		speed = 0x0;
		break;
	}
	if (inputChannel <= 4) {
		color = 0x0;
		switch (inputChannel) {
			case 1:
			channel = 0x0;
			break;
			case 2:
			channel = 0x1;
			break;
			case 3:
			channel = 0x2;
			break;
			case 4:
			channel = 0x3;
			break;
		}
	} else {
		color = 0x1;
		switch (inputChannel) {
			case 5:
			channel = 0x0;
			break;
			case 6:
			channel = 0x1;
			break;
			case 7:
			channel = 0x2;
			break;
			case 8:
			channel = 0x3;
			break;
		}
	}
	lego.SingleOutput(0, speed, color, channel);
}