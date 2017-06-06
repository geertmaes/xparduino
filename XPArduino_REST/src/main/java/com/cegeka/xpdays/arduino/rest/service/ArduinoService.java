package com.cegeka.xpdays.arduino.rest.service;

import com.cegeka.xpdays.arduino.Arduino;
import com.cegeka.xpdays.arduino.ArduinoFactory;
import com.cegeka.xpdays.arduino.ArduinoConfiguration;
import org.springframework.stereotype.Service;

import javax.annotation.PreDestroy;
import java.io.IOException;

import static com.cegeka.xpdays.arduino.component.ComponentType.*;
import static com.cegeka.xpdays.arduino.component.ComponentType.PHOTO_SENSOR;

@Service
public class ArduinoService {

    private Arduino arduino;

    public void openArduinoPort(String portName){
        closeArduinoPort();
        ArduinoConfiguration arduinoConfiguration = ArduinoConfiguration.builder()
                .withPortName(portName)
                .withComponent(8, BASE_LED)
                .withComponent(2, INFRARED_EMITTER)
                .withComponent(3, OBSTACLE_SENSOR)
                .withComponent(4, TRACK_SWITCH)
                .withComponent(9, BASE_LED)
                .withComponent(14, PHOTO_SENSOR)
                .withComponent(11, RFID_READER)
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
