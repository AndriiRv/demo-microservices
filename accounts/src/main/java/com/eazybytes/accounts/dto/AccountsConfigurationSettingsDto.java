package com.eazybytes.accounts.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(value = "microservice-config")
@Getter
@Setter
public class AccountsConfigurationSettingsDto {

    private String greetingsMessage;
}
