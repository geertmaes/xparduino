package com.cegeka.xpdays.arduino.rest.resource;

import com.cegeka.xpdays.arduino.rest.service.ArduinoService;
import com.cegeka.xpdays.arduino.rest.service.SerialPortService;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/port")
@CrossOrigin
public class SerialPortResource {

    @Autowired
    private ArduinoService arduinoService;

    @Autowired
    private SerialPortService serialPortService;

    @GetMapping
    public Collection<String> getPorts() {
        return serialPortService.getPortNames();
    }

    @PostMapping("/open")
    public void openPort(@RequestBody JsonNode port){
        arduinoService.openArduinoPort(port.get("port").textValue());
    }

    @PostMapping("/close")
    public void closePort(){
        arduinoService.closeArduinoPort();
    }
}
