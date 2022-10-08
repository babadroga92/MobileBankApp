package com.solvd.bankdb.dao;

import com.solvd.bankdb.model.Account;
import com.solvd.bankdb.model.User;

public interface IBankDao {
    public void addUser(User user);
    public User getUSer(int accountId);

    public void updateBalance(int accountId, double currentBalance);
    public void deleteUser(int id);

    public void deleteAccount(int id);

    public void createAccount(Account account);

    public Account getAccountById(int id);

    public User findUser(String username, String psswd);

    public Account findAccountByAccountNumber(String accountNumber);

    public Account findAccountForUserId(int userId);

    public User findUserBySsn(String ssn);

}
