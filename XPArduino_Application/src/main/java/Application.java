import com.cegeka.xpdays.arduino.Arduino;
import com.cegeka.xpdays.arduino.ArduinoFactory;
import com.cegeka.xpdays.arduino.configuration.ArduinoConfiguration;
import jssc.SerialPort;
import jssc.SerialPortList;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.cegeka.xpdays.arduino.component.ComponentType.BASE_LED;
import static com.cegeka.xpdays.arduino.component.ComponentType.OBSTACLE_SENSOR;
import static com.cegeka.xpdays.arduino.component.ComponentType.PHOTO_SENSOR;


public class Application {


    public static void main(String[] args) throws Exception {
        new Application().run();
    }

    public Application() {

    }

    public void run() throws Exception {
        List<SerialPort> availablePorts = scanAvailablePorts();
        SerialPort selectedPort = availablePorts.get(0);

        ArduinoConfiguration configuration = ArduinoConfiguration.builder()
                .withPortName(selectedPort.getPortName())
                .withComponent(8, BASE_LED)
                .withComponent(9, BASE_LED)
                .withComponent(14, PHOTO_SENSOR)
                .withComponent(3, OBSTACLE_SENSOR)
                .build();

        Arduino arduino = ArduinoFactory.create(configuration);
        arduino.registerEventListener(new DemoListener(arduino));
    }

    private static List<SerialPort> scanAvailablePorts() {
        return Arrays.stream(SerialPortList.getPortNames())
                .map(SerialPort::new)
                .collect(Collectors.toList());
    }
}
