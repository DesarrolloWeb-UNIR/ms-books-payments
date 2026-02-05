package com.relato.msbookspayments.exception;

public class InvalidPurchaseStateException extends RuntimeException {

    public InvalidPurchaseStateException(String message) {
        super(message);
    }
}