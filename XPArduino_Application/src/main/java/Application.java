import com.cegeka.xpdays.arduino.Arduino;
import com.cegeka.xpdays.arduino.ArduinoConfiguration;
import com.cegeka.xpdays.arduino.ArduinoFactory;
import com.cegeka.xpdays.arduino.state.impl.BaseLedState;
import com.cegeka.xpdays.arduino.state.impl.ObstacleSensorState;
import jssc.SerialPort;
import jssc.SerialPortList;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.cegeka.xpdays.arduino.component.ComponentType.*;
import static com.cegeka.xpdays.arduino.state.change.DifferentStateChangeListener.onDifferent;


public class Application {

    public static void main(String[] args) throws Exception {
        new Application().run();
    }

    private void run() throws Exception {
        List<SerialPort> availablePorts = scanAvailablePorts();
        SerialPort selectedPort = availablePorts.get(1);

        ArduinoConfiguration configuration = ArduinoConfiguration.builder()
                .withPortName(selectedPort.getPortName())
                .withComponent(2, INFRARED_EMITTER)
                .withComponent(3, OBSTACLE_SENSOR)
                .withComponent(4, TRACK_SWITCH)
                .withComponent(8, BASE_LED)
                .withComponent(9, BASE_LED)
                .withComponent(14, PHOTO_SENSOR)
                .build();

        Arduino arduino = ArduinoFactory.create(configuration);

        ObstacleSensorState obstacleSensor = arduino.getState(3, ObstacleSensorState.class);
        BaseLedState baseLed = arduino.getState(9, BaseLedState.class);

        obstacleSensor.onStateChange(
                onDifferent(state -> arduino.baseLed(9)
                        .withEmitting(state.isBlocked())
                        .execute()));

        baseLed.onStateChange(
                onDifferent(state -> arduino.baseLed(8)
                        .withEmitting(state.isEmitting())
                        .execute()));
    }

    private static List<SerialPort> scanAvailablePorts() {
        return Arrays.stream(SerialPortList.getPortNames())
                .map(SerialPort::new)
                .collect(Collectors.toList());
    }
}
