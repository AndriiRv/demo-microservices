package com.eazybytes.message.functions;

import com.eazybytes.message.dto.AccountsMessageDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

@Configuration
public class MessageFunctions {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageFunctions.class);

    @Bean
    public Function<AccountsMessageDto, AccountsMessageDto> email() {
        return accountsMessageDto -> {
            LOGGER.info("Fake sending email with the details: " + accountsMessageDto.toString());
            return accountsMessageDto;
        };
    }

    @Bean
    public Function<AccountsMessageDto, Long> sms() {
        return accountsMessageDto -> {
            LOGGER.info("Fake sending SMS with the details: " + accountsMessageDto.toString());
            return accountsMessageDto.accountNumber();
        };
    }
}
