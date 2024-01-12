package com.eazybytes.accounts.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AccountsDto {

    private Long accountNumber;

    @NotBlank
    private String accountType;

    @NotBlank
    private String branchAddress;
}
