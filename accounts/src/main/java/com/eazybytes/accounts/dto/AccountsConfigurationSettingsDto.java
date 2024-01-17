package com.eazybytes.accounts.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class AccountsConfigurationSettingsDto {

    @Value("${greetings-message:#{null}}")
    private String value;
}
