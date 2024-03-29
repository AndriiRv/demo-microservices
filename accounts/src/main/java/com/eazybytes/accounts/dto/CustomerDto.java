package com.eazybytes.accounts.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CustomerDto {

    @NotBlank
    private String name;

    @NotBlank
    private String email;

    @NotBlank
    private String mobileNumber;

    private AccountsDto accountsDto;
}
