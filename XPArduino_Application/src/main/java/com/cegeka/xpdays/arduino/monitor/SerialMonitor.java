package com.cegeka.xpdays.arduino.monitor;

import com.cegeka.xpdays.arduino.command.Command;
import com.cegeka.xpdays.arduino.command.CommandMapper;
import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.sun.tools.doclint.Entity.ne;
import static jssc.SerialPort.MASK_RXCHAR;

public class SerialMonitor {

    private final Logger logger = LoggerFactory.getLogger(SerialMonitor.class);

    private final SerialPort port;
    private final CommandMapper commandMapper;

    public SerialMonitor(SerialPort port) {
        this.port = port;
        commandMapper = new CommandMapper();
    }

    public void send(String message) {
        try {
            port.writeString(message);
            logger.info("Sending {} on port {}", message, port);
        } catch (SerialPortException e) {
            logger.warn("Failed to send {} on port {}", message, port, e);
        }
    }

    public void send(Command command) {
        send(commandMapper.mapToString(command));
    }

    public void onMessage(MessageListener listener) throws SerialPortException {
        port.addEventListener(new SerialPortEventHandler(port, listener), MASK_RXCHAR);
    }
}
