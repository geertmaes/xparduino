package com.cegeka.xparduino.bootstrap.configurator.objectmapper;

import com.cegeka.xparduino.bootstrap.ArduinoBootstrap;
import com.cegeka.xparduino.bootstrap.configurator.Configurator;
import com.cegeka.xparduino.command.AbstractCommand;
import com.cegeka.xparduino.command.Command;
import com.cegeka.xparduino.command.mapper.CommandMapper;
import com.cegeka.xparduino.command.mapper.CommandMapperFactory;
import com.cegeka.xparduino.event.Event;
import com.cegeka.xparduino.event.mapper.EventMapper;
import com.cegeka.xparduino.event.mapper.EventMapperFactory;
import org.reflections.Reflections;

import java.util.Set;

import static java.util.stream.Collectors.toSet;

public class ObjectMapperConfigurator implements Configurator<ObjectMapperConfigHolder> {

    private final EventMapperFactory eventMapperFactory = new EventMapperFactory();
    private final CommandMapperFactory commandMapperFactory = new CommandMapperFactory();

    @Override
    public void configure(ObjectMapperConfigHolder config, ArduinoBootstrap.BootstrapState state) {
        ObjectMapperConfig objectMapperConfig = config.getObjectMapperConfig();

        Set<Class<? extends Event>> events = extractEvents(objectMapperConfig);
        Set<Class<? extends Command>> commands = extractCommands(objectMapperConfig);

        EventMapper eventMapper = eventMapperFactory.create(events);
        CommandMapper commandMapper = commandMapperFactory.create(commands);

        state.setEventMapper(eventMapper);
        state.setCommandMapper(commandMapper);
    }

    private Set<Class<? extends Event>> extractEvents(ObjectMapperConfig config) {
        String basePackage = config.getEventsPackage();
        return new Reflections(basePackage).getSubTypesOf(Event.class);
    }

    private Set<Class<? extends Command>> extractCommands(ObjectMapperConfig config) {
        String basePackage = config.getCommandsPackage();
        return new Reflections(basePackage).getSubTypesOf(Command.class)
                .stream()
                .filter(commandClass -> ! commandClass.equals(AbstractCommand.class))
                .collect(toSet());
    }

}
