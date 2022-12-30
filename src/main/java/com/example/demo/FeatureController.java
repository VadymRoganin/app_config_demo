package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/feature")
public class FeatureController {

    @Autowired
    private Boolean isEnabled;

    @Autowired
    private FeatureProperties featureProperties;

    @GetMapping("/isEnabled2")
    public boolean isEnabled2() {
//        boolean isEnabled = isEnabled;
        String message = "Feature is " + (isEnabled ? "enabled" : "disabled");
        log.info(message);
        return isEnabled;
    }

    @GetMapping("/isEnabled")
    public boolean isEnabled() {
        boolean isEnabled = featureProperties.isEnabled();
        String message = "Feature is " + (isEnabled ? "enabled" : "disabled");
        log.info(message);
        return isEnabled;
    }

    @GetMapping("/limit")
    public int getLimit() {
        int limit = featureProperties.getLimit();
        String message = "Feature limit is " + limit;
        log.info(message);
        return limit;
    }
}