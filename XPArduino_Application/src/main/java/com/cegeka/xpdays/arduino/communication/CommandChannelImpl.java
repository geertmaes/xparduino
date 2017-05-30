package com.cegeka.xpdays.arduino.communication;

import com.cegeka.xpdays.arduino.command.Command;
import com.cegeka.xpdays.arduino.command.CommandSerializer;
import jssc.SerialPort;
import jssc.SerialPortException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CommandChannelImpl implements CommandChannel{

    private final Logger logger = LoggerFactory.getLogger(CommandChannelImpl.class);

    private final SerialPort port;
    private final CommandSerializer commandSerializer;

    public CommandChannelImpl(SerialPort port) {
        this.port = port;
        this.commandSerializer = new CommandSerializer();
    }

    @Override
    public void send(Command command) {
        send(commandSerializer.serialize(command));
    }

    @Override
    public void close() {
        try {
            port.closePort();
        } catch (SerialPortException e) {
            logger.warn("Failed to close port {}", port, e);
        }
    }

    private void send(String message) {
        try {
            port.writeString(message);
            logger.info("Sending {} on port {}", message, port);
        } catch (SerialPortException e) {
            logger.warn("Failed to send {} on port {}", message, port, e);
        }
    }

    @Override
    public void close() {
        try {
            port.closePort();
        } catch (SerialPortException ignore) {
        }
    }
}
