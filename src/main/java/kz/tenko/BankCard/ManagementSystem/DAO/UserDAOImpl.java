package kz.tenko.BankCard.ManagementSystem.DAO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import kz.tenko.BankCard.ManagementSystem.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {

    @Autowired
    private final EntityManager entityManager;

    public UserDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public User findUserByEmail(String email) {
        Query query = entityManager.createQuery("from User where email = :email");
        query.setParameter("email", email);
        return (User) query.getSingleResult();
    }

    @Override
    public List<User> findUsers() {
        Query query = entityManager.createQuery("from User");
        return query.getResultList();
    }

    @Override
    public void saveUser(User user) {
        User u = entityManager.merge(user);
        user.setId(u.getId());
    }

    @Override
    public void deleteUser(long id) {
        Query query = entityManager.createQuery("delete from User where id =:userId");
        query.setParameter("userId", id);
        query.executeUpdate();
    }
}
