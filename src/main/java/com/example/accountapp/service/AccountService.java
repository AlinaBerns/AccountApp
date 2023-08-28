package com.example.accountapp.service;

import com.example.accountapp.model.Account;
import com.example.accountapp.model.User;
import com.example.accountapp.repository.AccountRepository;
import com.example.accountapp.repository.UserRepository;

import java.util.List;
import java.util.Optional;

public class AccountService {
    private AccountRepository accountRepository = new AccountRepository();
    public boolean createAccount(Account account) {
        return accountRepository.createAccount(account);
    }

    public Optional<Account> getAccount(String email) {
        return accountRepository.getAccount(email);
    }

    public void createManyAccounts(List<Account> accountList) {
        accountRepository.createManyAccounts(accountList);
    }

}
