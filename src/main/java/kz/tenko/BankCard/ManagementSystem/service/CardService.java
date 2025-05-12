package kz.tenko.BankCard.ManagementSystem.service;

import kz.tenko.BankCard.ManagementSystem.DTO.FindCardsRequestDTO;
import kz.tenko.BankCard.ManagementSystem.entity.Card;

import java.util.List;

public interface CardService {

    void saveCard(Card card);

    List<Card> findCards(FindCardsRequestDTO findCardsRequestDTO);

    void deleteCard(long id);

    void cardBlockingRequest(String cardNumber);

    void transferAmount(String cardFrom, String cardTo, long transferAmount);
}
