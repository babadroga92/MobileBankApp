package com.solvd.bankdb.model;

public class Account {
    private int id;
    private AccountType type;
    private double balance;

    private String accountNumber;

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public AccountType getType() {
        return type;
    }

    public void setType(AccountType type) {
        this.type = type;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Account() {
    }

    public Account(int id, AccountType type, double balance, String accountNumber) {
        this.id = id;
        this.type = type;
        this.balance = balance;
        this.accountNumber=accountNumber;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", type=" + type +
                ", balance=" + balance +
                ", accountNumber='" + accountNumber + '\'' +
                '}';
    }
}
