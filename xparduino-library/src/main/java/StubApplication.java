import com.cegeka.xparduino.Arduino;
import com.cegeka.xparduino.ArduinoFactory;
import com.cegeka.xparduino.bootstrap.ArduinoConfiguration;
import com.cegeka.xparduino.bootstrap.configurator.component.ComponentConfig;
import com.cegeka.xparduino.queue.stub.StubQueueConfig;
import com.cegeka.xparduino.state.component.impl.BaseLedState;

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
                        .withComponent(2, OBSTACLE_SENSOR)
                        .withComponent(3, OBSTACLE_SENSOR)
                        .withComponent(4, BASE_LED)
                        .withComponent(5, BASE_LED)
                        .withComponent(10, RFID_READER)
                        .withComponent(12, RFID_READER)
                        .withComponent(15, TRACK_SWITCH)
                        .withComponent(16, TRACK_SWITCH)
                        .withComponent(19, INFRARED_EMITTER)
                        .build())
                .build();

        Arduino arduino = arduinoFactory.create(configuration);
        BaseLedState ledState = arduino.getState(4, BaseLedState.class);

        arduino.baseLed(4)
                .withEmitting(true)
                .execute();

//        ledState.onStateChange(state -> arduino.baseLed(4)
//                .withEmitting(!state.isEmitting())
//                .execute());
    }
}
