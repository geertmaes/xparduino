package com.cegeka.xparduino;

import com.cegeka.xparduino.bootstrap.ArduinoBootstrap;
import com.cegeka.xparduino.bootstrap.ArduinoConfiguration;
import com.cegeka.xparduino.bootstrap.configurator.component.ComponentConfig;
import com.cegeka.xparduino.channel.Channel;
import com.cegeka.xparduino.component.Component;
import com.cegeka.xparduino.event.Event;
import com.cegeka.xparduino.queue.stub.StubQueueConfig;
import org.junit.rules.ExternalResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Arrays;

import static java.lang.String.format;
import static java.lang.Thread.sleep;
import static junit.framework.TestCase.fail;

public class ArduinoTestRule extends ExternalResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(ArduinoTestRule.class);

    private final EventProber eventProber;
    private final EventSender eventSender;
    private final ArduinoConfiguration configuration;

    private Arduino arduino;

    public ArduinoTestRule(Component... components) {
        this.configuration = toArduinoConfig(components);
        this.eventProber = new EventProber();
        this.eventSender = new EventSender();
    }

    public void emitEvents(Event... events) {
        Arrays.stream(events).forEach(eventSender::send);
        this.waitForIncomingEvents(events.length);
    }

    private void waitForIncomingEvents(int events) {
        this.eventProber.waitForProcessedEvents(events);
    }

    public Arduino arduino() {
        return arduino;
    }

    @Override
    protected void before() throws Throwable {
        this.arduino = ArduinoBootstrap.fromConfiguration(configuration, eventSender, eventProber);
    }

    @Override
    protected void after() {
        try {
            this.arduino.close();
        } catch (IOException e) {
            LOGGER.error("Failed to close arduino", e);
        }
    }

    private ArduinoConfiguration toArduinoConfig(Component... components) {
        return ArduinoConfiguration.builder()
                .withArduinoQueue(new StubQueueConfig())
                .withComponents(toComponentConfig(components))
                .build();
    }

    private ComponentConfig toComponentConfig(Component... components) {
        ComponentConfig.Builder builder = new ComponentConfig.Builder();
        Arrays.stream(components)
                .forEach(component -> builder.withComponent(component.getPin(), component.getType()));
        return builder.build();
    }

    private static class EventSender implements ArduinoBootstrap.PostBuildListener {

        private Channel<Event> eventChannel;

        @Override
        public void onPostBuild(ArduinoBootstrap bootstrap) {
            eventChannel = bootstrap.getEventChannel();
        }

        void send(Event event) {
            eventChannel.send(event);
        }

    }

    private static class EventProber implements ArduinoBootstrap.PostBuildListener {

        private static final int WAIT_TIME = 100;
        private static final int MAX_RETRIES = 10;

        private volatile int receivedEvents = 0;

        @Override
        public void onPostBuild(ArduinoBootstrap bootstrap) {
            bootstrap.getEventChannel()
                    .register(this::receivedEvent);
            bootstrap.getCommandChannel()
                    .register(command -> waitForProcessedEvents(1));
        }

        private void receivedEvent(Event event) {
            receivedEvents++;
        }

        void waitForProcessedEvents(int events) {
            int retries = 0;

            while (receivedEvents != events && retries < MAX_RETRIES) {
                try {
                    LOGGER.info("Waiting for {} processed events", events - receivedEvents);
                    retries++;
                    sleep(WAIT_TIME);
                } catch (InterruptedException e) {
                    LOGGER.error("Error waiting for processed events", e);
                    throw new RuntimeException(e);
                }
            }

            if (retries >= MAX_RETRIES) {
                fail(format("Max retries (%s) exceeded", MAX_RETRIES));
            }

            receivedEvents = 0;
        }
    }

}
