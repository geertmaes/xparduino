package com.cegeka.xparduino.bootstrap.configurator.eventmapper;

import com.cegeka.xparduino.bootstrap.ArduinoBootstrap;
import com.cegeka.xparduino.bootstrap.configurator.Configurator;
import com.cegeka.xparduino.event.Event;
import com.cegeka.xparduino.event.mapper.EventMapper;
import com.cegeka.xparduino.event.mapper.EventMapperFactory;
import org.reflections.Reflections;

import java.util.Set;

public class EventMapperConfigurator implements Configurator<EventMapperConfigHolder> {

    private final EventMapperFactory eventMapperFactory = new EventMapperFactory();

    @Override
    public void configure(EventMapperConfigHolder config, ArduinoBootstrap.BootstrapState state) {
        EventMapperConfig eventMapperConfig = config.getEventMapperConfig();
        Set<Class<? extends Event>> events = extractEvents(eventMapperConfig);
        EventMapper eventMapper = eventMapperFactory.create(events);

        state.setEventMapper(eventMapper);
    }

    private Set<Class<? extends Event>> extractEvents(EventMapperConfig config) {
        String basePackage = config.getBasePackage();
        Set<Class<? extends Event>> events = config.getEvents();

        if (basePackage != null && !basePackage.isEmpty()) {
            events.addAll(scanBasePackage(basePackage));
        }

        return events;
    }

    private Set<Class<? extends Event>> scanBasePackage(String basePackage) {
        return new Reflections(basePackage).getSubTypesOf(Event.class);
    }

}
