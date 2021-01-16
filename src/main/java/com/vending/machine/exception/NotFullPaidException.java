package com.vending.machine.exception;

public class NotFullPaidException extends RuntimeException {
    private String message;
    private long balance;

    public NotFullPaidException(String message, long balance) {
        this.message = message;
        this.balance = balance;
    }
}
