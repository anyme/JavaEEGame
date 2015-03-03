package com.example;

/**
 * Created by anastasia on 27/02/15.
 */
public class MowExceptions extends Exception {
    public MowExceptions (String message) {
        super("MowException:: " + message);
    }
}
