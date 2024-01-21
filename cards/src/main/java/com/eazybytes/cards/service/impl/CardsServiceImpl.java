package com.eazybytes.cards.service.impl;

import com.eazybytes.cards.dto.CardsDto;
import com.eazybytes.cards.entity.Cards;
import com.eazybytes.cards.exception.ResourceNotFoundException;
import com.eazybytes.cards.mapper.CardsMapper;
import com.eazybytes.cards.repository.CardsRepository;
import com.eazybytes.cards.service.ICardsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CardsServiceImpl implements ICardsService {

    private CardsRepository cardsRepository;

    @Override
    public void createCards(CardsDto cardsDto) {
        Cards cards = CardsMapper.mapToCards(cardsDto, new Cards());
        Optional<Cards> optionalCards = cardsRepository.findById(cardsDto.getCardId());

        if (optionalCards.isPresent()) {
            throw new RuntimeException("Card already registered with id " + cardsDto.getCardId());
        }

        cards.setCreatedAt(LocalDateTime.now());
        cards.setCreatedBy("Anonymous");
        cardsRepository.save(cards);
    }

    @Override
    public CardsDto fetchCards(String mobileNumber) {
        Cards loans = cardsRepository.findByMobileNumber(Long.valueOf(mobileNumber))
                .orElseThrow(() -> new ResourceNotFoundException("Cards", "mobileNumber", mobileNumber));
        return CardsMapper.mapToCardsDto(loans, new CardsDto());
    }

    @Override
    public boolean updateCards(CardsDto cardsDto) {
        Cards loans = cardsRepository.findById(cardsDto.getCardId()).orElseThrow(
                () -> new ResourceNotFoundException("Cards", "cardId", cardsDto.getCardId().toString())
        );
        CardsMapper.mapToCards(cardsDto, loans);
        cardsRepository.save(loans);
        return true;
    }

    @Override
    public boolean deleteCards(Long cardId) {
        Cards cards = cardsRepository.findById(cardId).orElseThrow(
                () -> new ResourceNotFoundException("Loans", "loansId", cardId.toString())
        );
        cardsRepository.deleteById(cards.getCardId());
        return false;
    }
}
