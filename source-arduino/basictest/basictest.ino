int LedPin = 8;
char data;
void setup()
{
    Serial.begin(9600);
    pinMode(LedPin, OUTPUT);
}

void loop()
{
    data = Serial.read();
    if (Serial.available() > 0)
    {
        if (data == '1')
        {
            digitalWrite(LedPin, HIGH);
        }
        else
        {
            if (data == '0')
            {
                digitalWrite(LedPin, LOW);
            }
        }
    }
    else
    {
        if (Serial.available() < 0)
        {
            digitalWrite(LedPin, HIGH);
            delay(500);
            digitalWrite(LedPin, LOW);
            delay(500);
        }
    }
}
