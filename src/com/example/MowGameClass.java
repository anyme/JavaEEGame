package com.example;

import java.io.InputStream;

/**
 * Created by anastasia on 03/03/15.
 */
public class MowGameClass {

    BoardClass board;

    MowGameClass(InputStream in) {
        this.board = new BoardClass(in);
    }

    public void play() {
        board.startReadingInstructions();
    }

    public String getResults() {
        return board.getPositions();
    }
}
