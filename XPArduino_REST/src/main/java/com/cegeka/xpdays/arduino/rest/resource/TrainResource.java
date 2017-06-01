package com.cegeka.xpdays.arduino.rest.resource;

import com.cegeka.xpdays.arduino.rest.service.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/train")
@CrossOrigin
public class TrainResource {

    private TrainService trainService;

    @Autowired
    public TrainResource(TrainService trainService) {
        this.trainService = trainService;
    }

    @PostMapping
    public void setTrainSpeed(@RequestParam("speed") int speed) throws Exception {
        if(speed < -4 || speed > 4){
            throw new IllegalArgumentException("Invalid speed for train "+speed);
        }
        trainService.setSpeed(speed);
    }

}
