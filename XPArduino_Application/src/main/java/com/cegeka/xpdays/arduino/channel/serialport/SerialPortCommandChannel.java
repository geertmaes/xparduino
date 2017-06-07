package com.cegeka.xpdays.arduino.channel.serialport;

import com.cegeka.xpdays.arduino.command.Command;
import com.cegeka.xpdays.arduino.command.CommandSerializer;
import com.cegeka.xpdays.arduino.channel.CommandChannel;
import jssc.SerialPort;
import jssc.SerialPortException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SerialPortCommandChannel implements CommandChannel {

    private final Logger logger = LoggerFactory.getLogger(SerialPortCommandChannel.class);

    private final SerialPort port;
    private final CommandSerializer commandSerializer = new CommandSerializer();

    public SerialPortCommandChannel(SerialPort port) {
        this.port = port;
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
            logger.info("Sending {} on port {}", message, port.getPortName());
        } catch (SerialPortException e) {
            logger.warn("Failed to send {} on port {}", message, port, e);
        }
    }
}
