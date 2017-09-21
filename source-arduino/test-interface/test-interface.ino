#include <SoftwareSerial.h>
#include <SimpleTimer.h>



SoftwareSerial rfidOne(10,11);
SoftwareSerial rfidTwo(12,13);

int lastAvailable = 0;
int retryCounter = 0;
int BAUD = 9600;

SimpleTimer timer;

void setup() {
  // Open serial communications and wait for port to open:
  Serial.begin(9600);
  while (!Serial) {
    ; // wait for serial port to connect. Needed for native USB port only
  }


  // Start each software serial port
  rfidOne.begin(BAUD);
  rfidTwo.begin(BAUD);

}

void loop() {
  // By default, the last intialized port is listening.
  // when you want to listen on a port, explicitly select it:
  rfidOne.begin(BAUD);
  delay(50);
  rfidOne.listen();  
  while (!rfidOne.isListening()) {
  }
  
//    Serial.println("RFID 1 LISTENING");
//delay(5);
  // while there is data coming in, read it
  // and send to the hardware serial port:
  if (rfidOne.available() > 0){
    Serial.println("RFID 1 TRIGGERED");
    lastAvailable = 0;
    retryCounter = 0;
    while (rfidOne.available() < 14) {
      delay(2);
      if (rfidOne.available() == lastAvailable) {
        retryCounter++;
        if (retryCounter > 5) {
          break;
        }
      }
      Serial.print("RFID 1 WAITING ");
      Serial.println(rfidOne.available());
      lastAvailable = rfidOne.available();
    }
    Serial.println(rfidOne.available());
    if (rfidOne.available() >= 14) {
      Serial.println("Available: ");
      Serial.println(rfidOne.available());
      Serial.println("Data from port one:");
      String body = "";
      for (int i = 0; i < 14; i++) {
        body += rfidOne.read();
      }
      Serial.println(body);  
    }
        
  }
  rfidOne.end();
  while (rfidOne.isListening()) {
  }
  rfidTwo.begin(BAUD);
  delay(50);
  rfidTwo.listen();
  while (!rfidTwo.isListening()) {
  }
//  delay();
//    Serial.println("RFID 2 LISTENING");
  if (rfidTwo.available() > 0){
    Serial.println("RFID 2 TRIGGERED");
    lastAvailable = 0;
    retryCounter = 0;
    while (rfidTwo.available() < 14) {
      delay(2);
      if (rfidTwo.available() == lastAvailable) {
        retryCounter++;
        if (retryCounter > 5){
          break;
        }
      }
      Serial.print("RFID 2 WAITING ");
      Serial.println(rfidTwo.available());
      lastAvailable = rfidTwo.available();
    }
    Serial.println(rfidTwo.available());
    if (rfidTwo.available() >= 14) {
      Serial.println("Available: ");
      Serial.println(rfidTwo.available());
      Serial.println("Data from port two:");
      String body = "";
      for (int i = 0; i < 14; i++) {
        body += rfidTwo.read();
      }
      Serial.println(body);  
    }
        
  }
  rfidTwo.end();
  while (rfidTwo.isListening()){
  }
}
 