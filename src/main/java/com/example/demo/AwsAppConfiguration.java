package com.example.demo;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.appconfig.AmazonAppConfig;
import com.amazonaws.services.appconfig.AmazonAppConfigClient;
import com.amazonaws.services.appconfig.model.GetConfigurationRequest;
import com.amazonaws.services.appconfig.model.GetConfigurationResult;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.context.annotation.Configuration;

import java.io.UnsupportedEncodingException;
import java.util.Objects;

@Slf4j
@Configuration
public class AwsAppConfiguration {


    private final AmazonAppConfig appConfig;
    private final GetConfigurationRequest request;

    public AwsAppConfiguration() {
        appConfig = AmazonAppConfigClient.builder().withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials("AKIA36N5DFJHFELTBQWC",
                "XSrksaSU3Vd8AYJvmSqkPPvPUi2w84SeUeX91yfF"))).build();
        request = new GetConfigurationRequest();
        request.setClientId("clientId");
        request.setApplication("FeatureProperties");
        request.setConfiguration("FeaturePropertiesProfile1");
        request.setEnvironment("dev");
    }




    public JSONObject getConfiguration() throws UnsupportedEncodingException {
        GetConfigurationResult result = appConfig.getConfiguration(request);
        String message = String.format("contentType: %s", result.getContentType());
        log.info(message);

        if (!Objects.equals("application/json", result.getContentType())) {
            throw new IllegalStateException("config is expected to be JSON");
        }

        String content = new String(result.getContent().array(), "ASCII");
        return new JSONObject(content).getJSONObject("feature");
    }
}