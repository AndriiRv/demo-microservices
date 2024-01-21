package com.eazybytes.loans.service;

import com.eazybytes.loans.dto.LoansDto;

public interface ILoansService {

    void createLoans(LoansDto customerDto);

    LoansDto fetchLoans(String mobileNumber);

    boolean updateLoans(LoansDto customerDto);

    boolean deleteLoans(Long loanId);
}
