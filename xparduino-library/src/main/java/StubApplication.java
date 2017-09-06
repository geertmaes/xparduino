import com.cegeka.xparduino.Arduino;
import com.cegeka.xparduino.ArduinoFactory;
import com.cegeka.xparduino.bootstrap.ArduinoConfiguration;
import com.cegeka.xparduino.bootstrap.configurator.component.ComponentConfig;
import com.cegeka.xparduino.queue.stub.StubQueueConfig;
import com.cegeka.xparduino.state.component.impl.BaseLedState;

import static com.cegeka.xparduino.component.ComponentPin.*;
import static com.cegeka.xparduino.component.ComponentType.*;


public class StubApplication {

    public static void main(String[] args) throws Exception {
        new StubApplication().run();
    }

    private final ArduinoFactory arduinoFactory = new ArduinoFactory();

    private void run() throws Exception {
        ArduinoConfiguration configuration = ArduinoConfiguration.builder()
                .withArduinoQueue(new StubQueueConfig())
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

        Arduino arduino = arduinoFactory.create(configuration);
        BaseLedState ledState = arduino.getState(DIGITAL_4, BaseLedState.class);

        arduino.baseLed(DIGITAL_4)
                .on()
                .executing().execute();

//        ledState.onStateChange(state -> arduino.baseLed(4)
//                .withEmitting(!state.isEmitting())
//                .execute());
    }
}
