import com.cegeka.xpdays.arduino.Arduino;
import com.cegeka.xpdays.arduino.ArduinoFactory;
import com.cegeka.xpdays.arduino.command.impl.InfraredCommand;
import com.cegeka.xpdays.arduino.configuration.ArduinoConfiguration;
import jssc.SerialPort;
import jssc.SerialPortList;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.cegeka.xpdays.arduino.component.ComponentType.*;


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
                .withComponent(2, INFRARED_EMITTER)
                .withComponent(3, OBSTACLE_SENSOR)
                .withComponent(4, SWITCH)
                .withComponent(8, BASE_LED)
                .withComponent(9, BASE_LED)
                .withComponent(14, PHOTO_SENSOR)
                .build();

        Arduino arduino = ArduinoFactory.create(configuration);
        arduino.registerListener(new DemoListener(arduino));
    }

    private static List<SerialPort> scanAvailablePorts() {
        return Arrays.stream(SerialPortList.getPortNames())
                .map(SerialPort::new)
                .collect(Collectors.toList());
    }
}
