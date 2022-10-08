package com.solvd.bankdb.service;

import com.solvd.bankdb.exception.*;
import com.solvd.bankdb.model.Account;
import com.solvd.bankdb.model.User;

public interface IBankService {

    public Account getAccountById(int id);
    public void addUser(User user) throws NoNameException;
    public User getUSer(int accountId) throws InvalidIdException;

    public void deleteUser(int id) throws InvalidIdException;

    public void deleteAccount(int id) throws InvalidIdException;

    public void createAccount(Account account);

    public void withdrawBalance(int accountId, double amount) throws InsufficientAmountException;

    public void depositBalance(int accountId, double amount);

    public void transfer(int accountId, double amount) throws InsufficientAmountException;
    public User findUser(String username, String psswd) throws InvalidUserOrPsswdException;

    public Account findAccountByAccountNumber(String accountNumber) throws InvalidAccountNumberException;

    public Account findAccountForUserId(int userId) throws InvalidAccountNumberException;

    public User findUserBySsn(String ssn) throws InvalidSsnException;

}
