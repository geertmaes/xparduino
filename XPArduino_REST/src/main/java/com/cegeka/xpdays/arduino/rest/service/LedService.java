package com.cegeka.xpdays.arduino.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class LedService {

    private ArduinoService arduinoService;

    @Autowired
    private LedService(ArduinoService arduinoService){
        this.arduinoService = arduinoService;
    }

    public void enableLed(){
        changeLedState(true);
    }

    public void disableLed(){
        changeLedState(false);
    }

    public void startBlinkingLed(int delay, int period, TimeUnit timeUnit){
        arduinoService.getArduino()
                .baseLedBlink()
                .withDelay(delay)
                .withPeriod(period)
                .withTimeUnit(timeUnit)
                .execute();
    }

    private void changeLedState(boolean emitting){
        arduinoService.getArduino()
                .baseLed()
                .withEmitting(emitting)
                .execute();
    }
}
