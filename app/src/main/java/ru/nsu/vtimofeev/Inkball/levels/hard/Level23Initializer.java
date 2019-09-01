package ru.nsu.vtimofeev.Inkball.levels.hard;

import ru.nsu.vtimofeev.Inkball.levels.LevelInitializer;
import ru.nsu.vtimofeev.Inkball.levels.generation.BallDescription;
import ru.nsu.vtimofeev.Inkball.levels.generation.VelocityType;
import ru.nsu.vtimofeev.Inkball.model.*;

import java.util.List;
import java.util.Random;

/**
 * Created by IntelliJ IDEA.
 * User: vtimofeev
 * Date: 5/17/12
 * Time: 10:40 PM
 * To change this template use File | Settings | File Templates.
 */
public class Level23Initializer extends LevelInitializer {

    private float spawnInterval = 4f;

    private static float MIN_VELOCITY = 4.0f;
    private static float VELOCITY_KOEF = 5.0f;

    private static final String firstHint = "LOCKABLE BLOCKS SWITCH";
    private static final String secondHint = "AFTER HOLING THE MATCHING BALL";

    @Override
    public String getFirstHint() {
        return firstHint;
    }
    @Override
    public String getSecondHint() {
        return secondHint;
    }

    public void initialize(WorldInitialization world) {

        initBorderWalls(world);
        initWalls(world);
        initHoles(world);
        initBallGenerator(world);
    }

    protected void initWalls(WorldInitialization world) {

        int deltaX = WALLS_IN_ROW / 5;
        int deltaY = WALLS_IN_COLUMN / 4 ;

        for (int i = deltaX; i <= WALLS_IN_ROW - deltaX;i+=deltaX) {
            for (int j = deltaY; j<= WALLS_IN_COLUMN - deltaY; j+=deltaY) {
                world.addWall(new Wall((i + 0.5f) * Wall.defaultSize,
                        (j + 0.5f) * Wall.defaultSize));
            }
        }
//
        drawPuzzleLine(world, 1, deltaX - 1, deltaY, deltaY, Color.ORANGE, PuzzleState.UNLOCKED);
        drawPuzzleLine(world, 1, deltaX - 1, 3*deltaY, 3*deltaY, Color.ORANGE, PuzzleState.UNLOCKED);

        drawPuzzleLine(world, deltaX + 1, 2*deltaX - 1, deltaY, deltaY, Color.BLUE,
                PuzzleState.UNLOCKED);
        drawPuzzleLine(world, deltaX + 1, 2*deltaX - 1, 3*deltaY, 3*deltaY, Color.BLUE,
                PuzzleState.UNLOCKED);
        drawPuzzleLine(world, deltaX + 1, 2*deltaX - 1, 2*deltaY, 2*deltaY, Color.ORANGE,
                PuzzleState.UNLOCKED);

        drawPuzzleLine(world, 2*deltaX + 1, 3*deltaX - 1, deltaY, deltaY, Color.ORANGE,
                PuzzleState.UNLOCKED);
        drawPuzzleLine(world, 2*deltaX + 1, 3*deltaX - 1, 2*deltaY, 2*deltaY, Color.BLUE,
                PuzzleState.UNLOCKED);

        drawPuzzleLine(world, 3*deltaX + 1, 4*deltaX - 1, deltaY, deltaY, Color.BLUE,
                PuzzleState.UNLOCKED);
        drawPuzzleLine(world, 3*deltaX + 1, 4*deltaX - 1, 2*deltaY, 2*deltaY, Color.ORANGE,
                PuzzleState.UNLOCKED);
        drawPuzzleLine(world, 3*deltaX + 1, 4*deltaX - 1, 3*deltaY, 3*deltaY, Color.ORANGE,
                PuzzleState.UNLOCKED);

        drawPuzzleLine(world, 4*deltaX + 1, 5*deltaX, 2*deltaY, 2*deltaY, Color.BLUE,
                PuzzleState.UNLOCKED);
        drawPuzzleLine(world, 4*deltaX + 1, 5*deltaX, 3*deltaY, 3*deltaY, Color.BLUE,
                PuzzleState.UNLOCKED);

    }


    private void initBallGenerator(WorldInitialization world) {

        float generatorX = (WALLS_IN_ROW - 2 + 0.5f) * Wall
                .defaultSize;
        float generatorY =  (2 + 0.5f) * Wall.defaultSize;

        BallDescription description1 =
                new BallDescription(VelocityType.ANY_VELOCITY_DEFAULT_COORDINATES, Color.BLUE);

        BallDescription description2 =
                new BallDescription(VelocityType.ANY_VELOCITY_DEFAULT_COORDINATES, Color.BLUE);

        BallDescription description3 =
                new BallDescription(VelocityType.ANY_VELOCITY_DEFAULT_COORDINATES, Color.ORANGE);

        initBallGenerator(world, generatorX,
                generatorY, VELOCITY_KOEF, MIN_VELOCITY, spawnInterval,
                description1, description2, description3);

    }


    private void initHoles(WorldInitialization world) {
        world.addHole((new Hole((2) * Wall.defaultSize,
                (WALLS_IN_COLUMN - 2) * Wall.defaultSize, Color.BLUE)));
        world.addHole((new Hole((WALLS_IN_ROW - 2) * Wall.defaultSize,
                (WALLS_IN_COLUMN - 2) * Wall.defaultSize, Color.ORANGE)));
    }

}
