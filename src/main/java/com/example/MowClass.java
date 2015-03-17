package com.example;

import com.example.utility.NESW;

/**
 * Created by anastasia on 27/02/15.
 */
public class MowClass extends MowBase {

    private final String initStatePattern = "^[0-9]+\\s[0-9]+\\s[NEWS]$";
    private final String instructionsPattern = "^[AGD]+$";

    MowClass(String aCurrentPosition) {
        validateInitialStateInput(aCurrentPosition);
        parseArguments(aCurrentPosition);
    }

    @Override
    public boolean readInstructionsFlow(){
        while (currentInstruction < instructions.length()){
            if (instructions.charAt(currentInstruction) == GOLEFT)  { turnLeft(); }
            if (instructions.charAt(currentInstruction) == GORIGHT) { turnRight(); }
            if (instructions.charAt(currentInstruction) == FORWARD) {

                ++currentInstruction;
                return true;
            }
            ++currentInstruction;
        }
        return false;
    }

    @Override
    public void validateInitialStateInput(String initState) {
        super.validateInitialStateInput(initState);
        if (!initState.matches(initStatePattern)) {
            throw new IllegalArgumentException("Illegal initial state format");
        }
    }

    @Override
    public void validateInstructionsInput(String aInstructions) {
        super.validateInstructionsInput(aInstructions);
        if (!aInstructions.matches(instructionsPattern)) {
            throw new IllegalArgumentException("Illegal instructions format");
        }

    }

    public void parseArguments(String args) {
        String parts[] = args.split("\\s+");
        xCurrent = Integer.parseInt(parts[0]);
        yCurrent = Integer.parseInt(parts[1]);
        setDirectionCurrent(parts[2].charAt(0));
    }

    @Override
    public void setInstructions(String anInstruction) {
        validateInstructionsInput(anInstruction);
        super.setInstructions(anInstruction);
    }

    @Override
    public void turnLeft() {
        switch (this.getDirectionCurrent()) {
            case NORTH:
                setDirectionCurrent(NESW.WEST);
                break;

            case SOUTH:
                setDirectionCurrent(NESW.EAST);
                break;

            case EAST:
                setDirectionCurrent(NESW.NORTH);
                break;

            case WEST:
                setDirectionCurrent(NESW.SOUTH);
                break;

        }
    }

    @Override
    public void turnRight() {
        switch (this.getDirectionCurrent()) {
            case NORTH:
                setDirectionCurrent(NESW.EAST);
                break;

            case SOUTH:
                setDirectionCurrent(NESW.WEST);
                break;

            case EAST:
                setDirectionCurrent(NESW.SOUTH);
                break;

            case WEST:
                setDirectionCurrent(NESW.NORTH);
                break;
        }
    }

    @Override
    public void forward() {
        xCurrent = getNextXPosition();
        yCurrent = getNextYPosition();
    }

}
