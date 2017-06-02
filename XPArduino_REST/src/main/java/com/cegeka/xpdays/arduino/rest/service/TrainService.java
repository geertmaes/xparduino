package com.cegeka.xpdays.arduino.rest.service;

import com.cegeka.xpdays.arduino.rest.domain.Train;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainService {

    private InfraredService infraredService;
    private List<Train> trains;

    @Autowired
    public TrainService(InfraredService infraredService, List<Train> trains) {
        this.infraredService = infraredService;
        this.trains = trains;
    }

    public List<Train> getTrains() {
        return trains;
    }

    public Train getTrain(String identifier){
        return getTrains().stream()
                .filter(t -> t.getIdentifier().equals(identifier))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Unknown train "+identifier));
    }

    public void setSpeed(Train train, int speed){
        if(speed < 0){
            speed += 16;
        }
        infraredService.emit(train.getColor(), train.getChannel(), speed);
    }
}
