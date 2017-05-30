package com.cegeka.xpdays.arduino.rest.service;

import com.cegeka.xpdays.arduino.Arduino;
import jssc.SerialPort;
import org.springframework.stereotype.Service;

import java.io.IOException;

import static com.cegeka.xpdays.arduino.Arduino.fromSerialPort;

@Service
public class ArduinoService {

    private Arduino arduino;

    public void openArduinoPort(SerialPort serialPort){
        closeArduinoPort();
        arduino = fromSerialPort(serialPort);
    }

    public void closeArduinoPort(){
        if(arduino != null){
            try {
                arduino.close();
            } catch (IOException e) {
                throw new RuntimeException("Could not close arduino port: "+e.getMessage());
            }
        }
    }

    public Arduino getArduino() {
        return arduino;
    }
}
