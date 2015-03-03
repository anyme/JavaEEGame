package com.example;

/**
 * Created by anastasia on 27/02/15.
 */
public class MowClass {


    private int xCurrent = 0;
    private int yCurrent = 0;
    private String mInsrtuctions = "";
    private int mCurrentInstruction = 0;
    private final String mDirections = "nesw";
    private int mDirectionCurrent = 0;

    public MowClass(String aCurrentPosition) throws MowExceptions {
        String parts[] = aCurrentPosition.split("\\s+");
        if (parts.length == 3) {
            xCurrent = Integer.parseInt(parts[0]);
            yCurrent = Integer.parseInt(parts[1]);
            mDirectionCurrent = mDirections.indexOf(parts[2].charAt(0));
        } else throw new MowExceptions("Bad arguments");

    }

    public void setInstructions(String anInstruction) {
        mInsrtuctions = anInstruction;
    }

    public String getCurrentPositionString() {
        return xCurrent + " " + yCurrent;
    }

    public Character getDirectionCurrent() {
        return mDirections.charAt(mDirectionCurrent);
    }

    public String getFullState() {
        return getCurrentPositionString() + " " + getDirectionCurrent().toString().toUpperCase();
    }

    public int getXCurrent() {
        return xCurrent;
    }

    public int getYCurrent() {
        return yCurrent;
    }

    public boolean readNextInstruction(){
        while (mCurrentInstruction < mInsrtuctions.length() && mInsrtuctions.charAt(mCurrentInstruction) != 'a'){
            if (mInsrtuctions.charAt(mCurrentInstruction) == 'g') { turnLeft(); }
            if (mInsrtuctions.charAt(mCurrentInstruction) == 'd') { turnRight(); }
            ++mCurrentInstruction;
        }
        if (mCurrentInstruction < mInsrtuctions.length()) {
            ++mCurrentInstruction;
            return true;
        }
        return false;
    }

    public int getNextXPosition() {
        switch (this.getDirectionCurrent()) {
            case 'n':
                return xCurrent;

            case 's' :
                return xCurrent;

            case 'e' :
                return xCurrent + 1;

            case 'w' :
                return xCurrent - 1;
        }
        return -1;
    }

    public int getNextYPosition() {
        switch (this.getDirectionCurrent()) {
            case 'n':
                return yCurrent + 1;

            case 's' :
                return yCurrent - 1;

            case 'e' :
                return yCurrent;

            case 'w' :
                return yCurrent;
        }
        return -1;
    }

    public void makeMove() {
        xCurrent = getNextXPosition();
        yCurrent = getNextYPosition();
    }

    public void turnLeft() {
        mDirectionCurrent = (mDirectionCurrent + 3) % 4;
    }

    public void turnRight() {
        mDirectionCurrent = (mDirectionCurrent + 1) % 4;
    }

}
