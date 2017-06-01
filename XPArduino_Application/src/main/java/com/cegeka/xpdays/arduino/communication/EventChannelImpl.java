package com.cegeka.xpdays.arduino.communication;

import com.cegeka.xpdays.arduino.event.dispatch.EventDispatcher;
import com.cegeka.xpdays.arduino.event.dispatch.EventDispatchingException;
import com.cegeka.xpdays.arduino.event.dispatch.EventListener;
import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class EventChannelImpl implements EventChannel {

    private static final Logger LOGGER = LoggerFactory.getLogger(EventChannelImpl.class);

    private final SerialPort port;
    private final EventDispatcher eventDispatcher;

    public EventChannelImpl(SerialPort port) {
        this.port = port;
        this.eventDispatcher = new EventDispatcher();
        setupSerialPortListener();
    }

    private void setupSerialPortListener() {
        try {
            this.port.addEventListener(new EventChannelSerialPortListener(port, eventDispatcher));
        } catch (SerialPortException e) {
            LOGGER.warn("Failed to add serial port listener", e);
        }
    }

    @Override
    public void send(String payload) {
        try {
            this.eventDispatcher.dispatch(payload);
        } catch (EventDispatchingException e) {
            LOGGER.warn("Failed dispatching event", e);
        }
    }

    @Override
    public void registerEventListener(EventListener listener) {
        this.eventDispatcher.registerEventListener(listener);
    }

    @Override
    public void close() throws IOException {
        try {
            port.closePort();
        } catch (SerialPortException e) {
            LOGGER.warn("Failed to close port {}", port, e);
        }
    }

    private static class EventChannelSerialPortListener implements SerialPortEventListener {

        private static final String EMPTY = "";
        private static final String EVENT_PREFIX = "<";
        private static final String EVENT_SUFFIX = ">";

        private final SerialPort port;
        private final EventDispatcher eventDispatcher;

        private String buffer = EMPTY;

        private EventChannelSerialPortListener(SerialPort port, EventDispatcher eventDispatcher) {
            this.port = port;
            this.eventDispatcher = eventDispatcher;
        }

        @Override
        public void serialEvent(SerialPortEvent event) {
            try {
                if (isValidEvent(event)) {
                    buffer += readString(event);
                    LOGGER.info("Received event from serial port ()", buffer);
                    if (buffer.startsWith(EVENT_PREFIX) && buffer.endsWith(EVENT_SUFFIX)) {
                        eventDispatcher.dispatch(buffer);
                        buffer = EMPTY;
                    }
                }
            } catch (SerialPortException e) {
                LOGGER.warn("Failed reading string from serial port", e);
            } catch (EventDispatchingException e) {
                LOGGER.warn("Failed dispatching event", e);
            }
        }

        private boolean isValidEvent(SerialPortEvent event) {
            return event.isRXCHAR() && event.getEventValue() > 0;
        }

        private String readString(SerialPortEvent event) throws SerialPortException {
            return this.port.readString(event.getEventValue());
        }
    }
}
