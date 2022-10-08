package com.solvd.bankdb.model;

public class User {

    private int id;

    private String firstName;

    private String lastName;

    private String ssn;

    private int accountID;

    private String username;

    private String psswd;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPsswd() {
        return psswd;
    }

    public void setPsswd(String psswd) {
        this.psswd = psswd;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public int getAccountID() {
        return accountID;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User() {
    }

    public User(String firstName, String lastName, String ssn, int accountID, int id, String username, String psswd) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.ssn = ssn;
        this.accountID = accountID;
        this.id = id;
        this.username = username;
        this.psswd = psswd;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", ssn='" + ssn + '\'' +
                ", accountID=" + accountID +
                '}';
    }
}
