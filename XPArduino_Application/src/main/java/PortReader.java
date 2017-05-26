import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;

public class PortReader implements SerialPortEventListener{
    SerialPort serialPort;

    public PortReader(){

    }

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
                System.out.println("Ërror receiving from com port: " + e);
            }
        }
    }

}
