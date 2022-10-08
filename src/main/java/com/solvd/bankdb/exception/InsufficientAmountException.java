package com.solvd.bankdb.exception;

public class InsufficientAmountException extends Exception{
    private String msg;

    public String getMsg() {
        return msg;
    }

    public InsufficientAmountException(String msg) {
        this.msg = msg;
    }
}
