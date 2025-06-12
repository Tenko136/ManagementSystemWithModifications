package kz.tenko.BankCard.ManagementSystem.DAO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import kz.tenko.BankCard.ManagementSystem.entity.Card;
import kz.tenko.BankCard.ManagementSystem.entity.CurrencyRate;
import org.springframework.stereotype.Repository;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Repository
public class UserDAOImpl {

    private final EntityManager entityManager;

    private final static String currencyURL = "https://openexchangerates.org/api/latest.json?app_id=7e07ce2faba9499991eb01abd0c50e71&symbols=RUB,KZT&date=%s";
    private final static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public UserDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Card> findCards(Long userId) {
        Query query = entityManager.createQuery("from Card where userId = :userId");
        query.setParameter("userId", userId);
        return query.getResultList();
    }

    public Long findBalance(String number) {
        Card card = entityManager.find(Card.class, number);
        return card.getBalance();
    }

    public void changeBalance(String number, Long amount) {
        Query query = entityManager
                .createQuery("update Card set balance = balance + :amount where number = :number");
        query.setParameter("amount", amount);
        query.setParameter("number", number);
        query.executeUpdate();
    }

    public void findAllTransfers() {

    }

    public void blockingCard(String cardNumber) {
        Query query = entityManager
                .createQuery("update Card set cardBlockingRequest = true where number =:cardNumber");
        query.setParameter("cardNumber", cardNumber);
        query.executeUpdate();
    }

    @Transactional
    public CurrencyRate getCurrencyRate() {
        return (CurrencyRate) entityManager
                .createQuery("from CurrencyRate order by dateRate desc limit 1")
                .getSingleResult();
    }
}
