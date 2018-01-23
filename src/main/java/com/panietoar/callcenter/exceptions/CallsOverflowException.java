package com.panietoar.callcenter.exceptions;

public class CallsOverflowException extends Exception {

    public CallsOverflowException() {
        super("Max capacity of calls reached, please wait");
    }
}
