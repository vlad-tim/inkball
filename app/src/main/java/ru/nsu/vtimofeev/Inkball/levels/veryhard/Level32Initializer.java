package ru.nsu.vtimofeev.Inkball.levels.veryhard;

import ru.nsu.vtimofeev.Inkball.levels.LevelInitializer;
import ru.nsu.vtimofeev.Inkball.levels.generation.BallDescription;
import ru.nsu.vtimofeev.Inkball.levels.generation.Coordinates;
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
public class Level32Initializer extends LevelInitializer {

    private float spawnInterval = 0f;

    private static float MIN_VELOCITY = 5.0f;
    private static float VELOCITY_KOEF = 6.0f;

    public void initialize(WorldInitialization world) {

        initBorderWalls(world);
        initWalls(world);
        initPuzzles(world);
        initTimerWalls(world);
        initHoles(world);
        initBallGenerator(world);
    }

    private void initTimerWalls(WorldInitialization world) {
        drawTimerWallLine(world, 1, WALLS_IN_ROW/2 - 3, WALLS_IN_COLUMN/2, WALLS_IN_COLUMN/2);
        drawTimerWallLine(world, WALLS_IN_ROW/2 + 3, WALLS_IN_ROW - 2, WALLS_IN_COLUMN/2,
                WALLS_IN_COLUMN/2);
        drawTimerWallLine(world, WALLS_IN_ROW/2, WALLS_IN_ROW/2, 1, WALLS_IN_COLUMN/2 - 3);
        drawTimerWallLine(world, WALLS_IN_ROW/2, WALLS_IN_ROW/2, WALLS_IN_COLUMN/2 + 3,
                WALLS_IN_COLUMN - 2);
    }

    @Override
    protected void initBorderWalls(WorldInitialization world) {

        drawWallLine(world, -1, -1, 0, WALLS_IN_COLUMN - 1, Color.NONE, false);
        drawWallLine(world, WALLS_IN_ROW, WALLS_IN_ROW, 0, WALLS_IN_COLUMN - 1, Color.NONE, false);

        drawWallLine(world, 0, WALLS_IN_ROW - 1, -1, -1, Color.NONE, false);
        drawWallLine(world, 0, WALLS_IN_ROW - 1, WALLS_IN_COLUMN, WALLS_IN_COLUMN, Color.NONE, false);
    }

    private void initWalls(WorldInitialization world) {

        drawWallLine(world, WALLS_IN_ROW/2 - 2, WALLS_IN_ROW/2 + 2, WALLS_IN_COLUMN/2,
                WALLS_IN_COLUMN/2,
                Color.NONE, false);
        drawWallLine(world, WALLS_IN_ROW /2, WALLS_IN_ROW/2, WALLS_IN_COLUMN/2 + 1,
                WALLS_IN_COLUMN/2 + 2, Color.NONE, false);
        drawWallLine(world, WALLS_IN_ROW /2, WALLS_IN_ROW/2, WALLS_IN_COLUMN/2 - 2,
                WALLS_IN_COLUMN/2 - 1, Color.NONE, false);

        drawWallLine(world, 0, 0, WALLS_IN_COLUMN/2,
                WALLS_IN_COLUMN/2, Color.BLUE, false);
        drawWallLine(world, WALLS_IN_ROW - 1,  WALLS_IN_ROW - 1, WALLS_IN_COLUMN/2,
                WALLS_IN_COLUMN/2, Color.YELLOW, false);
        drawWallLine(world, WALLS_IN_ROW/2, WALLS_IN_ROW/2, 0,
                0, Color.ORANGE, false);
        drawWallLine(world, WALLS_IN_ROW/2, WALLS_IN_ROW/2, WALLS_IN_COLUMN - 1,
                WALLS_IN_COLUMN - 1, Color.GREEN, false);
    }

