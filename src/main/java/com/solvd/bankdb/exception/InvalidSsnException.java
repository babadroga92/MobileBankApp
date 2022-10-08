package com.solvd.bankdb.exception;

public class InvalidSsnException extends Exception{
    private String msg;

    public String getMsg() {
        return msg;
    }

    public InvalidSsnException(String msg) {
        this.msg = msg;
    }
}
