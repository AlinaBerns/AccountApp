package com.example.accountapp.service;

import com.example.accountapp.model.Account;
import com.example.accountapp.model.User;
import com.example.accountapp.repository.UserRepository;

import java.util.Optional;

public class UserService {

    private AccountService accountService = new AccountService();
    private UserRepository userRepository = new UserRepository();

   public boolean createUser(User user) {

       boolean success = accountService.createAccount(user.getAccount());
       if (success) {
            userRepository.createUser(user);
        }


        return true;
    }

    public static void main(String[] args) {
        Account account = new Account("lala", "lala");
        User user = new User("l", "a", Optional.of(account));
        System.out.println(user.toString());

        Account account1 = user.getAccount();
        System.out.println(account1.getEmail());
    }
}
