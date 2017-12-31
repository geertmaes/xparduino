package com.cegeka.xparduino.command.impl.trackswitch;

import com.cegeka.xparduino.event.impl.trackswitch.TrackSwitchEvent;
import org.junit.Test;

import static com.cegeka.xparduino.command.CommandTestConstants.TrackSwitch.trackSwitchCommand;
import static com.cegeka.xparduino.domain.Direction.LEFT;
import static com.cegeka.xparduino.domain.Direction.RIGHT;
import static com.cegeka.xparduino.event.EventTestConstants.trackSwitchEvent;
import static org.assertj.core.api.Assertions.assertThat;

public class TrackSwitchCommandBehaviourTest {

    private final TrackSwitchCommandBehaviour behaviour = new TrackSwitchCommandBehaviour();

    @Test
    public void create_Direction_Left() throws Exception {
        TrackSwitchEvent event = behaviour.createEvent(trackSwitchCommand(LEFT));

        assertThat(event).isEqualTo(trackSwitchEvent(LEFT));
    }

    @Test
    public void create_Direction_Right() throws Exception {
        TrackSwitchEvent event = behaviour.createEvent(trackSwitchCommand(RIGHT));

        assertThat(event).isEqualTo(trackSwitchEvent(RIGHT));
    }

}