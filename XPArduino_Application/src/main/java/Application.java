import com.cegeka.xpdays.arduino.Arduino;
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

        arduino.baseLedBlink()
                .withDelay(4)
                .withPeriod(5)
                .execute();

        while (true) {
            int emitting = scanner.nextInt();

            arduino
                    .baseLed()
                    .withEmitting(emitting > 0)
                    .execute();
        }
    }

    private String toString(List<SerialPort> ports) {
        return IntStream.range(0, ports.size())
                .mapToObj(index -> index + ") " + ports.get(index).getPortName())
                .collect(Collectors.joining(","));
    }
}
