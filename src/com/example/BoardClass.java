package com.example;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by anastasia on 03/03/15.
 */
public class BoardClass {
    private int mHeight = 0;
    private int mWidth = 0;

    HashMap<String, MowClass> mPositions = null;
    ArrayList<MowClass> mQueue = null;

    public BoardClass(InputStream in) {

        this.mPositions = new HashMap<String, MowClass>();
        this.mQueue = new ArrayList<MowClass>();

        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        String line;

        try {
            line = reader.readLine();
            if (line != null) {
                String parts[] = line.split("\\s+");
                mWidth  = Integer.parseInt(parts[0]) + 1;
                mHeight = Integer.parseInt(parts[1]) + 1;
            }

            while ((line = reader.readLine()) != null) {
                MowClass aMow = new MowClass(line.toLowerCase());
                line = reader.readLine();
                if (line != null) {
                    aMow.setInstructions(line.toLowerCase());
                    mPositions.put(aMow.getCurrentPositionString(), aMow);
                    mQueue.add(aMow);
                }
            }

            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private String createPositionString () {
        String result = "";
        for (MowClass mow : mQueue) {
            result += mow.getFullState() + "<br/>";
        }

        return result;
    }

    public String getPositions() {
        return createPositionString();
    }


    private boolean isOccupied(int x, int y) {
        if (mPositions.get(x + "" + y) != null) { return true; }

        return false;

    }

    private boolean isOutOfBounds(int x, int y) {
        if (! (0 <= x && x < mWidth) )  { return true; }
        if (! (0 <= y && y < mHeight) ) { return true; }
        return false;

    }

    private void updatePositions(String oldKey, String newKey){
        mPositions.put(newKey, mPositions.remove(oldKey));
    }

    private  void handleMowMovements(MowClass aMow) {
        String aKey = aMow.getXCurrent() + " " + aMow.getYCurrent();
        displayBoard();
        while (aMow.readNextInstruction()) {
            int x = aMow.getNextXPosition();
            int y = aMow.getNextYPosition();

            if (!isOutOfBounds(x, y) && !isOccupied(x, y)) {
                aMow.makeMove();
                updatePositions(aKey, aMow.getXCurrent() + " " + aMow.getYCurrent());
            }
            displayBoard();
        }


    }

    public void startReadingInstructions() {
        for (MowClass aMow : mQueue) {
            handleMowMovements(aMow);
        }
    }

    public void displayBoard() {
        for (int j = 0; j < mWidth ; ++j) {
            System.out.print(" " + j);
        }
        System.out.println(" ");

        for (int i = mHeight - 1; i >= 0; --i) {
            System.out.print(i + "");
            for (int j = 0; j < mWidth ; ++j) {
                if (mPositions.get(j + "" + i) != null) {
                    System.out.print(mPositions.get(j + "" + i).getDirectionCurrent() + " ");
                } else {
                    System.out.print("- ");
                }
            }
            System.out.println(" ");
        }

    }
}
