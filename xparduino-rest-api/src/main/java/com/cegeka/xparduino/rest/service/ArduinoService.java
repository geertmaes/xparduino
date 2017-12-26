package com.cegeka.xparduino.rest.service;

import com.cegeka.xparduino.Arduino;
import com.cegeka.xparduino.ArduinoFactory;
import com.cegeka.xparduino.bootstrap.ArduinoConfiguration;
import com.cegeka.xparduino.bootstrap.configurator.component.ComponentConfig;
import com.cegeka.xparduino.queue.serialport.SerialPortQueueConfig;
import org.springframework.stereotype.Service;

import static com.cegeka.xparduino.component.ComponentPin.*;
import static com.cegeka.xparduino.component.ComponentType.*;

@Service
public class ArduinoService {

    private Arduino arduino;

    public void openArduinoPort(String portName) {
        ArduinoConfiguration arduinoConfig = ArduinoConfiguration.builder()
                .withArduinoQueue(new SerialPortQueueConfig(portName))
                .withComponents(new ComponentConfig.Builder()
                        .withComponent(DIGITAL_2, INFRARED_EMITTER)
                        .withComponent(DIGITAL_3, OBSTACLE_SENSOR)
                        .withComponent(DIGITAL_4, TRACK_SWITCH)
                        .withComponent(DIGITAL_8, BASE_LED)
                        .withComponent(DIGITAL_9, BASE_LED)
                        .withComponent(DIGITAL_11, RFID_READER)
                        .withComponent(ANALOG_0, PHOTO_SENSOR)
                        .build())
                .build();
        arduino = new ArduinoFactory().create(arduinoConfig);
    }

    public Arduino getArduino() {
        if (arduino == null) {
            throw new RuntimeException("Port not open");
        }
        return arduino;
    }
}
