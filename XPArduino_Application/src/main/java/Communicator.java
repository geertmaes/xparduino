import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortList;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.HashMap;

public class Communicator implements SerialPortEventListener {
    final static int TIME_OUT = 2000;
    final static int SPACE_ASCII = 32;
    final static int DASH_ASCII = 45;
    final static int NEW_LINE_ASCII = 10;

    private String ports[];
    private HashMap portMap = new HashMap;
    private SerialPortList selectedPortIdentifier = null;
    private SerialPort serialPort = null;
    private InputStream input = null;
    private OutputStream output = null;

    String logText = "";

    public void searchForPorts() {
        ports = SerialPortList.getPortNames();
    }

    @Override
    public void serialEvent(SerialPortEvent serialPortEvent) {

    }
}
