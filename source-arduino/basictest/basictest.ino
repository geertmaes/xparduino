#include <legopowerfunctions.h>


int LedPin = 8;
const byte numChars = 32;
char receivedChars[numChars];
boolean newData = false;
LEGOPowerFunctions lego(9);

int dataNumber = 0;

void setup()
{
    Serial.begin(9600);
    Serial.println("<com.cegeka.xpdays.arduino.Arduino is ready>");
    pinMode(LedPin, OUTPUT);
}

void loop()
{
    recvWithEndMarker();
    showNewNumber();
}

void recvWithEndMarker() {
    static byte ndx = 0;
    char endMarker = '!';
    char rc;
    
    if (Serial.available() > 0) {
        rc = Serial.read();

        if (rc != endMarker) {
            receivedChars[ndx] = rc;
            ndx++;
            if (ndx >= numChars) {
                ndx = numChars - 1;
            }
        }
        else {
            receivedChars[ndx] = '\0'; // terminate the string
            ndx = 0;
            newData = true;
        }
    }
}

void showNewNumber() {
    if (newData == true) {
        dataNumber = 0;             // new for this version
        dataNumber = atoi(receivedChars);   // new for this version
        Serial.print("This just in ... ");
        Serial.println(receivedChars);
        if (dataNumber == 1) {
	        Serial.print("LED ON");    
	        digitalWrite(LedPin, HIGH);
        }
        else if (dataNumber == 0) {
        	Serial.print("LED OFF");    
	        digitalWrite(LedPin, LOW);	
        }
        else if (dataNumber == 3) {
            Serial.print("train moving");
            moveTrain(4, 1);
        }
        else if (dataNumber == 4) {
            Serial.print("train stopped");
            moveTrain(0, 1);
        }
        newData = false;
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