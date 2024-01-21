package com.eazybytes.loans.service.impl;

import com.eazybytes.loans.dto.LoansDto;
import com.eazybytes.loans.entity.Loans;
import com.eazybytes.loans.exception.ResourceNotFoundException;
import com.eazybytes.loans.mapper.LoansMapper;
import com.eazybytes.loans.repository.LoansRepository;
import com.eazybytes.loans.service.ILoansService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class LoansServiceImpl implements ILoansService {

    private LoansRepository loansRepository;

    @Override
    public void createLoans(LoansDto loansDto) {
        Loans loans = LoansMapper.mapToLoans(loansDto, new Loans());
        Optional<Loans> optionalCustomer = loansRepository.findById(loansDto.getLoanId());

        if (optionalCustomer.isPresent()) {
            throw new RuntimeException("Customer already registered with given id " + loansDto.getLoanId());
        }

        loans.setCreatedAt(LocalDateTime.now());
        loans.setCreatedBy("Anonymous");
        loansRepository.save(loans);
    }

    @Override
    public LoansDto fetchLoans(String mobileNumber) {
        Loans loans = loansRepository.findByMobileNumber(Long.valueOf(mobileNumber))
                .orElseThrow(() -> new ResourceNotFoundException("Loans", "mobileNumber", mobileNumber));
        return LoansMapper.mapToLoansDto(loans, new LoansDto());
    }

    @Override
    public boolean updateLoans(LoansDto loansDto) {
        Loans loans = loansRepository.findById(loansDto.getLoanId()).orElseThrow(
                () -> new ResourceNotFoundException("Loans", "loansId", loansDto.getLoanId().toString())
        );
        LoansMapper.mapToLoans(loansDto, loans);
        loansRepository.save(loans);
        return true;
    }

    @Override
    public boolean deleteLoans(Long loansId) {
        Loans loans = loansRepository.findById(loansId).orElseThrow(
                () -> new ResourceNotFoundException("Loans", "loansId", loansId.toString())
        );
        loansRepository.deleteById(loans.getLoanId());
        return false;
    }
}
