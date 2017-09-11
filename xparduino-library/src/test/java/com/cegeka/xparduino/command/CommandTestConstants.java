package com.cegeka.xparduino.command;

import com.cegeka.xparduino.command.impl.baseled.BaseLedCommand;
import com.cegeka.xparduino.command.impl.trackswitch.TrackSwitchCommand;
import com.cegeka.xparduino.command.serialized.SerializedCommand;
import com.cegeka.xparduino.domain.Direction;

import static com.cegeka.xparduino.command.CommandCode.BASE_LED_COMMAND;
import static com.cegeka.xparduino.command.CommandCode.TRACK_SWITCH_COMMAND;
import static com.cegeka.xparduino.component.ComponentPin.DIGITAL_0;
import static com.cegeka.xparduino.component.ComponentTestConstants.baseLed;
import static com.cegeka.xparduino.component.ComponentTestConstants.trackSwitch;

public class CommandTestConstants {

    public static class BaseLed {

        static String EMITTING = "ON";
        static String NOT_EMITTING = "OFF";

        public static BaseLedCommand baseLedCommand(boolean emitting) {
            return new BaseLedCommand(DIGITAL_0, emitting);
        }

        public static SerializedCommand serializedEmittingBaseLedCommand() {
            return new SerializedCommand(BASE_LED_COMMAND, EMITTING, baseLed(DIGITAL_0));
        }

        public static SerializedCommand serializedNotEmittingBaseLedCommand() {
            return new SerializedCommand(BASE_LED_COMMAND, NOT_EMITTING, baseLed(DIGITAL_0));
        }

    }

    public static class TrackSwitch {

        static String DIRECTION_LEFT = "LEFT";
        static String DIRECTION_RIGHT = "RIGHT";

        public static TrackSwitchCommand trackSwitchCommand(Direction direction) {
            return new TrackSwitchCommand(DIGITAL_0, direction);
        }

        public static SerializedCommand serializedLeftTrackSwitchCommand() {
            return new SerializedCommand(TRACK_SWITCH_COMMAND, DIRECTION_LEFT, trackSwitch(DIGITAL_0));
        }

        public static SerializedCommand serializedRightTrackSwitchCommand() {
            return new SerializedCommand(TRACK_SWITCH_COMMAND, DIRECTION_RIGHT, trackSwitch(DIGITAL_0));
        }

    }

}
