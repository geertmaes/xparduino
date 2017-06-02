import com.cegeka.xpdays.arduino.Arduino;
import com.cegeka.xpdays.arduino.event.impl.ObstacleSensorEvent;
import com.cegeka.xpdays.arduino.listener.BehaviourListener;

public class DemoListener extends BehaviourListener {

    public DemoListener(Arduino arduino) {
        super(arduino);
    }

    public void on(ObstacleSensorEvent event) {
        if (event.isBlocked()) {
            arduino().baseLed(9)
                    .withEmitting(true)
                    .execute();
            arduino().baseLed(8)
                    .withEmitting(false)
                    .execute();
        } else {
            arduino().baseLed(9)
                    .withEmitting(false)
                    .execute();
            arduino().baseLed(8)
                    .withEmitting(true)
                    .execute();
        }
    }
}
