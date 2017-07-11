package com.cegeka.xparduino.bootstrap;

import com.cegeka.xparduino.Arduino;
import com.cegeka.xparduino.bootstrap.composer.ArduinoQueueComposer;
import com.cegeka.xparduino.bootstrap.composer.ComponentComposer;
import com.cegeka.xparduino.bootstrap.composer.Composer;
import com.cegeka.xparduino.bootstrap.configurator.Configurator;
import com.cegeka.xparduino.bootstrap.configurator.component.ComponentConfigurator;
import com.cegeka.xparduino.bootstrap.configurator.eventmapper.EventMapperConfigurator;
import com.cegeka.xparduino.bootstrap.configurator.queue.ArduinoQueueConfigurator;
import com.cegeka.xparduino.channel.Channel;
import com.cegeka.xparduino.channel.ChannelImpl;
import com.cegeka.xparduino.command.Command;
import com.cegeka.xparduino.event.Event;
import com.cegeka.xparduino.event.mapper.EventMapper;
import com.cegeka.xparduino.queue.ArduinoQueue;
import com.cegeka.xparduino.state.ArduinoState;
import com.google.common.base.Stopwatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

public class ArduinoBootstrap {

    private static final Logger LOGGER = LoggerFactory.getLogger(ArduinoBootstrap.class);

    private static final String EVENT_CHANNEL = "Event Channel";
    private static final String COMMAND_CHANNEL = "Command Channel";

    public static Arduino fromConfiguration(ArduinoConfiguration configuration) {
        ArduinoBootstrap bootstrap = new ArduinoBootstrap();

        bootstrap.addComposer(new ComponentComposer());
        bootstrap.addComposer(new ArduinoQueueComposer());

        bootstrap.addConfigurator(new ComponentConfigurator());
        bootstrap.addConfigurator(new EventMapperConfigurator());
        bootstrap.addConfigurator(new ArduinoQueueConfigurator());

        return bootstrap.create(configuration);
    }

    private final Channel<Event> eventChannel = new ChannelImpl<>(EVENT_CHANNEL);
    private final Channel<Command> commandChannel = new ChannelImpl<>(COMMAND_CHANNEL);

    private final List<Composer> composers = newArrayList();
    private final List<Configurator<? super ArduinoConfiguration>> configurators = newArrayList();

    private final BootstrapState bootstrapState = new BootstrapState();

    private Arduino create(ArduinoConfiguration config) {
        Stopwatch stopwatch = Stopwatch.createStarted();
        LOGGER.info("---------------------------------------");
        LOGGER.info("Bootstrapping Arduino");
        executeConfigurators(config);
        executeComposers();
        LOGGER.info("Bootstrapping Arduino took {}", stopwatch.stop());
        LOGGER.info("---------------------------------------\n");
        return new Arduino(getArduinoState(), commandChannel);
    }

    private void executeConfigurators(ArduinoConfiguration config) {
        configurators.forEach(configurator -> executeConfigurator(config, configurator));
    }

    private void executeConfigurator(ArduinoConfiguration config, Configurator<? super ArduinoConfiguration> configurator) {
        try {
            LOGGER.info("Executing configurator ({})", className(configurator));
            configurator.configure(config, bootstrapState);
        } catch (Exception e) {
            LOGGER.info("Error configuring ({})", className(configurator), e);
            throw new ArduinoBootstrapConfigurationException(e);
        }
    }

    private void executeComposers() {
        composers.forEach(this::executeComposer);
    }

    private void executeComposer(Composer composer) {
        try {
            LOGGER.info("Executing composer ({})", className(composer));
            composer.compose(this);
        } catch (Exception e) {
            LOGGER.info("Error composing ({})", className(composer), e);
            throw new ArduinoBootstrapCompositionException(e);
        }
    }

    private String className(Object obj) {
        return obj.getClass().getSimpleName();
    }

    private void addConfigurator(Configurator<? super ArduinoConfiguration> configurator) {
        configurators.add(configurator);
    }

    private void addComposer(Composer composer) {
        composers.add(composer);
    }

    public Channel<Event> getEventChannel() {
        return eventChannel;
    }

    public Channel<Command> getCommandChannel() {
        return commandChannel;
    }

    public ArduinoQueue getQueue() {
        return bootstrapState.getArduinoQueue();
    }

    public EventMapper getEventMapper() {
        return bootstrapState.getEventMapper();
    }

    public ArduinoState getArduinoState() {
        return bootstrapState.getArduinoState();
    }

    public static class BootstrapState {

        private EventMapper eventMapper;
        private ArduinoState arduinoState;
        private ArduinoQueue arduinoQueue;

        public void setEventMapper(EventMapper eventMapper) {
            this.eventMapper = eventMapper;
        }

        public void setArduinoState(ArduinoState arduinoState) {
            this.arduinoState = arduinoState;
        }

        public void setArduinoQueue(ArduinoQueue arduinoQueue) {
            this.arduinoQueue = arduinoQueue;
        }

        EventMapper getEventMapper() {
            return eventMapper;
        }

        ArduinoState getArduinoState() {
            return arduinoState;
        }

        ArduinoQueue getArduinoQueue() {
            return arduinoQueue;
        }

    }

}
