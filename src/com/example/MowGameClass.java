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

    public void getResults(String filename) {
        System.out.println(" board positions :  " + board.getPositions());
        GameCache.cache.put(filename, board.getPositions());
    }
}
