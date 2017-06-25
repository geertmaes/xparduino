package com.cegeka.xparduino.event.deserialize;

import com.cegeka.xparduino.event.Event;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface EventDeserializerMapping {

    Class<? extends Event> value();
}
