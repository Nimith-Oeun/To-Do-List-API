package com.personal.todolistapi.exceptions;

public class ResourNotFound extends RuntimeException {
    public ResourNotFound(String message) {
        super(message);
    }
}