package com.cegeka.xparduino.rest.service;

import com.cegeka.xparduino.domain.Direction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SwitchService {

    private ArduinoService arduinoService;
    private Direction lastDirection;

    @Autowired
    public SwitchService(ArduinoService arduinoService) {
        this.arduinoService = arduinoService;
        this.lastDirection = Direction.LEFT;
    }

   public void switchTracks(){
        lastDirection = lastDirection.toggle();
        arduinoService.getArduino()
                .trackSwitch(4)
                .withDirection(lastDirection)
                .executing().execute();
   }
}
