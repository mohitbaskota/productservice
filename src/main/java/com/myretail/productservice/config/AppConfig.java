package com.myretail.productservice.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "app")
public class AppConfig {

    private String redskyKey;
    private String redskyUrl;

    public AppConfig() {
    }

    public String getRedskyKey() {
        return redskyKey;
    }

    public void setRedskyKey(String redskyKey) {
        this.redskyKey = redskyKey;
    }

    public String getRedskyUrl() {
        return redskyUrl;
    }

    public void setRedskyUrl(String redskyUrl) {
        this.redskyUrl = redskyUrl;
    }
}
