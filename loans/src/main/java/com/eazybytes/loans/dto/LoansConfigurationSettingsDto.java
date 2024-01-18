package com.eazybytes.loans.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(value = "microservice-config")
@Getter
@Setter
public class LoansConfigurationSettingsDto {

    private String greetingsMessage;
}
