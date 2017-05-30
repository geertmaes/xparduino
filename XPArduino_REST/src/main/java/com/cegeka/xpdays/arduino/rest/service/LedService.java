package com.cegeka.xpdays.arduino.rest.service;

import com.cegeka.xpdays.arduino.command.RepeatingCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class LedService {

    private ArduinoService arduinoService;
    private RepeatingCommand blinkCommand;

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
        blinkCommand = arduinoService.getArduino()
                .baseLedBlink(8)
                .withDelay(delay)
                .withPeriod(period)
                .withTimeUnit(timeUnit);
        blinkCommand.execute();
    }

    private void changeLedState(boolean emitting){
        if(blinkCommand != null){
            blinkCommand.stop();
            blinkCommand = null;
        }
        arduinoService.getArduino()
                .baseLed(8)
                .withEmitting(emitting)
                .execute();
    }
}
