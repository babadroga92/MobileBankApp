package com.solvd.bankdb.dao;

import com.solvd.bankdb.model.Account;
import com.solvd.bankdb.model.AccountType;
import com.solvd.bankdb.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BankDao implements IBankDao{

    private Connection connection;

    private String updateBalance = "Update Account set balance=? where id=? ";

    private String getAllUsers = "Select * from User";

    private String getUserByAccId = "Select * from Users where accountID=?";

    private String addUser = "Insert into User (firstName, lastName, ssn, accountID, username, psswd) values(?,?,?,?,?,?) ";

    private String createAccount = "Insert into Account(type, balance, accountnumber) values(?,?,?) ";

    private String deleteUser = "Delete from User where ID=?";

    private String deleteAccount = "Delete from Account where ID=?";

    private String getAccountById = "Select * from Account where id=?";

    private String findUser = "Select * from User where username=? and psswd=?";

    private String findAccountByAccountNumber = "Select * from Account where accountnumber=?";

    private String findAccountForUserId = "Select a.id, a.type, a.balance, a.accountnumber from Account a join User u on a.id=u.accountID where u.id=?";

    private String findUserBySsn = "Select * from User where ssn=?";
    private static Logger log = LogManager.getLogger(BankDao.class.getName());

    public BankDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void addUser(User user) {
        try {
            PreparedStatement ps = connection.prepareStatement(addUser);
            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.setString(3, user.getSsn());
            ps.setInt(4,user.getAccountID());
            ps.setString(5,user.getUsername());
            ps.setString(6,user.getPsswd());
            ps.executeUpdate();
        }
        catch (SQLException ex){
            log.error(ex.getMessage());
        }
    }

    @Override
    public User getUSer(int accountId) {
        try {
            PreparedStatement ps = connection.prepareStatement(getUserByAccId);
            ps.setInt(1,accountId);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return new User(rs.getString("firstName"),
                        rs.getString("lastName"),rs.getString("ssn"),
                        rs.getInt("accountID"),rs.getInt("id"),rs.getString("username"),rs.getString("psswd"));
            }
        }
        catch (SQLException ex){
            log.error(ex.getMessage());
        }
        return null;
    }


    @Override
    public void updateBalance(int accountId, double currentBalance) {
        try {
            PreparedStatement ps = connection.prepareStatement(updateBalance);
            ps.setDouble(1, currentBalance);
            ps.setInt(2, accountId);
            ps.executeUpdate();

        }catch (SQLException ex){
            log.error(ex.getMessage());
        }
    }

    @Override
    public void deleteUser(int id) {
        try {
            PreparedStatement ps = connection.prepareStatement(deleteUser);
            ps.setInt(1, id);
            ps.executeUpdate();
        }
      catch (SQLException ex){
            log.error(ex.getMessage());
      }
    }

    @Override
    public void deleteAccount(int id) {
        try{
            PreparedStatement ps = connection.prepareStatement(deleteAccount);
            ps.setInt(1, id);
            ps.executeUpdate();
        }catch (SQLException ex){
            log.error(ex.getMessage());
        }

    }

    @Override
    public void createAccount(Account account) {
        try {
            PreparedStatement ps = connection.prepareStatement(createAccount);
            ps.setString(1, account.getType().name());
            ps.setDouble(2,account.getBalance());
            ps.setString(3,account.getAccountNumber());
            ps.executeUpdate();
        }
        catch (SQLException ex){
            log.error(ex.getMessage());
        }
    }

    @Override
    public Account getAccountById(int id) {
        try {
            PreparedStatement ps = connection.prepareStatement(getAccountById);
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                String type = rs.getString("type");
                AccountType accountType = AccountType.valueOf(type);
                return new Account(rs.getInt("id"),accountType,rs.getDouble("balance"),rs.getString("accountnumber"));
            }
        }
        catch (SQLException ex){
            log.error(ex.getMessage());
        }
        return null;
    }

    @Override
    public User findUser(String username, String psswd) {
        try {
            PreparedStatement ps = connection.prepareStatement(findUser);
            ps.setString(1,username);
            ps.setString(2,psswd);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return new User(rs.getString("firstName"),rs.getString("lastName")
                        ,rs.getString("ssn"),rs.getInt("accountID")
                        ,rs.getInt("id"),rs.getString("username"),rs.getString("psswd"));
            }
        }catch(SQLException ex){
            log.error(ex.getMessage());
        }
        return null;
    }

    @Override
    public Account findAccountByAccountNumber(String accountNumber) {
        try {
            PreparedStatement ps = connection.prepareStatement(findAccountByAccountNumber);
            ps.setString(1, accountNumber);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String type = rs.getString("type");
                AccountType accountType = AccountType.valueOf(type);
                return new Account(rs.getInt("id"), accountType, rs.getDouble("balance"), rs.getString("accountNumber"));
            }
        } catch (SQLException ex) {
            log.error(ex.getMessage());
        }
        return null;
    }

    @Override
    public Account findAccountForUserId(int userId) {
        try{
            PreparedStatement ps = connection.prepareStatement(findAccountForUserId);
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                String type = rs.getString("type");
                AccountType accountType = AccountType.valueOf(type);
                return new Account(rs.getInt("id"), accountType, rs.getDouble("balance"), rs.getString("accountNumber"));
            }
        }
        catch (SQLException e){
            log.error(e.getMessage());
        }
        return null;
    }

    @Override
    public User findUserBySsn(String ssn) {
        try {
            PreparedStatement ps = connection.prepareStatement(findUserBySsn);
            ps.setString(1,ssn);
            ResultSet rs =ps.executeQuery();
            if(rs.next()){
                return new User(rs.getString("firstName"),rs.getString("lastName")
                        ,rs.getString("ssn"),rs.getInt("accountID")
                        ,rs.getInt("id"),rs.getString("username"),rs.getString("psswd"));
            }
        }catch (SQLException ex){
            log.error(ex.getMessage());
        }
        return null;
    }
}
