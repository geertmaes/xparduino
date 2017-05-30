package com.cegeka.xpdays.arduino.communication;

import com.cegeka.xpdays.arduino.command.Command;
import com.cegeka.xpdays.arduino.command.CommandMapper;
import jssc.SerialPort;
import jssc.SerialPortException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class CommandChannelImpl implements CommandChannel{

    private final Logger logger = LoggerFactory.getLogger(CommandChannelImpl.class);

    private final CommandMapper commandMapper;
    private final SerialPort port;

    public CommandChannelImpl(SerialPort port) {
        this.port = port;
        this.commandMapper = new CommandMapper();
    }

    @Override
    public void send(Command command) {
        send(commandMapper.mapToString(command));
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
