package com.cegeka.xpdays.arduino.rest.service;

import com.cegeka.xpdays.arduino.model.Color;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class InfraredService {

    private ArduinoService arduinoService;

    @Autowired
    private InfraredService(ArduinoService arduinoService){
        this.arduinoService = arduinoService;
    }

    public void emit(Color color, int channel, int speed){
        arduinoService.getArduino()
                .train(2)
                .withColor(color)
                .withChannel(channel)
                .withSpeed(speed)
                .withDelay(0)
                .withPeriod(100)
                .withTimeUnit(TimeUnit.MILLISECONDS)
                .withTimes(10)
                .execute();
    }
}
