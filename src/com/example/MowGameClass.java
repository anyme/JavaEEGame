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

    public void initBoard(InputStream in) throws Exception {
        board.initBoard(in);
    }

    public void play() {
        LoggerClass.LOGGER.info("Game is launched");
        board.startReadingInstructions();
    }

    public void getResults(String filename) {
        GameCache.cache.put(filename, board.getPositions());
    }
}
