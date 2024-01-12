package com.eazybytes.cards.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "cards")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Cards extends BaseEntity {

    @Id
    @Column(name = "card_id")
    private Long cardId;

    @Column(name = "mobile_number")
    private Long mobileNumber;

    @Column(name = "card_number")
    private String cardNumber;

    @Column(name = "card_type")
    private String cardType;

    @Column(name = "total_limit")
    private Long totalLimit;

    @Column(name = "amount_used")
    private Long amountUsed;

    @Column(name = "available_amount")
    private Long availableAmount;

}
