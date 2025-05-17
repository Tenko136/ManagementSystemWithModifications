package kz.tenko.BankCard.ManagementSystem.service;

import kz.tenko.BankCard.ManagementSystem.DAO.AdminDAOImpl;
import kz.tenko.BankCard.ManagementSystem.DTO.CardsForBlocking;
import kz.tenko.BankCard.ManagementSystem.DTO.FindCardsRequestDTO;
import kz.tenko.BankCard.ManagementSystem.entity.Card;
import kz.tenko.BankCard.ManagementSystem.entity.User;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class AdminServiceImpl {

    private final AdminDAOImpl adminDAO;

    public AdminServiceImpl(AdminDAOImpl adminDAO) {
        this.adminDAO = adminDAO;
    }

    @Transactional
    public List<User> findUsers() {
        return adminDAO.findUsers();
    }

    @Transactional
    public void saveUser(User user) {
        if (user.getEmail() == null || user.getRole() == null || user.getFirstName() == null
                || user.getLastName() == null || user.getPassword() == null) {
            throw new RuntimeException("Заполните обязательные поля");
        }

        if (adminDAO.findUserByEmail(user.getEmail()) != null) {
            throw new RuntimeException("Пользователь с таким email уже существует");
        }
        adminDAO.saveUser(user);
    }

    @Transactional
    public void deleteUser(long id) {
        adminDAO.deleteUser(id);
    }

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
        return adminDAO.findCards(findCardsRequestDTO);
    }

    @Transactional
    public void saveCard(Card card) {
        card.setNumber(card.getNumber().replaceAll("\\D", ""));
        if (!card.getNumber().matches("[0-9]{16}")) {
            throw new RuntimeException("Некорректный номер");
        }
//        if (card.getNumber() == null || card.getBalance() == null || card.getStatus() == null ||
//                card.getUserId() == null || card.getExpirationDate() == null) {
//            throw new RuntimeException("Заполните обязательные поля");
//        }
        adminDAO.saveCard(card);
    }

    @Transactional
    public void deleteCard(long id) {
        adminDAO.deleteCard(id);
    }

    @Transactional
    @Scheduled(cron = "@midnight")
    public List<CardsForBlocking> blockingCardRequest() {
        LocalDate date = LocalDate.now();
        return adminDAO.blockingCardRequest(date);
    }
    @Transactional
    public void blockingCardResponse(List<CardsForBlocking> cardsForBlocking) {
        adminDAO.blockingCardResponse(blockingCardRequest());
    }
}
