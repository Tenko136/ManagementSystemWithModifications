package kz.tenko.BankCard.ManagementSystem.service;

import kz.tenko.BankCard.ManagementSystem.DAO.CardDAO;
import kz.tenko.BankCard.ManagementSystem.DAO.UserDAO;
import kz.tenko.BankCard.ManagementSystem.DTO.FindCardsRequestDTO;
import kz.tenko.BankCard.ManagementSystem.entity.Card;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CardServiceImpl implements CardService {

    private final CardDAO cardDAO;
    private final UserDAO userDAO;

    public CardServiceImpl(CardDAO cardDAO, UserDAO userDAO) {
        this.cardDAO = cardDAO;
        this.userDAO = userDAO;
    }

    @Override
    @Transactional
    public void saveCard(Card card) {
        card.setNumber(card.getNumber().replaceAll("\\D", ""));
        if (!card.getNumber().matches("[0-9]{16}")) {
            throw new RuntimeException("Некорректный номер");
        }
        if (card.getNumber() == null || card.getBalance() == null || card.getStatus() == null ||
        card.getUserId() == null || card.getExpirationDate() == null) {
            throw  new RuntimeException("Заполните обязательные поля");
        }
        cardDAO.saveCard(card);
    }

    @Override
    public List<Card> findCards(FindCardsRequestDTO findCardsRequestDTO) {
//        boolean isAdmin = false;
//        for (GrantedAuthority ga : SecurityContextHolder.getContext().getAuthentication().getAuthorities()) {
//            if (ga.getAuthority().endsWith("ADMIN")) {
//                isAdmin = true;
//            }
//        }
//        if (isAdmin) {
//            return cardDAO.findCards(findCardsRequestDTO);
//        }
//        var user = userDAO.findUserByEmail(((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername());
//        return cardDAO.findCards(user.getId());
    return null;
    }

    @Override
    @Transactional
    public void deleteCard(long id) {
        cardDAO.deleteCard(id);
    }

    @Override
    @Transactional
    public void cardBlockingRequest(String cardNumber) {
        if (!cardFilter(cardNumber)) {
            throw new RuntimeException(String.format("Не найдена карта с номером %s у данного пользователя ", cardNumber));
        }
        cardDAO.blockingCard(cardNumber);
    }

    @Override
    @Transactional
    public void transferAmount(String cardFrom, String cardTo, long transferAmount) {

        if (cardFilter(cardFrom) && cardFilter(cardTo)) {
            long from = cardDAO.findBalance(cardFrom);
            if ((from - transferAmount) < 0) {
                throw new RuntimeException("Недостаточно средств");
            }
            cardDAO.changeBalance(cardFrom, -transferAmount);
            cardDAO.changeBalance(cardTo, transferAmount);
            return;
        }
        throw new RuntimeException("Попытка перевода на стороннюю карту");
    }


    public boolean cardFilter(String cardNumber) {
        boolean isFound = false;
        FindCardsRequestDTO findCardsRequestDTO = new FindCardsRequestDTO();
        findCardsRequestDTO.setCardNumber(cardNumber);
        for (Card card : findCards(findCardsRequestDTO)) {
            if (card.getNumber().equals(cardNumber)) {
                isFound = true;
                break;
            }
        }
        return isFound;

    }
}
