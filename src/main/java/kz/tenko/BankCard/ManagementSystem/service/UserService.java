package kz.tenko.BankCard.ManagementSystem.service;


import kz.tenko.BankCard.ManagementSystem.entity.User;

import java.util.List;


public interface UserService {

    List<User> findUsers();

    void saveUser(User user);

    void deleteUser(long id);


}

