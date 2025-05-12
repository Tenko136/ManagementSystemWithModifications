package kz.tenko.BankCard.ManagementSystem.controller;

import kz.tenko.BankCard.ManagementSystem.entity.User;
import kz.tenko.BankCard.ManagementSystem.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/find-users")
    public List<User> findUsers() {
        return userService.findUsers();
    }

    @PostMapping("/save-user")
    public void saveUser(@RequestBody User user) {
        userService.saveUser(user);
    }

    @DeleteMapping("/delete-user/{id}")
    public void deleteUser(@PathVariable long id) {
        userService.deleteUser(id);
    }

}
