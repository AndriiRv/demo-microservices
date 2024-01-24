package com.eazybytes.accounts.service.client;

import com.eazybytes.accounts.dto.CardsDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class CardsFallback implements CardsFeignClient {

    /**
     * Fallback 'fetchCardsDetails' method which return 'null' value if Cards microservice is not responds correctly.
     *
     * @param correlationId
     * @param mobileNumber
     * @return
     */
    @Override
    public ResponseEntity<CardsDto> fetchCardsDetails(String correlationId, String mobileNumber) {
        return null;
    }
}
