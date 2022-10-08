package com.solvd.bankdb.service;

import com.solvd.bankdb.dao.BankDao;
import com.solvd.bankdb.exception.*;
import com.solvd.bankdb.model.Account;
import com.solvd.bankdb.model.User;

import java.util.Objects;

public class BankServiceImpl implements IBankService{

    private BankDao bankDao;

    public BankServiceImpl(BankDao bankDao) {
        this.bankDao = bankDao;
    }

    @Override
    public Account getAccountById(int id) {
        return this.bankDao.getAccountById(id);
    }

    @Override
    public void addUser(User user) throws NoNameException {
        if(user.getFirstName().isEmpty()){
            throw new NoNameException("User is required to have a name");
        }
         this.bankDao.addUser(user);

    }

    @Override
    public User getUSer(int accountId) throws InvalidIdException {
        if(accountId <1){
            throw new InvalidIdException("Invalid ID");
        }
        return bankDao.getUSer(accountId);
    }


    @Override
    public void deleteUser(int id) throws InvalidIdException {
        if(id<1){
            throw new InvalidIdException("User doesn't exist");
        }
        this.bankDao.deleteUser(id);

    }

    @Override
    public void deleteAccount(int id) throws InvalidIdException {
        if(id<1){
            throw new InvalidIdException("Account doesn't exist");
        }
        this.bankDao.deleteAccount(id);

    }

    @Override
    public void createAccount(Account account) {
        this.bankDao.createAccount(account);

    }

    @Override
    public void withdrawBalance(int accountId, double amount) throws InsufficientAmountException {
        Account account = this.bankDao.getAccountById(accountId);
        if(amount > account.getBalance()){
            throw new InsufficientAmountException("Insufficient amount.");
        }
        else {
            double newBalance = account.getBalance() - amount;
            this.bankDao.updateBalance(accountId, newBalance);
        }
    }

    @Override
    public void depositBalance(int accountId, double amount) {
        Account account = this.bankDao.getAccountById(accountId);
        double newBalance = account.getBalance() + amount;
        this.bankDao.updateBalance(accountId, newBalance);

    }

    @Override
    public void transfer(int accountId, double amount) throws InsufficientAmountException {
        withdrawBalance(accountId, amount);
        depositBalance(accountId, amount);

    }

    @Override
    public User findUser(String username, String psswd) throws InvalidUserOrPsswdException {
        if(username == null || psswd == null){
            throw new InvalidUserOrPsswdException("In order to make an account you will need both username and password!");
        }
        User user = this.bankDao.findUser(username,psswd);
        if(Objects.isNull(user)){
            throw new InvalidUserOrPsswdException("Your username and password do not match.");
        }
        return user;
    }

    @Override
    public Account findAccountByAccountNumber(String accountNumber) throws InvalidAccountNumberException {
        if(accountNumber==null || accountNumber.length()<10){
            throw new InvalidAccountNumberException("Account number not valid.");
        }
        return this.bankDao.findAccountByAccountNumber(accountNumber);
    }

    @Override
    public Account findAccountForUserId(int userId) throws InvalidAccountNumberException {
        if(userId<1){
            throw new InvalidAccountNumberException("Invalid account id number");
        }
        return this.bankDao.findAccountForUserId(userId);
    }

    @Override
    public User findUserBySsn(String ssn) throws InvalidSsnException {
        if(ssn ==null){
            throw new InvalidSsnException("No account with specific SSN exist.");
        }
        return this.bankDao.findUserBySsn(ssn);
    }
}
