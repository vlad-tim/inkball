package ru.nsu.vtimofeev.Inkball.graphics;

import ru.nsu.vtimofeev.GameFramework.HelperClasses.GLHelpers.TextureRegion;
import ru.nsu.vtimofeev.Inkball.model.*;
import ru.nsu.vtimofeev.Inkball.resources.Assets;

/**
 * Created by IntelliJ IDEA.
 * User: vtimofeev
 * Date: 6/29/12
 * Time: 6:52 PM
 * To change this template use File | Settings | File Templates.
 */
public class Utils {
    
    private static Color[] colors = Color.values();
    private static Direction[] directions = Direction.values();
    private static BarAlignment[] alignments = BarAlignment.values();
    private static PuzzleState[] puzzleStates = PuzzleState.values();

    public static TextureRegion getBarRegion(Bar bar) {
        
        Color barColor = bar.getColor();
        Direction direction = bar.getDirection();
        BarAlignment alignment = bar.getAlignment();
        
        int colorIndex = getIndex(barColor);
        int regionIndex = 0;
        
        if (bar.hasDirection()) {

            regionIndex += directions.length * colorIndex + getIndex(direction);
        }

        else {

            regionIndex += directions.length * colors.length;
            regionIndex += alignments.length * (colorIndex - 1) + getIndex(alignment);
        }
        
        return Assets.barRegions[regionIndex];
    }

    public static TextureRegion getPuzzleRegion(Puzzle puzzle) {

        Color puzzleColor = puzzle.getColor();
        PuzzleState state = puzzle.getState();

        int colorIndex = getIndex(puzzleColor);
        int stateIndex = getIndex(state);
        int regionIndex = 0;


        regionIndex += puzzleStates.length * (colorIndex - 1) + stateIndex;

        return Assets.puzzlesRegions[regionIndex];
    }

    private static int getIndex(Color color) {
        for (int i = 0; i < colors.length; ++i) {
            if (color == colors[i]) {
                return i;
            }
        }

        //Should normally never get here
        return -1;
    }

    private static int getIndex(Direction direction) {
        for (int i = 0; i < directions.length; ++i) {
            if (direction == directions[i]) {
                return i;
            }
        }

        //Should normally never get here
        return -1;
    }

    private static int getIndex(BarAlignment alignment) {
        for (int i = 0; i < alignments.length; ++i) {
            if (alignment == alignments[i]) {
                return i;
            }
        }

        //Should normally never get here
        return -1;
    }

    private static int getIndex(PuzzleState state) {
        for (int i = 0; i < puzzleStates.length; ++i) {
            if (state == puzzleStates[i]) {
                return i;
            }
        }

        //Should normally never get here
        return -1;
    }
}
