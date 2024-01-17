package com.eazybytes.loans.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class LoansConfigurationSettingsDto {

    @Value("${greetings-message:#{null}}")
    private String value;
}
