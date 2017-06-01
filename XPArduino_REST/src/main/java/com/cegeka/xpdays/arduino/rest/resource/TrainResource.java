package com.cegeka.xpdays.arduino.rest.resource;

import com.cegeka.xpdays.arduino.rest.service.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/train")
@CrossOrigin
public class TrainResource {

    @Autowired
    private TrainService trainService;

    @PostMapping
    public void controlTrain(@RequestParam("speed") int speed) throws Exception {
        if(speed < -4 || speed > 4){
            throw new IllegalArgumentException("Invalid speed for train "+speed);
        }
        trainService.setSpeed(speed);
    }

}
