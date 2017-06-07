package com.cegeka.xpdays.arduino.channel.serialport;

import com.cegeka.xpdays.arduino.event.dispatch.EventDispatcher;
import com.cegeka.xpdays.arduino.event.dispatch.EventDispatchingException;
import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SerialPortEventChannelListener implements SerialPortEventListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(SerialPortEventChannelListener.class);

    private final SerialPort port;
    private final EventDispatcher eventDispatcher;
    private final EventBuffer buffer = new EventBuffer();

    SerialPortEventChannelListener(SerialPort port, EventDispatcher eventDispatcher) {
        this.port = port;
        this.eventDispatcher = eventDispatcher;
    }

    @Override
    public void serialEvent(SerialPortEvent event) {
        try {
            handle(event);
        } catch (SerialPortException e) {
            LOGGER.warn("Failed reading string from serial port", e);
        } catch (EventDispatchingException e) {
            LOGGER.warn("Failed dispatching event", e);
        }
    }

    private void handle(SerialPortEvent event) throws SerialPortException, EventDispatchingException {
        String payload = readString(event);
        buffer.add(payload);

        while (buffer.hasNext()) {
            eventDispatcher.dispatch(buffer.next());
        }
    }

    private String readString(SerialPortEvent event) throws SerialPortException {
        return this.port.readString(event.getEventValue());
    }
}