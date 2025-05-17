package kz.tenko.BankCard.ManagementSystem.controller;

import kz.tenko.BankCard.ManagementSystem.DTO.CardsForBlocking;
import kz.tenko.BankCard.ManagementSystem.DTO.FindCardsRequestDTO;
import kz.tenko.BankCard.ManagementSystem.entity.Card;
import kz.tenko.BankCard.ManagementSystem.entity.User;
import kz.tenko.BankCard.ManagementSystem.service.AdminServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final AdminServiceImpl adminService;

    public AdminController(AdminServiceImpl adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/find-users")
    public List<User> findUsers() {
        return adminService.findUsers();
    }

    @PostMapping("/save-user")
    public void saveUser(@RequestBody User user) {
        adminService.saveUser(user);
    }

    @DeleteMapping("/delete-user/{id}")
    public void deleteUser(@PathVariable long id) {
        adminService.deleteUser(id);
    }

    @GetMapping("/find-cards")
    public List<Card> findAllCards(@RequestBody FindCardsRequestDTO findCardsRequestDTO) {
        return adminService.findCards(findCardsRequestDTO);
    }

    @PostMapping("/save-card")
    public void saveCard(@RequestBody Card card) {
        adminService.saveCard(card);
    }

    @DeleteMapping("/delete-card/{id}")
    public void deleteCard(@PathVariable long id) {
        adminService.deleteCard(id);
    }

    @GetMapping("/blocking-card")
    public List<CardsForBlocking> blockingCardsRequest() {
       return adminService.blockingCardRequest();
    }

    @PostMapping("/blocking-card")
    public void blockingCardResponse(List<CardsForBlocking> cardsForBlocking) {
        adminService.blockingCardResponse(cardsForBlocking);
    }
}
