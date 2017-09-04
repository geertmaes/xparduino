package com.cegeka.xparduino.event;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)
@Target(TYPE)
public @interface EventMapping {

    EventCode code();

    Class<? extends EventDataSerializer<? extends Event>> serializer();

    Class<? extends EventDataDeserializer<? extends Event>> deserializer();
}
