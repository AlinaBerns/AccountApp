package com.example.accountapp.model;

import com.example.accountapp.util.IdGenerator;

import java.util.Optional;

public class User {
    private long id;
    private String fname;
    private String lname;
    private Optional <Account> optionalAccount;
    private Account account;

    public User(long id, String fname, String lname, Optional<Account> account) {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.optionalAccount = account;
    }

    public User(String fname, String lname, Optional<Account> account) {
        this(IdGenerator.generateID(), fname, lname, account);
        this.fname = fname;
        this.lname = lname;
        this.optionalAccount = account;

    }

    public long getId() {
        return id;
    }

    public String getFname() {
        return fname;
    }

    //public void setFname(String fname) {
        //this.fname = fname;
   // }

    public String getLname() {
        return lname;
    }

    //public void setLname(String lname) {
        //this.lname = lname;
    //}

    public Account getAccount() {
        return optionalAccount.get();
    }


}
