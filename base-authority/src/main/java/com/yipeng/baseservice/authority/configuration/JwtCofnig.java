package com.yipeng.baseservice.authority.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@ConfigurationProperties(prefix = "jwt")
@Component
public class JwtCofnig {

    private String issuer;
    private String base64Secret;
    private int expiresSecond;
}