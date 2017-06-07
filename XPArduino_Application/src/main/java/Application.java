import com.cegeka.xpdays.arduino.Arduino;
import com.cegeka.xpdays.arduino.ArduinoConfiguration;
import com.cegeka.xpdays.arduino.ArduinoFactory;
import com.cegeka.xpdays.arduino.model.Direction;
import com.cegeka.xpdays.arduino.state.change.OnlyOnceStateChangeListener;
import com.cegeka.xpdays.arduino.state.change.StateChangeListener;
import com.cegeka.xpdays.arduino.state.change.TimeoutStateChangeListener;
import com.cegeka.xpdays.arduino.state.impl.BaseLedState;
import com.cegeka.xpdays.arduino.state.impl.ObstacleSensorState;
import com.cegeka.xpdays.arduino.state.impl.RfidReaderState;
import com.cegeka.xpdays.arduino.state.impl.TrackSwitchState;
import jssc.SerialPort;
import jssc.SerialPortList;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.cegeka.xpdays.arduino.component.ComponentType.*;
import static com.cegeka.xpdays.arduino.state.change.DifferentStateChangeListener.onDifferent;
import static com.cegeka.xpdays.arduino.state.change.OnlyOnceStateChangeListener.onlyOnce;
import static com.cegeka.xpdays.arduino.state.change.TimeoutStateChangeListener.onTimeout;


public class Application {

    public static void main(String[] args) throws Exception {
        new Application().run();
    }

    private void run() throws Exception {
        List<SerialPort> availablePorts = scanAvailablePorts();
        SerialPort selectedPort = availablePorts.get(0);

        ArduinoConfiguration configuration = ArduinoConfiguration.builder()
                .withPortName(selectedPort.getPortName())
                .withComponent(2, OBSTACLE_SENSOR)
                .withComponent(3, OBSTACLE_SENSOR)
                .withComponent(4, BASE_LED)
                .withComponent(5, BASE_LED)
                .withComponent(10, RFID_READER)
                .withComponent(12, RFID_READER)
                .withComponent(15, TRACK_SWITCH)
                .withComponent(16, TRACK_SWITCH)
                .withComponent(19, INFRARED_EMITTER)
                .build();

        Arduino arduino = ArduinoFactory.create(configuration);

        ObstacleSensorState obstacleSensor = arduino.getState(2, ObstacleSensorState.class);
        ObstacleSensorState obstacleSensor2 = arduino.getState(3, ObstacleSensorState.class);

        final boolean[] left = {false};

        StateChangeListener<ObstacleSensorState> timeoutChangeListener
                = onTimeout(state -> {
            if (state.isBlocked()) {
                left[0] = !left[0];
                arduino.trackSwitch(15)
                        .withDirection(left[0] ? Direction.LEFT : Direction.RIGHT)
                        .execute();
            } ;
        }, 2000);

        obstacleSensor.onStateChange(
                onDifferent(timeoutChangeListener));
        obstacleSensor2.onStateChange(
                onDifferent(timeoutChangeListener));
    }

    private static List<SerialPort> scanAvailablePorts() {
        return Arrays.stream(SerialPortList.getPortNames())
                .map(SerialPort::new)
                .collect(Collectors.toList());
    }
}
