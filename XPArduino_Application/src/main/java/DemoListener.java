import com.cegeka.xpdays.arduino.Arduino;
import com.cegeka.xpdays.arduino.command.impl.InfraredCommand;
import com.cegeka.xpdays.arduino.common.Direction;
import com.cegeka.xpdays.arduino.event.impl.BaseLedEvent;
import com.cegeka.xpdays.arduino.event.impl.ObstacleSensorEvent;
import com.cegeka.xpdays.arduino.event.impl.PhotoSensorEvent;
import com.cegeka.xpdays.arduino.listener.BehaviourListener;

public class DemoListener extends BehaviourListener {

    private boolean blocked = false;
    private boolean left = false;

    public DemoListener(Arduino arduino) {
        super(arduino);
    }

    private long lastTime = 0;

    public void on(ObstacleSensorEvent event) {
        if (event.isBlocked()) {
            arduino().baseLed(8)
                    .withEmitting(true)
                    .execute();
            arduino().infrared(2)
                    .withColor(InfraredCommand.Color.RED)
                    .withChannel(0)
                    .withSpeed(4)
                    .withTimes(1)
                    .execute();
        } else {
            arduino().infrared(2)
                    .withColor(InfraredCommand.Color.RED)
                    .withChannel(0)
                    .withSpeed(0)
                    .withTimes(1)
                    .execute();
        }
    }}
