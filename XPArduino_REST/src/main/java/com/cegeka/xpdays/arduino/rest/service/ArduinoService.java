package com.cegeka.xpdays.arduino.rest.service;

import com.cegeka.xpdays.arduino.Arduino;
import com.cegeka.xpdays.arduino.ArduinoFactory;
import jssc.SerialPort;
import org.springframework.stereotype.Service;

import javax.annotation.PreDestroy;
import java.io.IOException;

@Service
public class ArduinoService {

    private Arduino arduino;

    public void openArduinoPort(String portName){
        closeArduinoPort();
        arduino = ArduinoFactory.create(new SerialPort(portName));
    }

    @PreDestroy
    public void closeArduinoPort(){
        if(arduino != null){
            try {
                arduino.close();
            } catch (IOException ignore) {
            } finally {
                arduino = null;
            }
        }
    }

    public Arduino getArduino() {
        if(arduino == null){
            throw new RuntimeException("Port not open");
        }
        return arduino;
    }
}
