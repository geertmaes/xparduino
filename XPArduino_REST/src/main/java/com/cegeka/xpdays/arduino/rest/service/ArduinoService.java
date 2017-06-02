package com.cegeka.xpdays.arduino.rest.service;

import com.cegeka.xpdays.arduino.Arduino;
import com.cegeka.xpdays.arduino.ArduinoFactory;
import com.cegeka.xpdays.arduino.configuration.ArduinoConfiguration;
import org.springframework.stereotype.Service;

import javax.annotation.PreDestroy;
import java.io.IOException;

import static com.cegeka.xpdays.arduino.component.ComponentType.BASE_LED;
import static com.cegeka.xpdays.arduino.component.ComponentType.INFRARED_EMITTER;

@Service
public class ArduinoService {

    private Arduino arduino;

    public void openArduinoPort(String portName){
        closeArduinoPort();
        ArduinoConfiguration arduinoConfiguration = ArduinoConfiguration.builder()
                .withPortName(portName)
                .withComponent(8, BASE_LED)
                .withComponent(2, INFRARED_EMITTER)
                .build();
        arduino = ArduinoFactory.create(arduinoConfiguration);
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
