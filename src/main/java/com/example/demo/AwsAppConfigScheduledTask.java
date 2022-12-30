package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.UnsupportedEncodingException;

@Slf4j
@Configuration
@EnableScheduling
public class AwsAppConfigScheduledTask {

    @Autowired
    private FeatureProperties featureProperties;

    @Autowired
    private AwsAppConfiguration appConfiguration;

    @Scheduled(fixedRate = 5000)
    public void pollConfiguration() throws UnsupportedEncodingException {
        log.info("polls configuration from aws app config");
        JSONObject externalizedConfig = appConfiguration.getConfiguration();
        featureProperties.setEnabled(externalizedConfig.getBoolean("enabled"));
        featureProperties.setLimit(externalizedConfig.getInt("limit"));
    }

}