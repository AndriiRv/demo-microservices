package com.eazybytes.cards.service;

import com.eazybytes.cards.dto.CardsDto;

public interface ICardsService {

    void createCards(CardsDto cardsDto);

    CardsDto fetchCards(String mobileNumber);

    boolean updateCards(CardsDto cardsDto);

    boolean deleteCards(Long cardId);
}
