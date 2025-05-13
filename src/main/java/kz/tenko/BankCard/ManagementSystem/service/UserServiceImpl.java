package kz.tenko.BankCard.ManagementSystem.service;

import kz.tenko.BankCard.ManagementSystem.DAO.AdminDAOImpl;
import kz.tenko.BankCard.ManagementSystem.DAO.UserDAOImpl;
import kz.tenko.BankCard.ManagementSystem.DTO.FindCardsRequestDTO;
import kz.tenko.BankCard.ManagementSystem.entity.Card;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl  {

   private final UserDAOImpl userDAO;
   private final AdminDAOImpl adminDAO;

    public UserServiceImpl(UserDAOImpl userDAO, AdminDAOImpl adminDAO) {
        this.userDAO = userDAO;
        this.adminDAO = adminDAO;
    }

    @Transactional
    public void cardBlockingRequest(String cardNumber) {
        if (!cardFilter(cardNumber)) {
            throw new RuntimeException(String.format("Не найдена карта с номером %s у данного пользователя ", cardNumber));
        }
        userDAO.blockingCard(cardNumber);
    }
    @Transactional
    public void transferAmount(String cardFrom, String cardTo, long transferAmount) {

        if (cardFilter(cardFrom) && cardFilter(cardTo)) {
            long from =userDAO.findBalance(cardFrom);
            if ((from - transferAmount) < 0) {
                throw new RuntimeException("Недостаточно средств");
            }
            userDAO.changeBalance(cardFrom, -transferAmount);
            userDAO.changeBalance(cardTo, transferAmount);
            return;
        }
        throw new RuntimeException("Попытка перевода на стороннюю карту");
    }

    public boolean cardFilter(String cardNumber) {
        boolean isFound = false;
        FindCardsRequestDTO findCardsRequestDTO = new FindCardsRequestDTO();
        findCardsRequestDTO.setCardNumber(cardNumber);
        for (Card card : adminDAO.findCards(findCardsRequestDTO)) {
            if (card.getNumber().equals(cardNumber)) {
                isFound = true;
                break;
            }
        }
        return isFound;
    }
}
