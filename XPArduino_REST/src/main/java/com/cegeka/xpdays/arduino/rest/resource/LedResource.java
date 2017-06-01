package com.cegeka.xpdays.arduino.rest.resource;

import com.cegeka.xpdays.arduino.rest.service.LedService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/led")
@CrossOrigin
public class LedResource {

    private LedService ledService;
    private ObjectMapper objectMapper;

    @Autowired
    public LedResource(LedService ledService, ObjectMapper objectMapper) {
        this.ledService = ledService;
        this.objectMapper = objectMapper;
    }

    @PostMapping("/{action}")
    public void controlLed(@PathVariable String action, @RequestBody(required = false) String body) throws Exception {
        action = action.toLowerCase();
        if("on".equals(action)) {
            ledService.enableLed();
        }else if("off".equals(action)){
            ledService.disableLed();
        }else if("blinkon".equals(action)){
            JsonNode jsonNode = body == null
                    ? new ObjectNode(objectMapper.getNodeFactory())
                    : objectMapper.readTree(body);
            int delay = getInt(jsonNode, "delay", 0);
            int period = getInt(jsonNode, "period", 3);
            TimeUnit timeUnit = TimeUnit.valueOf(getString(jsonNode, "timeunit", "SECONDS"));
            ledService.startBlinkingLed(delay, period, timeUnit);
        }else if("blinkoff".equals(action)){
            ledService.stopBlinkingLed();
        }else {
            throw new Exception("Unknown LED action "+action);
        }
    }

    private int getInt(JsonNode node, String name, int defaultValue){
        JsonNode intNode = node.get(name);
        if(intNode == null){
            return defaultValue;
        }
        return intNode.asInt(defaultValue);
    }

    private String getString(JsonNode node, String name, String defaultValue){
        JsonNode stringNode = node.get(name);
        if(stringNode == null){
            return defaultValue;
        }
        return stringNode.asText(defaultValue);
    }
}
