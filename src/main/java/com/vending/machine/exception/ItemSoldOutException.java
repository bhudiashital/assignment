package com.vending.machine.exception;

public class ItemSoldOutException extends RuntimeException {
    private String message;

    public ItemSoldOutException(String string) {
        this.message = string;
    }
}
