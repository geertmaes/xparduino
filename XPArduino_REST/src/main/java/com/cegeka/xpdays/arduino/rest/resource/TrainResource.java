package com.cegeka.xpdays.arduino.rest.resource;

import com.cegeka.xpdays.arduino.rest.service.InfraredService;
import com.cegeka.xpdays.arduino.rest.transfer.TrainParamTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/train")
@CrossOrigin
public class TrainResource {

    private InfraredService infraredService;

    @Autowired
    public TrainResource(InfraredService infraredService) {
        this.infraredService = infraredService;
    }

    @PostMapping
    public void setTrainSpeed(@RequestBody TrainParamTO trainParams) throws Exception {
        validateTrainParams(trainParams);
        infraredService.emit(trainParams.color, trainParams.channel, trainParams.speed);
    }

    private void validateTrainParams(@RequestBody TrainParamTO trainParams) {
        if(trainParams.speed < -4 || trainParams.speed > 4){
            throw new IllegalArgumentException("Invalid speed "+trainParams.speed);
        }
        if(trainParams.color < 0 || trainParams.color > 1){
            throw new IllegalArgumentException("Invalid color "+trainParams.color);
        }
        if(trainParams.channel < 0 || trainParams.channel > 3){
            throw new IllegalArgumentException("Invalid channel "+trainParams.channel);
        }
    }

}
