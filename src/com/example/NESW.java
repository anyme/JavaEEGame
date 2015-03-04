package com.example;

/**
 * Created by anastasia on 04/03/15.
 */
public enum NESW {
    NORTH("N"),
    EAST("E"),
    SOUTH("S"),
    WEST("W"),
    DEFAULT("");

    private String name;

    NESW(String name) {
        this.name = name;
    }

    public String getName(){
        return name;
    }
}
