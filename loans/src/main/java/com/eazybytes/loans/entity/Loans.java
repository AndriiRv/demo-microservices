package com.eazybytes.loans.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "loans")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Loans extends BaseEntity {

    @Id
    @Column(name = "loan_id")
    private Long loanId;

    @Column(name = "mobile_number")
    private Long mobileNumber;

    @Column(name = "loan_number")
    private String loanNumber;

    @Column(name = "loan_type")
    private String loanType;

    @Column(name = "total_loan")
    private Long totalLoan;

    @Column(name = "amount_paid")
    private Long amountPaid;

    @Column(name = "outstanding_amount")
    private Long outstandingAmount;

}
