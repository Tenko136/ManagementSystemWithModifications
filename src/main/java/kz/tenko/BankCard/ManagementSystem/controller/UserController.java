package kz.tenko.BankCard.ManagementSystem.controller;

import kz.tenko.BankCard.ManagementSystem.entity.Card;
import kz.tenko.BankCard.ManagementSystem.service.UserServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// todo вынести клиента отдельно, сделать клиентскую часть MVC
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserServiceImpl userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    //todo извлечение id активного пользователя, ограничение видимости карт в рамках id
    @GetMapping("/my-card")
    public List<Card> findCards(Long userId) {
        return userService.findCards(userId);
    }

    //todo отображение только номера карты и баланса, (?)нужна ДТО
    @GetMapping("/balance")
    public void findBalance(String cardNum) {
        userService.findBalance(cardNum);
    }

    //todo логика работы с валютами, переводами
    @PostMapping("/transfer")
    public ResponseEntity<?> transferAmount(String cardFrom, String cardTo, long transferAmount) {
        try {
            userService.transferAmount(cardFrom, cardTo, transferAmount);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    //todo тоже ограничение показа, стоит вынести в отдельный метод, пока не подключу секьюрити
    @GetMapping("/all-transfer")
    public void findAllTransfer() {
        userService.findAllTransfers();
    }

    //todo проверка
    @PostMapping("/card-blocking-request")
    public void cardBlockingRequest(String cardNumber) {
        userService.cardBlockingRequest(cardNumber);
    }
}
