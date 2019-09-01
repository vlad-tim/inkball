package ru.nsu.vtimofeev.Inkball.levels.master;

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
public class Level37Initializer extends LevelInitializer {

    private float spawnInterval = 0f;

    private static float MIN_VELOCITY = 6.0f;
    private static float VELOCITY_KOEF = 7.0f;

    public void initialize(WorldInitialization world) {

        initBorderWalls(world);
        initWalls(world);
        initPuzzles(world);
        initHoles(world);
        initBallGenerator(world);

        world.setTime(60f);
    }

    private void initWalls(WorldInitialization world) {

        drawWallLine(world, WALLS_IN_ROW - 4, WALLS_IN_ROW - 4, 1, 2, Color.YELLOW, true);
        drawWallLine(world, WALLS_IN_ROW - 3, WALLS_IN_ROW - 2, 3, 3, Color.YELLOW, true);

        world.addWall(new Wall((WALLS_IN_ROW/2 - 1 + 0.5f) * Wall.defaultSize,
                (WALLS_IN_COLUMN - 2 + 0.5f) * Wall.defaultSize, Color.ORANGE, true));
        world.addWall(new Wall((WALLS_IN_ROW/2 + 0.5f) * Wall.defaultSize,
                (WALLS_IN_COLUMN - 3 + 0.5f) * Wall.defaultSize, Color.ORANGE, true));
        world.addWall(new Wall((WALLS_IN_ROW/2 + 1 + 0.5f) * Wall.defaultSize,
                (WALLS_IN_COLUMN - 2 + 0.5f) * Wall.defaultSize, Color.ORANGE, true));

        world.addWall(new Wall((WALLS_IN_ROW - 3 + 0.5f) * Wall.defaultSize,
                (WALLS_IN_COLUMN - 2 + 0.5f) * Wall.defaultSize, Color.ORANGE, false));
        world.addWall(new Wall((WALLS_IN_ROW - 2 + 0.5f) * Wall.defaultSize,
                (WALLS_IN_COLUMN - 2 + 0.5f) * Wall.defaultSize, Color.ORANGE, false));
        world.addWall(new Wall((WALLS_IN_ROW - 2 + 0.5f) * Wall.defaultSize,
                (WALLS_IN_COLUMN - 3 + 0.5f) * Wall.defaultSize, Color.ORANGE, false));

        drawWallLine(world, 6, 6, WALLS_IN_COLUMN/2-1, WALLS_IN_COLUMN/2+1, Color.GREEN, false);



    }


    private void initPuzzles(WorldInitialization world) {
        drawPuzzleRectangle(world, 5, 7, WALLS_IN_COLUMN/2-2, WALLS_IN_COLUMN/2+2, Color.YELLOW,
                PuzzleState.UNLOCKED);
        drawPuzzleRectangle(world, WALLS_IN_ROW/2-1, WALLS_IN_ROW/2+1, WALLS_IN_COLUMN/2-3,
                WALLS_IN_COLUMN/2+1, Color.GREEN, PuzzleState.LOCKED);

        world.addPuzzle(new Puzzle((WALLS_IN_ROW - 4 + 0.5f) * Wall.defaultSize,
                (WALLS_IN_COLUMN - 2 + 0.5f) * Wall.defaultSize, Color.BLUE, PuzzleState.LOCKED));
        world.addPuzzle(new Puzzle((WALLS_IN_ROW - 3 + 0.5f) * Wall.defaultSize,
                (WALLS_IN_COLUMN - 3 + 0.5f) * Wall.defaultSize, Color.BLUE, PuzzleState.LOCKED));
        world.addPuzzle(new Puzzle((WALLS_IN_ROW - 2 + 0.5f) * Wall.defaultSize,
                (WALLS_IN_COLUMN - 4 + 0.5f) * Wall.defaultSize, Color.BLUE, PuzzleState.LOCKED));

    }


    private void initBallGenerator(WorldInitialization world) {

        BallDescription description1 =
                new BallDescription(VelocityType.ANY_VELOCITY, Color.BLUE,
                        new Coordinates((1 + 0.5f) * Wall.defaultSize,
                                (WALLS_IN_COLUMN - 2 + 0.5f) * Wall.defaultSize));

        BallDescription description2 =
                new BallDescription(VelocityType.ANY_VELOCITY, Color.BLUE,
                        new Coordinates((WALLS_IN_ROW/2 + 0.5f) * Wall.defaultSize,
                                (WALLS_IN_COLUMN/2 + 0.5f) * Wall.defaultSize));

        BallDescription description3 =
                new BallDescription(VelocityType.ANY_VELOCITY, Color.BLUE,
                        new Coordinates((WALLS_IN_ROW/2 + 0.5f) * Wall.defaultSize,
                                (WALLS_IN_COLUMN/2 - 2 + 0.5f) * Wall.defaultSize));

        BallDescription description4 =
                new BallDescription(VelocityType.ANY_VELOCITY, Color.YELLOW,
                        new Coordinates((WALLS_IN_ROW/2 + 0.5f) * Wall.defaultSize,
                                (WALLS_IN_COLUMN - 2 + 0.5f) * Wall.defaultSize));


        initBallGenerator(world, VELOCITY_KOEF, MIN_VELOCITY, spawnInterval,
                description1, description2, description3, description4);
    }


    private void initHoles(WorldInitialization world) {

        world.addHole((new Hole((2) * Wall.defaultSize,
                (2) * Wall.defaultSize, Color.BLUE)));
        world.addHole((new Hole((WALLS_IN_ROW - 2) * Wall.defaultSize,
                (2) * Wall.defaultSize, Color.YELLOW)));
        world.addHole((new Hole((WALLS_IN_ROW - 2) * Wall.defaultSize,
                (WALLS_IN_COLUMN/2) * Wall.defaultSize, Color.GREEN)));

    }

}
