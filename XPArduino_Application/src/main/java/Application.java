import com.cegeka.xpdays.arduino.Arduino;
import com.cegeka.xpdays.arduino.ArduinoFactory;
import com.cegeka.xpdays.arduino.component.ComponentType;
import com.cegeka.xpdays.arduino.configuration.ArduinoConfiguration;
import com.cegeka.xpdays.arduino.event.impl.BaseLedEvent;
import com.cegeka.xpdays.arduino.listener.DynamicEventListener;
import com.cegeka.xpdays.arduino.state.impl.BaseLedState;
import jssc.SerialPort;
import jssc.SerialPortList;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
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
        System.out.println(System.in.available());
        List<SerialPort> availablePorts = scanAvailablePorts();
        System.out.println("Available ports: \n" + toString(availablePorts));
        SerialPort selectedPort = availablePorts.get(0);

        ArduinoConfiguration configuration = ArduinoConfiguration.builder()
                .withPortName(selectedPort.getPortName())
                .withComponent(8, ComponentType.BASE_LED)
                .withComponent(9, ComponentType.BASE_LED)
                .build();

        Arduino arduino = ArduinoFactory.create(configuration);
        arduino.registerDynamicListener(event -> {
            if (event.getPin() == 8) {
                arduino.baseLed(9)
                        .withEmitting(event.isEmitting())
                        .execute();
            }
        }, BaseLedEvent.class);

        arduino.baseLedBlink(8)
                .withPeriod(50)
                .withTimeUnit(TimeUnit.MILLISECONDS)
                .execute();

        BaseLedState state = arduino.getState(8, BaseLedState.class);

        System.out.println(state.isEmitting());
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
