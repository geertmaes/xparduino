package com.cegeka.xparduino.event;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface EventMapping {

    EventCode code();

    Class<? extends EventDataSerializer<? extends Event>> serializer();

    Class<? extends EventDataDeserializer<? extends Event>> deserializer();
}
