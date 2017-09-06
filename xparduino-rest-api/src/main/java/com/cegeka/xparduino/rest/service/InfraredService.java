package com.cegeka.xparduino.rest.service;

import com.cegeka.xparduino.domain.Color;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.cegeka.xparduino.component.ComponentPin.DIGITAL_2;
import static java.util.concurrent.TimeUnit.MILLISECONDS;

@Service
public class InfraredService {

    private ArduinoService arduinoService;

    @Autowired
    private InfraredService(ArduinoService arduinoService) {
        this.arduinoService = arduinoService;
    }

    public void emit(Color color, int channel, int speed) {
        arduinoService.getArduino()
                .train(DIGITAL_2)
                    .withColor(color)
                    .withChannel(channel)
                    .withSpeed(speed)
                .repeating()
                    .withDelay(0)
                    .withTimes(10)
                    .withPeriod(100)
                    .withTimeUnit(MILLISECONDS)
                .execute();
    }
}
