package com.example.demo.common.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:url.properties")
@Data
public class UrlConfig {
    @Value("${airPlane.ip}")
    private  String ariPlaneIP;
    @Value("${airPlane.port}")
    private  String ariPlanePort;
    @Value("${airPlane.url}")
    private  String ariPlaneUrl;
    @Value("${boat.ip}")
    private String boatIP;
    @Value("${boat.port}")
    private String boatPort;
    @Value("${boat.url}")
    private String boatURl;
    @Value("${car.ip}")
    private String carIP;
    @Value("${car.port}")
    private String carPort;
    @Value("${car.url}")
    private String carUrl;

}
