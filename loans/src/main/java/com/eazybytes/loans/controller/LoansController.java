package com.eazybytes.loans.controller;

import com.eazybytes.loans.dto.ResponseDto;
import com.eazybytes.loans.constants.LoansConstants;
import com.eazybytes.loans.dto.LoansDto;
import com.eazybytes.loans.service.ILoansService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "CRUD REST API for Loans in EazyBank",
        description = "CRUD REST APIs in EazyBank to CREATE, UPDATE, FETCH and DELETE loans details"
)
@RestController
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
public class LoansController {

    private final ILoansService iLoansService;

    @Value("${greetings-message:#{null}}")
    private String greetingsMessage;

    public LoansController(ILoansService iLoansService) {
        this.iLoansService = iLoansService;
    }

    @Operation(
            summary = "Create Loans REST API",
            description = "REST API to create new Loans inside EazyBank"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP status CREATED"
    )
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createLoans(@Validated @RequestBody LoansDto loansDto) {
        iLoansService.createLoans(loansDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(LoansConstants.STATUS_201, LoansConstants.MESSAGE_201));
    }

    @Operation(
            summary = "Fetch Loans Details REST API",
            description = "REST API to fetch Loans details based on a loan id"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP status OK"
    )
    @GetMapping("/fetch")
    public ResponseEntity<LoansDto> fetchLoansDetails(@RequestParam Long loansId) {
        LoansDto customerDto = iLoansService.fetchLoans(loansId);
        return ResponseEntity.status(HttpStatus.OK).body(customerDto);
    }

    @Operation(
            summary = "Update Loans Details REST API",
            description = "REST API to update Loans details based on a loan id"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP status Internal Server Error"
            )
    })
    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateLoansDetails(@Validated @RequestBody LoansDto customerDto) {
        boolean isUpdated = iLoansService.updateLoans(customerDto);
        if (isUpdated) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDto(LoansConstants.STATUS_200, LoansConstants.MESSAGE_200));
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDto(LoansConstants.STATUS_500, LoansConstants.MESSAGE_500));
        }
    }

    @Operation(
            summary = "Delete Loans Details REST API",
            description = "REST API to delete Loans details based on a loan id"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP status Internal Server Error"
            )
    })
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteLoansDetails(@RequestParam Long cardId) {
        boolean isDeleted = iLoansService.deleteLoans(cardId);
        if (isDeleted) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDto(LoansConstants.STATUS_200, LoansConstants.MESSAGE_200));
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDto(LoansConstants.STATUS_500, LoansConstants.MESSAGE_500));
        }
    }

    @Operation(
            summary = "Greetings Loans Details REST API",
            description = "REST API to show greetings"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP status OK"
    )
    @GetMapping("/greetings")
    public ResponseEntity<String> greetings() {
        return ResponseEntity.status(HttpStatus.OK).body(greetingsMessage);
    }
}
