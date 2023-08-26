package com.example.accountapp.model;

import java.util.Objects;

public class Account {

    private final String email;
    private String passw;

    public Account(String email, String passw) {
        this.email = email;
        this.passw = passw;
    }

    public String getEmail() {
        return email;
    }

    public String getPassw() {
        return passw;
    }

    public void setPassw(String passw) {
        this.passw = passw;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(email, account.email) && Objects.equals(passw, account.passw);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, passw);
    }
}
