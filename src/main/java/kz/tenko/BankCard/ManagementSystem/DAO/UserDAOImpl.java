package kz.tenko.BankCard.ManagementSystem.DAO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import kz.tenko.BankCard.ManagementSystem.entity.Card;
import kz.tenko.BankCard.ManagementSystem.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDAOImpl {

    @Autowired
    private final EntityManager entityManager;

    public UserDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void blockingCard(String cardNumber) {
        Query query = entityManager.createQuery("update Card set cardBlockingRequest = true where number =:cardNumber");
        query.setParameter("cardNumber", cardNumber);
        query.executeUpdate();
    }

    public Long findBalance(String number) {
        Card card = entityManager.find(Card.class, number);
        return card.getBalance();
    }

    public void changeBalance(String number, Long amount) {
        Query query = entityManager.createQuery("update Card set balance = balance + :amount where number = :number");
        query.setParameter("amount", amount);
        query.setParameter("number", number);
        query.executeUpdate();
    }
}
