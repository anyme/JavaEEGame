package com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by anastasia on 03/03/15.
 */
public class BoardClass extends BoardBase {

    private final String dimensionPattern = "^[0-9]+\\s[0-9]+$";
    private final String NEWLINE = "\\n";

    @Override
    protected void setDimensions(String dimen) {
        validateDimensionInput(dimen);
        parseArguments(dimen);
    }

    BoardClass() {
        super();
    }

    public void initBoard(InputStream in) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        String line;
        setDimensions(reader.readLine());

        while ((line = reader.readLine()) != null) {
            MowClass aMow = new MowClass(line);
            aMow.setInstructions(reader.readLine());
            board.put(getKey(aMow), aMow);
            mow.add(aMow);
        }
        reader.close();

    }

    private void parseArguments(String args) {
        String parts[] = args.split("\\s+");
        width = Integer.parseInt(parts[0]) + 1;
        height = Integer.parseInt(parts[1]) + 1;
    }


    public String getPositions() {
        String result = "";
        for (MowClass mow : this.mow) {
            result += mow.displayCurrentState() + NEWLINE;
        }
        return result;
    }


    private boolean isOccupied(int x, int y) {
        return board.get(x + "" + y) != null;

    }

    private boolean isOutOfBounds(int x, int y) {
        return !(0 <= x && x < width) || !(0 <= y && y < height);

    }

    private void updateBoardPositions(String oldKey, String newKey){
        board.put(newKey, board.remove(oldKey));
    }

    private String getKey(MowClass aMow) {
        return aMow.getXCurrent() + "" + aMow.getYCurrent();
    }

    /**
     * Verify that each step is legal within the board parameters
     * @param aMow
     */
    private void handleMowMovements(MowClass aMow) {
        String aKey;
        while (aMow.readInstructionsFlow()) {
            aKey = getKey(aMow);
            int x = aMow.getNextXPosition();
            int y = aMow.getNextYPosition();

            if (!isOutOfBounds(x, y) && !isOccupied(x, y)) {
                aMow.forward();
                updateBoardPositions(aKey, aMow.getXCurrent() + "" + aMow.getYCurrent());
            } else {
                aMow.skipInstruction();
            }
        }
    }

    /**
     * Start reading set of the instructions of each Mow
     */
    public void startReadingInstructions() {
        for (MowClass aMow : mow) {
            handleMowMovements(aMow);
        }
    }

    @Override
    public void validateDimensionInput(String dimen) {
        super.validateDimensionInput(dimen);
        if (!dimen.matches(dimensionPattern)) {
            throw new IllegalArgumentException("Illegal board dimension size");
        }
    }
}
