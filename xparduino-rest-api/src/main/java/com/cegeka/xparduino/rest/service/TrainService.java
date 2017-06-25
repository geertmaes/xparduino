package com.cegeka.xparduino.rest.service;

import com.cegeka.xparduino.state.component.impl.RfidReaderState;
import com.cegeka.xparduino.domain.Train;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainService {

    private InfraredService infraredService;
    private ArduinoService arduinoService;
    private List<Train> trains;
    private String lastTrain;

    @Autowired
    public TrainService(ArduinoService arduinoService, InfraredService infraredService, List<Train> trains) {
        this.arduinoService = arduinoService;
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

    public String getLastTrain(){
        RfidReaderState state = arduinoService.getArduino().getState(11, RfidReaderState.class);

        return state == null ? "" : (state.getTagId() == null ? "" : state.getTagId());
    }

    public void setSpeed(Train train, int speed){
        if(speed < 0){
            speed += 16;
        }
        infraredService.emit(train.getColor(), train.getChannel(), speed);
    }
}
