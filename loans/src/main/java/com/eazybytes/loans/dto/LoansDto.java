package com.eazybytes.loans.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LoansDto {

    private Long loanId;

    @NotNull
    private Long mobileNumber;

    @NotBlank
    private String loanNumber;

    @NotBlank
    private String loanType;

    @NotNull
    private Long totalLoan;

    @NotNull
    private Long amountPaid;

    @NotNull
    private Long outstandingAmount;
}
