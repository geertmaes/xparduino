package com.cegeka.xparduino.bootstrap.configurator.objectmapper;

public class ObjectMapperConfig {

    private final String eventsPackage;
    private final String commandsPackage;

    public ObjectMapperConfig(String eventsPackage, String commandsPackage) {
        this.eventsPackage = eventsPackage;
        this.commandsPackage = commandsPackage;
    }

    String getEventsPackage() {
        return eventsPackage;
    }

    String getCommandsPackage() {
        return commandsPackage;
    }

}
