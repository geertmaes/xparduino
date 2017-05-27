import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;
import sun.jvm.hotspot.runtime.Bytes;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

public class PortReader implements SerialPortEventListener{

    private final SerialPort serialPort;

    public PortReader(SerialPort serialPort) {
        this.serialPort = serialPort;
    }

    @Override
    public void serialEvent(SerialPortEvent serialPortEvent) {
        if (serialPortEvent.isRXCHAR() && serialPortEvent.getEventValue() > 0) {
            try {
                String receivedData = this.serialPort.readString(serialPortEvent.getEventValue());
                System.out.println("Received response: " + receivedData);
            } catch (SerialPortException e) {
                System.out.println("Error receiving from serial port: " + e);
            }
        }
    }

}
