package com.cegeka.xparduino.event.impl.trackswitch;

import com.cegeka.xparduino.component.ComponentPin;
import com.cegeka.xparduino.domain.Direction;
import com.cegeka.xparduino.event.AbstractEvent;
import com.cegeka.xparduino.event.EventMapping;

import static com.cegeka.xparduino.event.EventCode.TRACK_SWITCH_EVENT;
import static java.util.Objects.requireNonNull;

@EventMapping(
        code = TRACK_SWITCH_EVENT,
        serializer = TrackSwitchEventSerializer.class,
        deserializer = TrackSwitchEventDeserializer.class)
public class TrackSwitchEvent extends AbstractEvent {

    private final Direction direction;

    public TrackSwitchEvent(ComponentPin pin, Direction direction) {
        super(pin);
        requireNonNull(direction);
        this.direction = direction;
    }

    public Direction getDirection() {
        return direction;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        TrackSwitchEvent that = (TrackSwitchEvent) o;

        return direction == that.direction;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (direction != null ? direction.hashCode() : 0);
        return result;
    }

}
