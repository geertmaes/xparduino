package com.cegeka.xpdays.arduino.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrainService {

    private ArduinoService arduinoService;

    @Autowired
    private TrainService(ArduinoService arduinoService){
        this.arduinoService = arduinoService;
    }

    public void setSpeed(int speed){

    }
}
