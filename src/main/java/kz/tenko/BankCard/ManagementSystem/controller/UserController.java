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

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserServiceImpl userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @PostMapping("/card-blocking-request")
    public void cardBlockingRequest(String cardNumber) {
        userService.cardBlockingRequest(cardNumber);
    }

    @PostMapping("/transfer")
    public ResponseEntity<?> transferAmount(String cardFrom, String cardTo, long transferAmount) {
        try {
            userService.transferAmount(cardFrom, cardTo, transferAmount);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/balance")
    public void findBalance(String cardNum) {
        userService.findBalance(cardNum);
    }

    @GetMapping("/all-transfer")
    public void findAllTransfer() {
        userService.findAllTransfers();
    }

    @GetMapping("/my-card")
    public List<Card> findCards(Long userId) {
        return userService.findCards(userId);
    }
}
