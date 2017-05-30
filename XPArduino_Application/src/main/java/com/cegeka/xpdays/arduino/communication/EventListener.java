package com.cegeka.xpdays.arduino.communication;

import com.cegeka.xpdays.arduino.event.Event;

import java.util.function.Consumer;

public interface EventListener extends Consumer<Event> {
}
