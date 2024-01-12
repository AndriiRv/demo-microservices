package com.eazybytes.cards.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CardsDto {

    private Long cardId;

    @NotNull
    private Long mobileNumber;

    @NotBlank
    private String cardNumber;

    @NotBlank
    private String cardType;

    @NotNull
    private Long totalLimit;

    @NotNull
    private Long amountUsed;

    @NotNull
    private Long availableAmount;
}
