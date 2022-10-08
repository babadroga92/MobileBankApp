package com.solvd.bankdb.exception;

public class NoNameException extends Exception {
    private String msg;

    public String getMsg() {
        return msg;
    }

    public NoNameException(String msg) {
        this.msg = msg;
    }
}
