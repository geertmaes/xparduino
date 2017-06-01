package com.cegeka.xpdays.arduino.rest.resource;

import com.cegeka.xpdays.arduino.rest.service.TrainService;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class TrainResourceTest {

    private TrainResource trainResource;
    private TrainService trainService;

    @Before
    public void setUp() throws Exception {
        trainService = mock(TrainService.class);
        trainResource = new TrainResource(trainService);
    }

    @Test
    public void setSpeed_invalid() throws Exception {
        assertThatThrownBy(() -> trainResource.setTrainSpeed(-5)).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> trainResource.setTrainSpeed(5)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void setSpeed() throws Exception {
        trainResource.setTrainSpeed(2);

        verify(trainService).setSpeed(2);
    }
}
