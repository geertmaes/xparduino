import com.cegeka.xparduino.Arduino;
import com.cegeka.xparduino.configuration.ArduinoConfiguration;
import com.cegeka.xparduino.queue.serialport.SerialPortQueueConfiguration;
import com.cegeka.xparduino.state.change.StateChangeListener;
import com.cegeka.xparduino.state.component.impl.ObstacleSensorState;
import com.cegeka.xparduino.state.component.impl.TrackSwitchState;
import jssc.SerialPort;
import jssc.SerialPortList;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.cegeka.xparduino.component.ComponentType.*;
import static com.cegeka.xparduino.state.change.DifferentStateChangeDecorator.withDifferent;
import static com.cegeka.xparduino.state.change.TimeoutStateChangeDecorator.withTimeout;


public class Application {

    public static void main(String[] args) throws Exception {
        new Application().run();
    }

    private void run() throws Exception {
        List<SerialPort> availablePorts = scanAvailablePorts();
        SerialPort selectedPort = availablePorts.get(0);

        ArduinoConfiguration configuration = ArduinoConfiguration.builder()
                .withQueueConfiguration(new SerialPortQueueConfiguration(selectedPort.getPortName()))
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

        Arduino arduino = Arduino.create(configuration);

        ObstacleSensorState obstacleSensor = arduino.getState(2, ObstacleSensorState.class);
        ObstacleSensorState obstacleSensor2 = arduino.getState(3, ObstacleSensorState.class);
        TrackSwitchState trackSwitchState = arduino.getState(15, TrackSwitchState.class);

        StateChangeListener<ObstacleSensorState> switchTrackOnChange
                = withTimeout(sensorState -> toggleTrackSwitch(arduino, sensorState, trackSwitchState), 2000);

        obstacleSensor.onStateChange(
                withDifferent(switchTrackOnChange));
        obstacleSensor2.onStateChange(
                withDifferent(switchTrackOnChange));
    }

    private void toggleTrackSwitch(Arduino arduino, ObstacleSensorState state, TrackSwitchState trackSwitchState) {
        if (state.isBlocked()) {
            arduino.trackSwitch(trackSwitchState.getPin())
                    .withDirection(trackSwitchState.getDirection().toggle())
                    .execute();
        }
    }

    private static List<SerialPort> scanAvailablePorts() {
        return Arrays.stream(SerialPortList.getPortNames())
                .map(SerialPort::new)
                .collect(Collectors.toList());
    }
}
