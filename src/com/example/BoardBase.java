package com.example;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by anastasia on 04/03/15.
 */
public abstract class BoardBase {
    protected int height = 0;
    protected int width = 0;

    protected HashMap<String, MowClass> board = null;
    protected ArrayList<MowClass> mow = null;

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {

        return height;
    }

    public int getWidth() {
        return width;
    }

    protected abstract void setDimensions(String dimen);

    public HashMap<String, MowClass> getBoard() {
        return board;
    }

    public ArrayList<MowClass> getMow() {
        return mow;
    }

    public BoardBase() {
        this.mow = new ArrayList<MowClass>();
        this.board = new HashMap<String, MowClass>();
    }

    public void validateDimensionInput (String dimen) {
        if (dimen == null) {
            throw new NullPointerException("Illegal null");
        }
        if (dimen.isEmpty()) {
            throw new IllegalArgumentException("Illegal empty string");
        }
    }
}
