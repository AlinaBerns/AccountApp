package com.example.accountapp.service;

import com.example.accountapp.model.Account;
import com.example.accountapp.model.User;
import com.example.accountapp.repository.AccountRepository;

import java.util.List;
import java.util.Optional;

public class LoginService {
    private UserService userService = new UserService();

    public boolean register(String fname, String lname, String email, String passw) {

        Account account = new Account(email, passw);
        User user = new User(fname, lname, Optional.of(account));

        userService.createUser(user);

       return true;
    }

    public Optional<User> login(String email, String passw) {
        Optional<User> user = userService.getUser(email);

        if (user.isPresent()&&user.get().getAccount().getPassw().equals(passw)) {
            return user;
        }

        return Optional.empty();
    }

    public void registerManyUsers(List<User> userList) {
        userService.createManyUsers(userList);
    }
}
