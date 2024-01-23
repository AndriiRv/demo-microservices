package com.eazybytes.cards.controller;

import com.eazybytes.cards.constants.CardsConstants;
import com.eazybytes.cards.dto.CardsConfigurationSettingsDto;
import com.eazybytes.cards.dto.CardsDto;
import com.eazybytes.cards.dto.ResponseDto;
import com.eazybytes.cards.service.ICardsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "CRUD REST API for Cards in EazyBank",
        description = "CRUD REST APIs in EazyBank to CREATE, UPDATE, FETCH and DELETE card details"
)
@RestController
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
public class CardsController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CardsController.class);

    private final ICardsService iCardsService;
    private final CardsConfigurationSettingsDto cardsConfigurationSettingsDto;

    public CardsController(ICardsService iCardsService, CardsConfigurationSettingsDto cardsConfigurationSettingsDto) {
        this.iCardsService = iCardsService;
        this.cardsConfigurationSettingsDto = cardsConfigurationSettingsDto;
    }

    @Operation(
            summary = "Create Cards REST API",
            description = "REST API to create new Cards inside EazyBank"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP status CREATED"
    )
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createCards(@Validated @RequestBody CardsDto loansDto) {
        iCardsService.createCards(loansDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(CardsConstants.STATUS_201, CardsConstants.MESSAGE_201));
    }

    @Operation(
            summary = "Fetch Cards Details REST API",
            description = "REST API to fetch Cards details based on a mobile number"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP status OK"
    )
    @GetMapping("/fetch")
    public ResponseEntity<CardsDto> fetchCardsDetails(@RequestHeader(value = "eazybank-correlation-id") String correlationId,
                                                      @RequestParam String mobileNumber) {
        LOGGER.debug("eazyBank-correlation-id found: {}", correlationId);

        CardsDto customerDto = iCardsService.fetchCards(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK).body(customerDto);
    }

    @Operation(
            summary = "Update Cards Details REST API",
            description = "REST API to update Cards details based on a card id"
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
    public ResponseEntity<ResponseDto> updateCardsDetails(@Validated @RequestBody CardsDto customerDto) {
        boolean isUpdated = iCardsService.updateCards(customerDto);
        if (isUpdated) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDto(CardsConstants.STATUS_200, CardsConstants.MESSAGE_200));
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDto(CardsConstants.STATUS_500, CardsConstants.MESSAGE_500));
        }
    }

    @Operation(
            summary = "Delete Cards Details REST API",
            description = "REST API to delete Cards details based on a card id"
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
    public ResponseEntity<ResponseDto> deleteCardsDetails(@RequestParam Long cardId) {
        boolean isDeleted = iCardsService.deleteCards(cardId);
        if (isDeleted) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDto(CardsConstants.STATUS_200, CardsConstants.MESSAGE_200));
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDto(CardsConstants.STATUS_500, CardsConstants.MESSAGE_500));
        }
    }

    @Operation(
            summary = "Greetings Cards Details REST API",
            description = "REST API to show greetings"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP status OK"
    )
    @GetMapping("/greetings")
    public ResponseEntity<String> greetings() {
        return ResponseEntity.status(HttpStatus.OK).body(cardsConfigurationSettingsDto.getGreetingsMessage());
    }
}
