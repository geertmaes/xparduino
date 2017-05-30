package com.cegeka.xpdays.arduino.rest.service;

import jssc.SerialPort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Collection;

import static com.cegeka.xpdays.arduino.Arduino.scanAvailablePorts;
import static java.util.stream.Collectors.toSet;

@Service
public class SerialPortService {

    public SerialPort getPortByName(@PathVariable String port) {
        return scanAvailablePorts().stream()
                .filter(p -> p.getPortName().equals(port))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Unknown port: "+port));
    }

    public Collection<String> getPortNames(){
        return scanAvailablePorts().stream()
                .map(SerialPort::getPortName)
                .collect(toSet());
    }

}
