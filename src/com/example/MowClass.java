package com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

/**
 * Created by anastasia on 27/02/15.
 */
public class MowClass {

    HashMap<String, Character> positions = null;

    private int height = 0;
    private int width = 0;


    private int xCurrent = 0;
    private int yCurrent = 0;
    private String directions = "nesw";
    private int directionCurrent = 0;

    MowClass() {
        positions = new HashMap<String, Character>();
    }

    public String getPositions() {
        return createPositionString();
    }

    private String createPositionString () {
        String result = "";
        for (String s : positions.keySet()) {
            result += s + " " + positions.get(s) + "<br/>";
        }

        return result;
    }

    private void setInitialPositions(String aPositions) throws MowExceptions {
        String parts[] = aPositions.split("\\s+");
        if (parts.length == 3) {
            xCurrent = Integer.parseInt(parts[0]);
            yCurrent = Integer.parseInt(parts[1]);
            directionCurrent = directions.indexOf(parts[2].charAt(0));
        } else throw new MowExceptions("Bad arguments");
    }

    private boolean isOccupied(int x, int y) {
        if (positions.get(x + " " + y) != null) { return true; }
        return false;

    }

    private boolean isOutOfBounds(int x, int y) {
        if (0 <= x && x < width)  { return false; }
        if (0 <= y && y < height) { return false; }
        return true;

    }

    private void makeMove() {

        switch (directions.charAt(directionCurrent)) {
            case 'n':
                if (!isOutOfBounds(xCurrent, yCurrent + 1) && !isOccupied(xCurrent, yCurrent + 1)) {
                    ++yCurrent;
                }
                break;

            case 's' :
                if (!isOutOfBounds(xCurrent, yCurrent - 1) && !isOccupied(xCurrent, yCurrent - 1)) {
                    --yCurrent;
                }
                break;

            case 'e' :
                if (!isOutOfBounds(xCurrent + 1 , yCurrent) && !isOccupied(xCurrent + 1, yCurrent)) {
                    ++xCurrent;
                }
                break;

            case 'w' :
                if (!isOutOfBounds(xCurrent - 1 , yCurrent) && !isOccupied(xCurrent - 1, yCurrent)) {
                    --xCurrent;
                } else {
                }
                break;
        }

    }

    private void turnLeft() {
        directionCurrent = (directionCurrent + 3) % 4;
    }

    private void turnRight() {
        directionCurrent = (directionCurrent + 1) % 4;
    }


    private void processStates(String aCommands) {


        for (char commandCurrent : aCommands.toCharArray()) {
            switch (commandCurrent) {
                case 'a' :
                    makeMove();
                    break;
                case 'g' :
                    turnLeft();
                    break;
                case 'd' :
                    turnRight();
                    break;
            }
        }
    }

    public void calculateFinalPositions(InputStream in) throws MowExceptions {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));

        String line;
        try {
            line = reader.readLine();
            if (line != null) {
                String parts[] = line.split("\\s+");
                width = Integer.parseInt(parts[0]);
                height = Integer.parseInt(parts[1]);
            }

            while ((line = reader.readLine()) != null) {
                line = line.toLowerCase();
                setInitialPositions(line);
                line = reader.readLine();
                if (line != null) {
                    line = line.toLowerCase();
                    processStates(line);
                    positions.put(xCurrent + " " + yCurrent, Character.toUpperCase(directions.charAt(directionCurrent)));
                }
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
