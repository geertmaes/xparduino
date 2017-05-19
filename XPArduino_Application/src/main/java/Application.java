import jssc.SerialPort;
import jssc.SerialPortException;


public class Application {

    public static final String ARDUINO_SERIAL_PORT = "COM3";

    public static void main(String[] args) {
        SerialPort serialPort = new SerialPort(ARDUINO_SERIAL_PORT);
        try {
            serialPort.openPort();
            serialPort.setParams(SerialPort.BAUDRATE_9600, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);

        } catch (SerialPortException e) {
            System.err.println(e);
        }
    }
}
