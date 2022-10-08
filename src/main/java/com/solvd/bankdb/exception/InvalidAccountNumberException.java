package com.solvd.bankdb.exception;

public class InvalidAccountNumberException extends Exception{
    private String msg;

    public String getMsg() {
        return msg;
    }

    public InvalidAccountNumberException(String msg) {
        this.msg = msg;
    }
}
