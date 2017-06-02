package com.cegeka.xpdays.arduino.rest.resource;

import com.cegeka.xpdays.arduino.rest.service.SwitchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/switch")
@CrossOrigin
public class SwitchResource {

    private SwitchService switchService;

    @Autowired
    public SwitchResource(SwitchService switchService) {
        this.switchService = switchService;
    }


    @PostMapping
    public void switchTracks() throws Exception {
        switchService.switchTracks();
    }

}
