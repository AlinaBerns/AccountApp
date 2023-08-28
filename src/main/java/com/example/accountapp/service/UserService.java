package com.example.accountapp.service;

import com.example.accountapp.model.Account;
import com.example.accountapp.model.User;
import com.example.accountapp.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserService {

    private AccountService accountService = new AccountService();
    private UserRepository userRepository = new UserRepository();

   public boolean createUser(User user) {

       boolean success = accountService.createAccount(user.getAccount());
       if (success) {
            userRepository.createUser(user);
        }

        return false;
    }

    public Optional<User> getUser(String email) {
       Optional<Account> account = accountService.getAccount(email);

       if (account.isPresent()) {
           Account foundAccount = account.get();
           Optional<User> user = userRepository.getUser(foundAccount);

           return user;
       } else {
           return Optional.empty();
       }
    }
    public void createManyUsers(List<User> userList){
        List <Account> accounts = new ArrayList<>();

        for (User user: userList) {
            accounts.add(user.getAccount());
        }
        accountService.createManyAccounts(accounts);
    }
}