    private void initPuzzles(WorldInitialization world) {

        world.addPuzzle(new Puzzle((WALLS_IN_ROW/2 - 2 + 0.5f) * Wall.defaultSize,
                (WALLS_IN_COLUMN/2 - 2 + 0.5f) * Wall.defaultSize, Color.YELLOW,
                PuzzleState.UNLOCKED));
        world.addPuzzle(new Puzzle((WALLS_IN_ROW/2 - 1 + 0.5f) * Wall.defaultSize,
                (WALLS_IN_COLUMN/2 - 2 + 0.5f) * Wall.defaultSize, Color.YELLOW,
                PuzzleState.UNLOCKED));
        world.addPuzzle(new Puzzle((WALLS_IN_ROW/2 - 2 + 0.5f) * Wall.defaultSize,
                (WALLS_IN_COLUMN/2 - 1 + 0.5f) * Wall.defaultSize, Color.YELLOW,
                PuzzleState.UNLOCKED));

        world.addPuzzle(new Puzzle((WALLS_IN_ROW/2 - 2 + 0.5f) * Wall.defaultSize,
                (WALLS_IN_COLUMN/2 + 2 + 0.5f) * Wall.defaultSize, Color.ORANGE,
                PuzzleState.LOCKED));
        world.addPuzzle(new Puzzle((WALLS_IN_ROW/2 - 1 + 0.5f) * Wall.defaultSize,
                (WALLS_IN_COLUMN/2 + 2 + 0.5f) * Wall.defaultSize, Color.ORANGE,
                PuzzleState.LOCKED));
        world.addPuzzle(new Puzzle((WALLS_IN_ROW/2 - 2 + 0.5f) * Wall.defaultSize,
                (WALLS_IN_COLUMN/2 + 1 + 0.5f) * Wall.defaultSize, Color.ORANGE,
                PuzzleState.LOCKED));

        world.addPuzzle(new Puzzle((WALLS_IN_ROW/2 + 2 + 0.5f) * Wall.defaultSize,
                (WALLS_IN_COLUMN/2 + 2 + 0.5f) * Wall.defaultSize, Color.BLUE,
                PuzzleState.LOCKED));
        world.addPuzzle(new Puzzle((WALLS_IN_ROW/2 + 1 + 0.5f) * Wall.defaultSize,
                (WALLS_IN_COLUMN/2 + 2 + 0.5f) * Wall.defaultSize, Color.BLUE,
                PuzzleState.LOCKED));
        world.addPuzzle(new Puzzle((WALLS_IN_ROW/2 + 2 + 0.5f) * Wall.defaultSize,
                (WALLS_IN_COLUMN/2 + 1 + 0.5f) * Wall.defaultSize, Color.BLUE,
                PuzzleState.LOCKED));

        world.addPuzzle(new Puzzle((WALLS_IN_ROW/2 + 2 + 0.5f) * Wall.defaultSize,
                (WALLS_IN_COLUMN/2 - 2 + 0.5f) * Wall.defaultSize, Color.GREEN,
                PuzzleState.LOCKED));
        world.addPuzzle(new Puzzle((WALLS_IN_ROW/2 + 1 + 0.5f) * Wall.defaultSize,
                (WALLS_IN_COLUMN/2 - 2 + 0.5f) * Wall.defaultSize, Color.GREEN,
                PuzzleState.LOCKED));
        world.addPuzzle(new Puzzle((WALLS_IN_ROW/2 + 2 + 0.5f) * Wall.defaultSize,
                (WALLS_IN_COLUMN/2 - 1 + 0.5f) * Wall.defaultSize, Color.GREEN,
                PuzzleState.LOCKED));

    }


    private void initBallGenerator(WorldInitialization world) {

        BallDescription description1 =
                new BallDescription(VelocityType.ANY_VELOCITY, Color.ORANGE,
                        new Coordinates((WALLS_IN_ROW/2 - 1 + 0.5f) * Wall.defaultSize,
                                (WALLS_IN_COLUMN/2 - 1 + 0.5f) * Wall.defaultSize));

        BallDescription description2 =
                new BallDescription(VelocityType.ANY_VELOCITY, Color.BLUE,
                        new Coordinates((WALLS_IN_ROW/2 - 1 + 0.5f) * Wall.defaultSize,
                                (WALLS_IN_COLUMN/2 + 1 + 0.5f) * Wall.defaultSize));

        BallDescription description3 =
                new BallDescription(VelocityType.ANY_VELOCITY, Color.GREEN,
                        new Coordinates((WALLS_IN_ROW/2 + 1 + 0.5f) * Wall.defaultSize,
                                (WALLS_IN_COLUMN/2 + 1 + 0.5f) * Wall.defaultSize));

        BallDescription description4 =
                new BallDescription(VelocityType.ANY_VELOCITY, Color.YELLOW,
                        new Coordinates((WALLS_IN_ROW/2 + 1 + 0.5f) * Wall.defaultSize,
                                (WALLS_IN_COLUMN/2 - 1 + 0.5f) * Wall.defaultSize));

        initBallGenerator(world, VELOCITY_KOEF, MIN_VELOCITY, spawnInterval,
                description1, description2, description3, description4);
    }


    private void initHoles(WorldInitialization world) {

        world.addHole((new Hole((1) * Wall.defaultSize,
                (1) * Wall.defaultSize, Color.GREEN)));
        world.addHole((new Hole((WALLS_IN_ROW - 1) * Wall.defaultSize,
                (1) * Wall.defaultSize, Color.BLUE)));
        world.addHole((new Hole((WALLS_IN_ROW - 1) * Wall.defaultSize,
                (WALLS_IN_COLUMN - 1) * Wall.defaultSize, Color.ORANGE)));
        world.addHole((new Hole((1) * Wall.defaultSize,
                (WALLS_IN_COLUMN - 1) * Wall.defaultSize, Color.YELLOW)));
    }
}
