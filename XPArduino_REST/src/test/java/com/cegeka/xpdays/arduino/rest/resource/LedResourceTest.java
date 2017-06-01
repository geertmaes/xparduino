package com.cegeka.xpdays.arduino.rest.resource;

import com.cegeka.xpdays.arduino.rest.service.LedService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class LedResourceTest {

    private LedResource ledResource;
    private LedService ledService;

    @Before
    public void setUp() throws Exception {
        ledService = mock(LedService.class);
        ledResource = new LedResource(ledService, new ObjectMapper());
    }

    @Test
    public void turnOnLed() throws Exception {
        ledResource.controlLed("on", null);

        verify(ledService).enableLed();
    }

    @Test
    public void turnOffLed() throws Exception {
        ledResource.controlLed("off", null);

        verify(ledService).disableLed();
    }

    @Test
    public void startBlinkingLed_defaultParams() throws Exception {
        ledResource.controlLed("blinkon", null);

        verify(ledService).startBlinkingLed(anyInt(), anyInt(), isA(TimeUnit.class));
    }

    @Test
    public void startBlinkingLed_params() throws Exception {
        ledResource.controlLed("blinkon", "{\"delay\": 1, \"period\": 5, \"timeunit\": \"MINUTES\"}");

        verify(ledService).startBlinkingLed(1, 5, TimeUnit.MINUTES);
    }

    @Test
    public void stopBlinkingLed() throws Exception {
        ledResource.controlLed("blinkoff", null);

        verify(ledService).stopBlinkingLed();
    }
}
