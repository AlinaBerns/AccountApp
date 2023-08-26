package com.example.accountapp.service;

import com.example.accountapp.model.Account;
import com.example.accountapp.model.User;
import com.example.accountapp.repository.AccountRepository;

import java.util.Optional;

public class LoginService {
    private UserService userService = new UserService();

    public boolean register(String fname, String lname, String email, String passw) {

        Account account = new Account(email, passw);
        User user = new User(fname, lname, Optional.of(account));

        userService.createUser(user);

       return true;
    }

    public boolean loginIsExist(String email) {
        AccountRepository accountRepository = new AccountRepository();

        return accountRepository.getAccount(email).isPresent();
    }

    public void login (String email, String passw) {
        Account account = new Account(email, passw);

        Optional <Account> optionalAccount = Optional.of(account);

        AccountRepository accountRepository = new AccountRepository();



        if (loginIsExist(email)&&!accountRepository.getAccount(email).equals(optionalAccount)) {
            //System.out.println("Your password isn't correct");

        } else if (loginIsExist(email)&&accountRepository.getAccount(email).equals(optionalAccount)) {
            System.out.println("WELCOME, "+account.getEmail());

        } else {
            System.out.println("Account isn't exist");
        }
    }
}
