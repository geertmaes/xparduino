package com.cegeka.xparduino.rest.service;

import com.cegeka.xparduino.Arduino;
import com.cegeka.xparduino.component.ComponentType;
import com.cegeka.xparduino.configuration.ArduinoConfiguration;
import com.cegeka.xparduino.queue.serialport.SerialPortQueueConfiguration;
import org.springframework.stereotype.Service;

import javax.annotation.PreDestroy;
import java.io.IOException;

@Service
public class ArduinoService {

    private Arduino arduino;

    public void openArduinoPort(String portName){
        closeArduinoPort();
        ArduinoConfiguration arduinoConfiguration = ArduinoConfiguration.builder()
                .withQueueConfiguration(new SerialPortQueueConfiguration(portName))
                .withComponent(8, ComponentType.BASE_LED)
                .withComponent(2, ComponentType.INFRARED_EMITTER)
                .withComponent(3, ComponentType.OBSTACLE_SENSOR)
                .withComponent(4, ComponentType.TRACK_SWITCH)
                .withComponent(9, ComponentType.BASE_LED)
                .withComponent(14, ComponentType.PHOTO_SENSOR)
                .withComponent(11, ComponentType.RFID_READER)
                .build();
        arduino = Arduino.create(arduinoConfiguration);
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
