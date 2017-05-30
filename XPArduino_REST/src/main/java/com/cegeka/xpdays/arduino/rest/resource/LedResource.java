package com.cegeka.xpdays.arduino.rest.resource;

import com.cegeka.xpdays.arduino.rest.service.LedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/led")
@CrossOrigin
public class LedResource {

    @Autowired
    private LedService ledService;

    @PostMapping("/{action}")
    public void controlLed(@PathVariable String action) throws Exception {
        action = action.toLowerCase();
        if("on".equals(action)) {
            ledService.enableLed();
        }else if("off".equals(action)){
            ledService.disableLed();
        }else if("blinkon".equals(action)){
            ledService.startBlinkingLed(0, 3, TimeUnit.SECONDS);
        }else if("blinkoff".equals(action)){
            ledService.stopBlinkingLed();
        }else {
            throw new Exception("Unknown LED action "+action);
        }
    }

}
