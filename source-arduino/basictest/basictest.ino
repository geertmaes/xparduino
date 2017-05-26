int LedPin = 8;
const byte numChars = 32;
char receivedChars[numChars];
boolean newData = false;

int dataNumber = 0;

void setup()
{
    Serial.begin(9600);
    Serial.println("<Arduino is ready>");
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
        newData = false;
    }
}