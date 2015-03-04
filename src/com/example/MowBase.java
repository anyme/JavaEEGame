package com.example;

/**
 * Created by anastasia on 04/03/15.
 */
public abstract class MowBase {

    protected final Character FORWARD = 'A';
    protected final Character GOLEFT  = 'G';
    protected final Character GORIGHT = 'D';

    protected int xCurrent;
    protected int yCurrent;
    protected String instructions;
    protected int currentInstruction;
    protected NESW directionCurrent;

    public MowBase() {
        this.xCurrent = 0;
        this.yCurrent = 0;
        this.instructions = "";
        this.currentInstruction = 0;
        this.directionCurrent = NESW.DEFAULT;
    }

    public void validateInitialStateInput(String initState) {
        if (initState == null) {
            throw new NullPointerException("Illegal null");
        }
        if (initState == "") {
            throw new IllegalArgumentException("Illegal empty string");
        }
    }

    public void validateInstructionsInput(String aInstructions) {
        if (aInstructions == null) {
            throw new NullPointerException("Illegal null");
        }
        if (aInstructions == "") {
            throw new IllegalArgumentException("Illegal empty string");
        }
    }

    /**
     * MowClass Getters/Setters
     */

    public NESW getDirectionCurrent() {
        return directionCurrent;
    }

    public int getXCurrent() {
        return xCurrent;
    }

    public int getYCurrent() {
        return yCurrent;
    }

    public int getNextXPosition() {
        switch (this.getDirectionCurrent()) {
            case NORTH:
            case SOUTH:
                return xCurrent;
            case EAST:
                return xCurrent + 1;
            case WEST:
                return xCurrent - 1;
        }
        return -1;
    }

    public int getNextYPosition() {
        switch (this.getDirectionCurrent()) {
            case NORTH:
                return yCurrent + 1;
            case SOUTH:
                return yCurrent - 1;
            case EAST:
            case WEST:
                return yCurrent;
        }
        return -1;
    }

    public String getCurrentPositionString() {
        return xCurrent + " " + yCurrent;
    }

    public String displayCurrentState() {
        return getCurrentPositionString() + " " + getDirectionCurrent().getName();
    }

    public void setDirectionCurrent(NESW directionCurrent) {
        this.directionCurrent = directionCurrent;
    }

    public void setDirectionCurrent(Character directionCurrent) {
        switch (directionCurrent) {
            case 'N':
                this.directionCurrent = NESW.NORTH;
                break;
            case 'S':
                this.directionCurrent = NESW.SOUTH;
                break;
            case 'W':
                this.directionCurrent = NESW.WEST;
                break;
            case 'E':
                this.directionCurrent = NESW.EAST;
                break;
        }

    }

    public void setInstructions(String anInstruction) {
        instructions = anInstruction;
    }

    /**
     * MowClass basic behaviour
     */

    public void skipInstruction() {}

    public abstract void turnLeft();
    public abstract void turnRight();
    public abstract void forward();
    public abstract boolean readInstructionsFlow();

}
