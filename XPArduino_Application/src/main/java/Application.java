import jssc.SerialPort;
import jssc.SerialPortEventListener;
import jssc.SerialPortList;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class Application {


    public static void main(String[] args) throws Exception {
        new Application().run();
    }

    public Application(){

    }

    public void run() throws Exception{
        Scanner scanner = new Scanner(System.in);

        List<SerialPort> availablePorts = getAvailablePorts();
        System.out.println("Available ports: \n" + toString(availablePorts));
        SerialPort selectedPort = availablePorts.get(scanner.nextInt());

        selectedPort.openPort();
        selectedPort.setParams(SerialPort.BAUDRATE_9600, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
        selectedPort.setFlowControlMode(SerialPort.FLOWCONTROL_RTSCTS_IN | SerialPort.FLOWCONTROL_RTSCTS_OUT);


        PortReader portReader = new PortReader(selectedPort);
        selectedPort.addEventListener(portReader, SerialPort.MASK_RXCHAR);

        Thread.sleep(4000);
        selectedPort.writeString("1!");
        Thread.sleep(4000);

        selectedPort.closePort();
    }

    private String toString(List<SerialPort> ports) {
        return IntStream.range(0, ports.size())
                .mapToObj(index -> index + ") " + ports.get(index).getPortName())
                .collect(Collectors.joining(","));
    }

    private List<SerialPort> getAvailablePorts() {
        return Arrays.stream(SerialPortList.getPortNames())
                .map(SerialPort::new)
                .collect(Collectors.toList());
    }
}
