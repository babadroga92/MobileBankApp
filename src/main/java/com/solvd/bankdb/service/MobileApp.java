package com.solvd.bankdb.service;

import com.solvd.bankdb.dao.BankDao;
import com.solvd.bankdb.exception.InsufficientAmountException;
import com.solvd.bankdb.exception.InvalidAccountNumberException;
import com.solvd.bankdb.exception.InvalidUserOrPsswdException;
import com.solvd.bankdb.model.Account;
import com.solvd.bankdb.model.AccountType;
import com.solvd.bankdb.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.util.Scanner;

public class MobileApp {

    private Connection connection;

    private BankDao radnik;

    private BankServiceImpl menadzer;

    public MobileApp(Connection connection, BankDao radnik, BankServiceImpl menadzer) {
        this.connection = connection;
        this.radnik = radnik;
        this.menadzer = menadzer;
    }

    private static Logger log = LogManager.getLogger(MobileApp.class.getName());

    public void login(){
        try{
            Scanner scanner = new Scanner(System.in);
            System.out.println("Welcome to NBS bank!");
            System.out.println("Choose '1' if you would like to create an Account");
            System.out.println("Choose '2' if you would like to make transactions");
            String userAction = scanner.nextLine();
            if(userAction.equalsIgnoreCase("1")){
                System.out.println("Enter your first name: ");
                String firstName = scanner.nextLine();
                System.out.println("Enter your last name: ");
                String lastName = scanner.nextLine();
                System.out.println("Enter your username: ");
                String username = scanner.nextLine();
                System.out.println("Enter your password: ");
                String psswd = scanner.nextLine();
                System.out.println("Enter your social security number: ");
                String ssnNumber = scanner.nextLine();
                System.out.println("Input account type (Checking,savings, growth) : ");
                String accType = scanner.nextLine();
                System.out.println("Input account number: ");
                String accNumber = scanner.nextLine();
                User userSsn = menadzer.findUserBySsn(ssnNumber);
                if(userSsn != null){
                    System.out.println("Your SSN is not unique");
                }

                AccountType accountType = AccountType.valueOf(accType.toUpperCase());
                Account account = new Account(0,accountType,0,accNumber);
                menadzer.createAccount(account);
                account=menadzer.findAccountByAccountNumber(accNumber);


                User user4= new User(firstName, lastName,"7575544",account.getId(),0, username, psswd);
                menadzer.addUser(user4);
                System.out.println("If you would like to make a transaction press '2': ");
                userAction =scanner.nextLine();
                if(userAction.equalsIgnoreCase("2")){
                    this.makeTransaction();
                }
            } else if (userAction.equalsIgnoreCase("2")) {
               this.makeTransaction();
            }
            scanner.close();
        }catch (Exception ex){
            log.error(ex.getMessage());
        }

    }
    public void makeTransaction()  {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter your username: ");
            String username = scanner.nextLine();
            System.out.println("Enter your password: ");
            String psswd = scanner.nextLine();
            User user5 = menadzer.findUser(username, psswd);
            System.out.println("Choose 'A' if you would like to deposit money into your Account");
            System.out.println("Choose 'B' if you would like to withdraw money into your Account");
            System.out.println("Choose 'C' if you would like to transfer money into another Account");
            String userAction1 = scanner.nextLine();
            System.out.println("Please type the amount: ");
            double amount = scanner.nextDouble();
            Account myAcc = menadzer.findAccountForUserId(user5.getId());
            if (userAction1.equalsIgnoreCase("A")) {
                menadzer.depositBalance(myAcc.getId(), amount);
            } else if (userAction1.equalsIgnoreCase("B")) {
                menadzer.withdrawBalance(myAcc.getId(), amount);
            } else if (userAction1.equalsIgnoreCase("C")) {
                System.out.println("Please type an account number to which you would like to transfer money: ");
                scanner.nextLine();
                String accountNumberTo = scanner.nextLine();
                Account accTo = menadzer.findAccountByAccountNumber(accountNumberTo);
                menadzer.withdrawBalance(myAcc.getId(), amount);
                menadzer.depositBalance(accTo.getId(), amount);
            } else {
                System.out.println("Transaction unsuccessful. Please go back to the starting Menu and choose again. ");
            }
            Account account = menadzer.getAccountById(myAcc.getId());
            System.out.println("Current balance is:" + " " + account.getBalance());
        } catch (InsufficientAmountException e) {
            log.error(e.getMsg());
        } catch (InvalidUserOrPsswdException e) {
            log.error(e.getMsg());
        } catch (InvalidAccountNumberException e) {
            log.error(e.getMsg());
        }
    }
}
