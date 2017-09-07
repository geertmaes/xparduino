import com.cegeka.xparduino.Arduino;
import com.cegeka.xparduino.ArduinoFactory;
import com.cegeka.xparduino.bootstrap.ArduinoConfiguration;
import com.cegeka.xparduino.bootstrap.configurator.component.ComponentConfig;
import com.cegeka.xparduino.queue.serialport.SerialPortQueueConfig;
import com.cegeka.xparduino.state.component.impl.ObstacleSensorState;
import com.cegeka.xparduino.state.component.impl.TrackSwitchState;
import jssc.SerialPort;
import jssc.SerialPortList;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.cegeka.xparduino.component.ComponentPin.*;
import static com.cegeka.xparduino.component.ComponentType.*;


public class Application {

    public static void main(String[] args) throws Exception {
        new Application().run();
    }

    private void run() throws Exception {
        List<SerialPort> availablePorts = scanAvailablePorts();
        SerialPort selectedPort = availablePorts.get(0);

        ArduinoConfiguration configuration = ArduinoConfiguration.builder()
                .withArduinoQueue(new SerialPortQueueConfig(selectedPort.getPortName()))
                .withComponents(new ComponentConfig.Builder()
                        .withComponent(DIGITAL_2, OBSTACLE_SENSOR)
                        .withComponent(DIGITAL_3, OBSTACLE_SENSOR)
                        .withComponent(DIGITAL_4, BASE_LED)
                        .withComponent(DIGITAL_5, BASE_LED)
                        .withComponent(DIGITAL_10, RFID_READER)
                        .withComponent(DIGITAL_12, RFID_READER)
                        .withComponent(ANALOG_1, TRACK_SWITCH)
                        .withComponent(ANALOG_2, TRACK_SWITCH)
                        .withComponent(ANALOG_5, INFRARED_EMITTER)
                        .build())
                .build();

        Arduino arduino = new ArduinoFactory().create(configuration);

        ObstacleSensorState obstacleSensor = arduino.getState(DIGITAL_2, ObstacleSensorState.class);
        ObstacleSensorState obstacleSensor2 = arduino.getState(DIGITAL_3, ObstacleSensorState.class);
        TrackSwitchState trackSwitchState = arduino.getState(ANALOG_1, TrackSwitchState.class);
/*
        StateChangeListener<ObstacleSensorState> switchTrackOnChange
                = withTimeout(sensorState -> toggleTrackSwitch(arduino, sensorState, trackSwitchState), 2000);

        obstacleSensor.onStateChange(
                withDifferent(switchTrackOnChange));
        obstacleSensor2.onStateChange(
                withDifferent(switchTrackOnChange));*/
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
