package com.example;

import com.example.utility.FileCache;
import com.example.utility.GameCache;
import com.example.utility.LoggerClass;

import java.io.InputStream;
import java.util.concurrent.Callable;

/**
 * Created by anastasia on 03/03/15.
 */
public class MowGameClass implements Callable {

    BoardClass board;
    String filename;

    public MowGameClass(String aFilename) {
        filename = aFilename;
        this.board = new BoardClass();
    }

    public void initBoard(InputStream in) throws Exception {
        board.initBoard(in);
        in.close();
    }

    public void play() {
        LoggerClass.LOGGER.info("Game is launched");
        board.startReadingInstructions();
    }

    public void getResults(String filename) {
        GameCache.cache.put(filename, board.getPositions());
    }

    @Override
    public Object call() throws Exception {
        this.initBoard(FileCache.cache.get(filename));
        this.play();
        this.getResults(filename);
        return null;
    }
}
