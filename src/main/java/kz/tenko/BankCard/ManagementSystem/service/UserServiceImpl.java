package kz.tenko.BankCard.ManagementSystem.service;

import kz.tenko.BankCard.ManagementSystem.DAO.UserDAO;
import kz.tenko.BankCard.ManagementSystem.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private final UserDAO userDAO;

    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    @Transactional
    public List<User> findUsers() {
        return userDAO.findUsers();
    }

    @Override
    @Transactional
    public void saveUser(User user) {
        if (user.getEmail() == null || user.getRole() == null || user.getName() == null || user.getPassword() == null) {
            throw new RuntimeException("Заполните обязательные поля");
        }

        if (userDAO.findUserByEmail(user.getEmail()) != null) {
            throw new RuntimeException("Пользователь с таким email уже существует");
        }
        userDAO.saveUser(user);
    }

    @Override
    @Transactional
    public void deleteUser(long id) {
        userDAO.deleteUser(id);
    }
}
