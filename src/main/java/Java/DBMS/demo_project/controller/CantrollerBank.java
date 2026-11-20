package Bankmanagement.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;
import Bankmanagement.app.model.User;
import Bankmanagement.app.service.BankService;

@RestController
@RequestMapping ("/users")
public class BankController {
    @Autowired
    private BankService bankService;

    @GetMapping
    public List<User> getAllUsers() {
        return bankService.getAllUsers();
    }
}