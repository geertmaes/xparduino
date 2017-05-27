import com.cegeka.xpdays.arduino.Arduino;
import com.cegeka.xpdays.arduino.command.BaseLEDCommand;
import com.cegeka.xpdays.arduino.command.Command;
import com.cegeka.xpdays.arduino.monitor.BufferedMessageListener;
import com.cegeka.xpdays.arduino.monitor.SerialMonitor;
import jssc.SerialPort;

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

        List<SerialPort> availablePorts = Arduino.scanAvailablePorts();
        System.out.println("Available ports: \n" + toString(availablePorts));
        SerialPort selectedPort = availablePorts.get(scanner.nextInt());

        Arduino arduino = Arduino.fromSerialPort(selectedPort);
        SerialMonitor monitor = arduino.getMonitor();

        monitor.onMessage(new BufferedMessageListener());

        while (true) {
            int emitting = scanner.nextInt();

            Command command = new BaseLEDCommand()
                    .withEmitting(emitting > 0);

            monitor.send(command);
        }
    }

    private String toString(List<SerialPort> ports) {
        return IntStream.range(0, ports.size())
                .mapToObj(index -> index + ") " + ports.get(index).getPortName())
                .collect(Collectors.joining(","));
    }
}
