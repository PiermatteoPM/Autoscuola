package com.autoscuola.exception;

public class InvalidEmailException extends Exception{
    public InvalidEmailException() {
        super("Invalid email entered!");
    }

}
