import com.cegeka.xpdays.arduino.Arduino;
import com.cegeka.xpdays.arduino.ArduinoFactory;
import com.cegeka.xpdays.arduino.component.ComponentType;
import com.cegeka.xpdays.arduino.configuration.ArduinoConfiguration;
import jssc.SerialPort;
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

    public Application() {

    }

    public void run() throws Exception {
        Scanner scanner = new Scanner(System.in);

        List<SerialPort> availablePorts = scanAvailablePorts();
        System.out.println("Available ports: \n" + toString(availablePorts));
        SerialPort selectedPort = availablePorts.get(scanner.nextInt());

        ArduinoConfiguration configuration = ArduinoConfiguration.builder()
                .withPortName(selectedPort.getPortName())
                .withComponent(8, ComponentType.BASE_LED)
                .build();

        Arduino arduino = ArduinoFactory.create(configuration);

        arduino.baseLedBlink(8)
                .withPeriod(3)
                .execute();
    }

    private String toString(List<SerialPort> ports) {
        return IntStream.range(0, ports.size())
                .mapToObj(index -> index + ") " + ports.get(index).getPortName())
                .collect(Collectors.joining(","));
    }


    private static List<SerialPort> scanAvailablePorts() {
        return Arrays.stream(SerialPortList.getPortNames())
                .map(SerialPort::new)
                .collect(Collectors.toList());
    }
}
