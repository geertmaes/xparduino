package com.cegeka.xparduino.command;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)
@Target(TYPE)
public @interface CommandMapping {

    CommandCode code();

    Class<? extends CommandDataSerializer<? extends Command>> serializer();

    Class<? extends CommandDataDeserializer<? extends Command>> deserializer();

}
