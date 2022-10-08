package com.solvd.bankdb.model;

public enum AccountType {
    CHECKING ("Checking account"),
    SAVINGS("Savings account"),
    GROWTH("Growth account");

    public String value;

    public String getValue() {
        return value;
    }

    AccountType(String value) {
        this.value = value;

    }
}
