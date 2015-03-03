package com.example;

import java.io.InputStream;

/**
 * Created by anastasia on 03/03/15.
 */
public class MowGameClass {

    BoardClass board;

    MowGameClass() {
        this.board = new BoardClass();
    }

    public void initBoard(InputStream in) {
        board.initBoard(in);
    }

    public void play() {
        board.startReadingInstructions();
    }

    public String getResults() {
        return board.getPositions();
    }
}
