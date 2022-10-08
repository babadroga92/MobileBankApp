package com.solvd.bankdb.exception;

public class InvalidIdException extends Exception {
    private String msg;

    public String getMsg() {
        return msg;
    }

    public InvalidIdException(String msg) {
        this.msg = msg;
    }
}
