package com.cegeka.xpdays.arduino.rest.service;

import jssc.SerialPortList;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;

import static java.util.stream.Collectors.toSet;

@Service
public class SerialPortService {

    public Collection<String> getPortNames(){
        return Arrays.stream(SerialPortList.getPortNames()).collect(toSet());
    }
}
