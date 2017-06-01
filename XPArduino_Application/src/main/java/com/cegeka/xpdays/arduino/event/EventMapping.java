package com.cegeka.xpdays.arduino.event;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface EventMapping {

    EventCode value();

    Class<? extends EventDeserializer> mapper() default DefaultEventDeserializer.class;
}
