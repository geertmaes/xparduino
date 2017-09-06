package com.cegeka.xparduino.command.behaviour;

import com.cegeka.xparduino.command.Command;
import com.cegeka.xparduino.event.Event;
import com.google.common.collect.ImmutableSet;

import java.util.Set;

import static com.cegeka.xparduino.utils.ClassUtils.className;

public class CommandBehaviourMapper {

    private static final Set<CommandBehaviour> BEHAVIOURS =
            ImmutableSet.<CommandBehaviour>builder()
                    .add(new BaseLedCommandBehaviour())
                    .add(new TrackSwitchCommandBehaviour())
                    .build();

    @SuppressWarnings("unchecked")
    public Event toEvent(Command command) {
        return BEHAVIOURS.stream()
                .filter(behaviour -> behaviour.canHandle(command))
                .findFirst()
                .map(behaviour -> behaviour.create(command))
                .orElseThrow(() -> new RuntimeException("No command behaviour found for " + className(command)));
    }

}
