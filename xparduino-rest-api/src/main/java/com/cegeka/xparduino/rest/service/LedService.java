package com.cegeka.xparduino.rest.service;

import com.cegeka.xparduino.command.RepeatingCommand;
import com.cegeka.xparduino.command.impl.baseled.BaseLedCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

import static com.cegeka.xparduino.component.ComponentPin.DIGITAL_8;

@Service
public class LedService {

    private ArduinoService arduinoService;
    private RepeatingCommand<BaseLedCommand> baseLedOnCommand;
    private RepeatingCommand<BaseLedCommand> baseLedOffCommand;

    @Autowired
    private LedService(ArduinoService arduinoService) {
        this.arduinoService = arduinoService;
    }

    public void enableLed() {
        changeLedState(true);
    }

    public void disableLed() {
        changeLedState(false);
    }

    public void startBlinkingLed(int delay, int period, TimeUnit timeUnit) {
        stopBlinkingLed();
        baseLedOnCommand = arduinoService.getArduino()
                .baseLed(DIGITAL_8)
                .repeat()
                .withDelay(delay)
                .withPeriod(period)
                .withTimeUnit(timeUnit);
        baseLedOffCommand = arduinoService.getArduino()
                .baseLed(DIGITAL_8)
                .repeat()
                .withDelay(delay + period / 2)
                .withPeriod(period)
                .withTimeUnit(timeUnit);
        baseLedOnCommand.execute();
        baseLedOffCommand.execute();
    }

    public void stopBlinkingLed() {
        if (baseLedOnCommand != null) {
            baseLedOnCommand.stop();
            baseLedOffCommand.stop();
            baseLedOnCommand = null;
            baseLedOffCommand = null;
        }
    }

    private void changeLedState(boolean emitting) {
        stopBlinkingLed();
        arduinoService.getArduino()
                .baseLed(DIGITAL_8)
                .withEmitting(emitting)
                .execute();
    }
}
