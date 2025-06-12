package kz.tenko.BankCard.ManagementSystem.DAO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import kz.tenko.BankCard.ManagementSystem.DTO.CardsForBlocking;
import kz.tenko.BankCard.ManagementSystem.DTO.FindCardsRequestDTO;
import kz.tenko.BankCard.ManagementSystem.entity.Card;
import kz.tenko.BankCard.ManagementSystem.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class AdminDAOImpl {

    @Autowired
    private final EntityManager entityManager;

    public AdminDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<User> findUsers() {
        Query query = entityManager.createQuery("from User");
        return query.getResultList();
    }

    //todo проверить корректность
    public void saveUser(User user) {
        User u = entityManager.merge(user);
        user.setId(u.getId());
    }

    public void deleteUser(long id) {
        Query query = entityManager.createQuery("delete from User where id =:userId");
        query.setParameter("userId", id);
        query.executeUpdate();
    }

    public List<Card> findCards(FindCardsRequestDTO findCardsRequestDTO) {
        Query query = entityManager.createQuery("from Card");
        return query.getResultList();
    }

    //todo проверить корректность
    public void saveCard(Card card) {
        Card c = entityManager.merge(card);
        card.setId(c.getId());
    }

// постраничная выдача:
//    public List<Card> findCards(FindCardsRequestDTO findCardsRequestDTO) {
//        Query query = null;
//        if (!StringUtils.hasText(findCardsRequestDTO.getCardNumber())) {
//            query = entityManager.createQuery("from Card");
//        } else {
//         query = entityManager.createQuery("from Card where number = :cardNumber");
//         query.setParameter("cardNumber", findCardsRequestDTO.getCardNumber());
//        }
//        query.setFirstResult((findCardsRequestDTO.getPageNumber() - 1) * findCardsRequestDTO.getPageSize());
//        query.setMaxResults(findCardsRequestDTO.getPageSize());
//        return query.getResultList();
//    }

    public void deleteCard(long id) {
        Query query = entityManager.createQuery("delete Card where id =:cardId");
        query.setParameter("cardId", id);
        query.executeUpdate();
    }

    public User findUserByEmail(String email) {
        Query query = entityManager.createQuery("from User where email = :email");
        query.setParameter("email", email);
        return (User) query.getSingleResult();
    }


    public List<CardsForBlocking> blockingCardRequest(LocalDate date) {
        Query query = entityManager.createQuery("from Card where date =:date");
       return query.getResultList();
    }

    public void blockingCardResponse(List<CardsForBlocking> cardsForBlocking) {
        for (CardsForBlocking blocking : cardsForBlocking) {
            String number = blocking.number();
            Query query = entityManager.createQuery("update Card set cardBlockingRequest = true where number =:number");
            query.setParameter("number", number);
        }
    }
}
