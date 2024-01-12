package com.eazybytes.accounts.dto;

import jakarta.validation.Valid;
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

    @Valid
    private AccountsDto accountsDto;
}
