package com.solvd.bankdb.exception;

public class InvalidUserOrPsswdException extends Exception{
    private String msg;

    public String getMsg() {
        return msg;
    }

    public InvalidUserOrPsswdException(String msg) {
        this.msg = msg;
    }
}
