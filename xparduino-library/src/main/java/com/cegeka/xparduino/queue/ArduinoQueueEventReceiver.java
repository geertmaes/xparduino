package com.cegeka.xparduino.queue;

import com.cegeka.xparduino.channel.Channel;
import com.cegeka.xparduino.event.Event;
import com.cegeka.xparduino.event.mapper.EventMapper;
import com.cegeka.xparduino.event.serialized.SerializedEvent;

public class ArduinoQueueEventReceiver implements ArduinoQueueReceiver {

    private final EventMapper eventMapper;
    private final Channel<Event> eventChannel;

    public ArduinoQueueEventReceiver(EventMapper eventMapper, Channel<Event> eventChannel) {
        this.eventMapper = eventMapper;
        this.eventChannel = eventChannel;
    }

    @Override
    public void onMessage(String message) {
        SerializedEvent serializedEvent = eventMapper.mapToSerializedEvent(message);
        Event event = eventMapper.mapToEvent(serializedEvent);
        eventChannel.send(event);
    }
}
