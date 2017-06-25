import com.cegeka.xparduino.Arduino;
import com.cegeka.xparduino.configuration.ArduinoConfiguration;
import com.cegeka.xparduino.queue.stub.StubQueue;
import com.cegeka.xparduino.queue.stub.StubQueueConfiguration;
import com.cegeka.xparduino.state.component.impl.BaseLedState;

import static com.cegeka.xparduino.component.ComponentType.*;


public class StubApplication {

    public static void main(String[] args) throws Exception {
        new StubApplication().run();
    }

    private void run() throws Exception {
        StubQueueConfiguration queueConfiguration = new StubQueueConfiguration();
        ArduinoConfiguration configuration = ArduinoConfiguration.builder()
                .withQueueConfiguration(queueConfiguration)
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
        BaseLedState ledState = arduino.getState(4, BaseLedState.class);

        StubQueue stubQueue = queueConfiguration.getQueue();

        stubQueue.onCommandCallback((message, queue) -> {
            if (message.equals("<0:4,ON>")) {
                queue.setNextEvent("<0:4,0,true>");
            }
            if (message.equals("<0:4,OFF>")) {
                queue.setNextEvent("<0:4,0,false>");
            }
        });

        arduino.baseLed(4)
                .withEmitting(true)
                .execute();

        ledState.onStateChange(state -> arduino.baseLed(4)
                .withEmitting(!state.isEmitting())
                .execute());


    }
}
