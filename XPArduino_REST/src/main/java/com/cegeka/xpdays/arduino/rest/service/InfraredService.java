package com.cegeka.xpdays.arduino.rest.service;

import com.cegeka.xpdays.arduino.command.impl.InfraredCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InfraredService {

    private ArduinoService arduinoService;

    @Autowired
    private InfraredService(ArduinoService arduinoService){
        this.arduinoService = arduinoService;
    }

    public void emit(int color, int channel, int speed){
        arduinoService.getArduino()
                .infrared(2)
                .withColor(InfraredCommand.Color.forColor(color))
                .withChannel(channel)
                .withSpeed(speed)
                .execute();
    }
}
