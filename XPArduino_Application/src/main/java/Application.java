import jssc.SerialPort;
import jssc.SerialPortException;
import jssc.SerialPortList;

import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collector;
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

        InputStream input = selectedPort.get();
        output = serialPort.getOutputStream();
        writeData(0, 0);

        successful = true;
        return successful;

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
