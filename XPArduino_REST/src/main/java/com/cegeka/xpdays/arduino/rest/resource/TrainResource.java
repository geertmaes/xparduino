package com.cegeka.xpdays.arduino.rest.resource;

import com.cegeka.xpdays.arduino.rest.domain.Train;
import com.cegeka.xpdays.arduino.rest.service.TrainService;
import com.cegeka.xpdays.arduino.rest.transfer.TrainParamTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/train")
@CrossOrigin
public class TrainResource {

    private TrainService trainService;


    @Autowired
    public TrainResource(TrainService trainService) {
        this.trainService = trainService;
    }

    @GetMapping
    public Collection<String> getTrains(){
        return trainService.getTrains().stream().map(Train::getIdentifier).collect(Collectors.toSet());
    }

    @GetMapping("/last")
    public String lastTrain(){
        return trainService.getLastTrain();
    }

    @PostMapping
    public void setTrainSpeed(@RequestBody TrainParamTO trainParams) throws Exception {
        validateTrainParams(trainParams);
        Train train = trainService.getTrain(trainParams.identifier);
        trainService.setSpeed(train, trainParams.speed);
    }

    private void validateTrainParams(TrainParamTO trainParams) {
        if(trainParams.speed < -4 || trainParams.speed > 4){
            throw new IllegalArgumentException("Invalid speed "+trainParams.speed);
        }
    }

}
