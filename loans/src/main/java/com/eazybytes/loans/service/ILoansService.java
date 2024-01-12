package com.eazybytes.loans.service;

import com.eazybytes.loans.dto.LoansDto;

public interface ILoansService {

    void createLoans(LoansDto customerDto);

    LoansDto fetchLoans(Long loanId);

    boolean updateLoans(LoansDto customerDto);

    boolean deleteLoans(Long loanId);
}
