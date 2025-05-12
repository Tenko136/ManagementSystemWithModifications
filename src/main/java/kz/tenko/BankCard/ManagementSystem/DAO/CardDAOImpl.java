package kz.tenko.BankCard.ManagementSystem.DAO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import kz.tenko.BankCard.ManagementSystem.DTO.FindCardsRequestDTO;
import kz.tenko.BankCard.ManagementSystem.entity.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

@Repository
public class CardDAOImpl implements CardDAO {

    @Autowired
    private final EntityManager entityManager;

    public CardDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void saveCard(Card card) {
        Card c = entityManager.merge(card);
        card.setId(c.getId());
    }

    @Override
    public List<Card> findCards(FindCardsRequestDTO findCardsRequestDTO) {
        Query query = null;
        if (!StringUtils.hasText(findCardsRequestDTO.getCardNumber())) {
            query = entityManager.createQuery("from Card");
        } else {
         query = entityManager.createQuery("from Card where number = :cardNumber");
         query.setParameter("cardNumber", findCardsRequestDTO.getCardNumber());
        }
        query.setFirstResult((findCardsRequestDTO.getPageNumber() - 1) * findCardsRequestDTO.getPageSize());
        query.setMaxResults(findCardsRequestDTO.getPageSize());
        return query.getResultList();
    }

    @Override
    public List<Card> findCards(Long userId) {
        Query query = entityManager.createQuery("from Card where userId = :userId");
        query.setParameter("userId", userId);
        return query.getResultList();
    }

    @Override
    public void deleteCard(long id) {
        Query query = entityManager.createQuery("delete Card where id =:cardId");
        query.setParameter("cardId", id);
        query.executeUpdate();
    }

    @Override
    public void blockingCard(String cardNumber) {
        Query query = entityManager.createQuery("update Card set cardBlockingRequest = true where number =:cardNumber");
        query.setParameter("cardNumber", cardNumber);
        query.executeUpdate();
    }

    @Override
    public Long findBalance(String number) {
        Card card = entityManager.find(Card.class, number);
        return card.getBalance();
    }

    @Override
    public void changeBalance(String number, Long amount) {
        Query query = entityManager.createQuery("update Card set balance = balance + :amount where number = :number");
        query.setParameter("amount", amount);
        query.setParameter("number", number);
        query.executeUpdate();
    }
}
