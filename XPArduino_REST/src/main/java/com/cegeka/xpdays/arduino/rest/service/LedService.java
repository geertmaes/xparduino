package com.cegeka.xpdays.arduino.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LedService {

    @Autowired
    private ArduinoService arduinoService;

    public void enableLed(){
        changeLedState(true);
    }

    public void disableLed(){
        changeLedState(false);
    }

    private void changeLedState(boolean emitting){
        arduinoService.getArduino()
                .baseLed()
                .withEmitting(emitting)
                .execute();
    }
}
