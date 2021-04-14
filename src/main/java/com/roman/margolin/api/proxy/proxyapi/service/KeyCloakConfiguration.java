package com.roman.margolin.api.proxy.proxyapi.service;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "key-cloak")
@Data
public class KeyCloakConfiguration {
    private String serverUrl;
    private String realm;
    private String clientId;
    private String clientSecret;
    private String userName;
    private String password;
}
