package com.cegeka.xparduino.bootstrap;

import com.cegeka.xparduino.Arduino;
import com.cegeka.xparduino.bootstrap.composer.ArduinoQueueComposer;
import com.cegeka.xparduino.bootstrap.composer.ComponentDefaultPositionComposer;
import com.cegeka.xparduino.bootstrap.composer.Composer;
import com.cegeka.xparduino.bootstrap.composer.EventDispatcherComposer;
import com.cegeka.xparduino.bootstrap.configurator.Configurator;
import com.cegeka.xparduino.bootstrap.configurator.component.ComponentConfigurator;
import com.cegeka.xparduino.bootstrap.configurator.eventmapper.EventMapperConfigurator;
import com.cegeka.xparduino.bootstrap.configurator.queue.ArduinoQueueConfigurator;
import com.cegeka.xparduino.channel.Channel;
import com.cegeka.xparduino.channel.ChannelImpl;
import com.cegeka.xparduino.channel.LockingChannel;
import com.cegeka.xparduino.command.Command;
import com.cegeka.xparduino.command.serialization.CommandSerializer;
import com.cegeka.xparduino.event.Event;
import com.cegeka.xparduino.event.mapper.EventMapper;
import com.cegeka.xparduino.queue.ArduinoQueue;
import com.cegeka.xparduino.state.ArduinoState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

import static com.cegeka.xparduino.channel.LockingChannel.locked;
import static com.cegeka.xparduino.channel.LoggingChannel.logged;
import static com.cegeka.xparduino.channel.NamedChannel.named;
import static com.cegeka.xparduino.utils.ClassUtils.className;
import static com.google.common.collect.Lists.newArrayList;

public class ArduinoBootstrap {

    private static final Logger LOGGER = LoggerFactory.getLogger(ArduinoBootstrap.class);

    private static final String EVENT_CHANNEL = "Event Channel";
    private static final String COMMAND_CHANNEL = "Command Channel";

    public static Arduino fromConfiguration(ArduinoConfiguration configuration, PostBuildListener... postBuildListeners) {
        ArduinoBootstrap bootstrap = new ArduinoBootstrap();

        bootstrap.addComposer(new EventDispatcherComposer());
        bootstrap.addComposer(new ArduinoQueueComposer());
        bootstrap.addComposer(new ComponentDefaultPositionComposer());

        bootstrap.addConfigurator(new ComponentConfigurator());
        bootstrap.addConfigurator(new EventMapperConfigurator());
        bootstrap.addConfigurator(new ArduinoQueueConfigurator());

        Arrays.stream(postBuildListeners)
                .forEach(bootstrap::addPostBuildListener);

        return bootstrap.create(configuration);
    }

    private final LockingChannel<Event> eventChannel = createEventChannel();
    private final LockingChannel<Command> commandChannel = createCommandChannel();

    private final List<Composer> composers = newArrayList();
    private final List<Configurator<? super ArduinoConfiguration>> configurators = newArrayList();
    private final List<PostBuildListener> postBuildListeners = newArrayList();

    private final BootstrapState bootstrapState = new BootstrapState();

    private Arduino create(ArduinoConfiguration config) {
        LOGGER.info("---------------------------------------");
        lockChannels();
        executeConfigurators(config);
        executeComposers();
        executePostBuildListeners();
        releaseChannels();
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

    private void executePostBuildListeners() {
        postBuildListeners.forEach(this::executePostBuildListener);
    }

    private void executePostBuildListener(PostBuildListener listener) {
        try {
            LOGGER.info("Executing post-build listener ({})", className(listener));
            listener.onPostBuild(this);
        } catch (Exception e) {
            LOGGER.info("Error composing ({})", className(listener), e);
            throw new ArduinoBootstrapCompositionException(e);
        }
    }

    private LockingChannel<Event> createEventChannel() {
        return locked(logged(named(new ChannelImpl<>(), EVENT_CHANNEL), event -> getEventMapper().toSerializedEvent(event).toString()));
    }

    private LockingChannel<Command> createCommandChannel() {
        return locked(logged(named(new ChannelImpl<>(), COMMAND_CHANNEL), command -> new CommandSerializer().serialize(command)));
    }

    private void lockChannels() {
        eventChannel.lock();
        commandChannel.lock();
    }

    private void releaseChannels() {
        eventChannel.release();
        commandChannel.release();
    }

    private void addConfigurator(Configurator<? super ArduinoConfiguration> configurator) {
        configurators.add(configurator);
    }

    private void addComposer(Composer composer) {
        composers.add(composer);
    }

    private void addPostBuildListener(PostBuildListener listener) {
        postBuildListeners.add(listener);
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

    @FunctionalInterface
    public interface PostBuildListener {

        void onPostBuild(ArduinoBootstrap bootstrap);
    }

}
