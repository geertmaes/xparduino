package com.cegeka.xpdays.arduino.event.deserialiser;

import com.cegeka.xpdays.arduino.event.Event;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface EventDeserializerMapping {

    Class<? extends Event> value();
}
