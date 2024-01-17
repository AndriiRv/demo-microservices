package com.eazybytes.cards.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class CardsConfigurationSettingsDto {

    @Value("${greetings-message:#{null}}")
    private String value;
}
