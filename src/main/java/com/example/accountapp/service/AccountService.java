package com.example.accountapp.service;

import com.example.accountapp.model.Account;
import com.example.accountapp.repository.AccountRepository;

public class AccountService {
   private AccountRepository accountRepository = new AccountRepository();
    public boolean createAccount(Account account) {

        accountRepository.createAccount(account);


        return true;
    }
}
