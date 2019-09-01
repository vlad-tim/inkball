package ru.nsu.vtimofeev.Inkball.levels.master;

import android.util.FloatMath;
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
public class Level43Initializer extends LevelInitializer {

    private float spawnInterval = 4f;

    private static float MIN_VELOCITY = 8.0f;
    private static float VELOCITY_KOEF = 9.0f;

    public void initialize(WorldInitialization world) {

        initBorderWalls(world);
        initPuzzles(world);
        initHoles(world);
        initBallGenerator(world);

        world.setTime(50f);
    }


    private void initPuzzles(WorldInitialization world) {

        drawPuzzleLine(world, 1, 3, 3, 3, Color.YELLOW, PuzzleState.LOCKED);
        drawPuzzleLine(world, 3, 3, 1, 2, Color.YELLOW, PuzzleState.LOCKED);

        drawPuzzleLine(world, 1, 3, WALLS_IN_COLUMN - 4, WALLS_IN_COLUMN - 4, Color.YELLOW,
                PuzzleState.LOCKED);
        drawPuzzleLine(world, 3, 3, WALLS_IN_COLUMN - 3, WALLS_IN_COLUMN - 2, Color.YELLOW,
                PuzzleState.LOCKED);

        drawPuzzleLine(world, WALLS_IN_ROW - 4, WALLS_IN_ROW - 2, WALLS_IN_COLUMN - 4,
                WALLS_IN_COLUMN - 4, Color.YELLOW, PuzzleState.UNLOCKED);
        drawPuzzleLine(world, WALLS_IN_ROW - 4, WALLS_IN_ROW - 4, WALLS_IN_COLUMN - 3,
                WALLS_IN_COLUMN - 2, Color.YELLOW, PuzzleState.UNLOCKED);

        drawPuzzleLine(world, WALLS_IN_ROW - 4, WALLS_IN_ROW - 2, 3, 3, Color.YELLOW,
                PuzzleState.UNLOCKED);
        drawPuzzleLine(world, WALLS_IN_ROW - 4, WALLS_IN_ROW - 4, 1, 2, Color.YELLOW,
                PuzzleState.UNLOCKED);


        drawPuzzleLine(world, WALLS_IN_ROW/2f - 2, WALLS_IN_ROW/2 - 2, 1, 3,
                Color.BLUE, PuzzleState.LOCKED);
        drawPuzzleLine(world, WALLS_IN_ROW/2f + 1, WALLS_IN_ROW/2 + 1, 1, 3,
                Color.BLUE, PuzzleState.LOCKED);
        drawPuzzleLine(world, WALLS_IN_ROW/2f - 1, WALLS_IN_ROW/2, 3, 3,
                Color.BLUE, PuzzleState.LOCKED);

        drawPuzzleLine(world, WALLS_IN_ROW/2f - 2, WALLS_IN_ROW/2 - 2, WALLS_IN_COLUMN - 4,
                WALLS_IN_COLUMN - 2, Color.BLUE, PuzzleState.UNLOCKED);
        drawPuzzleLine(world, WALLS_IN_ROW/2f + 1, WALLS_IN_ROW/2 + 1, WALLS_IN_COLUMN - 4,
                WALLS_IN_COLUMN - 2, Color.BLUE, PuzzleState.UNLOCKED);
        drawPuzzleLine(world, WALLS_IN_ROW/2f - 1, WALLS_IN_ROW/2, WALLS_IN_COLUMN - 4,
                WALLS_IN_COLUMN - 4, Color.BLUE, PuzzleState.UNLOCKED);
    }



    private void initBallGenerator(WorldInitialization world) {

        float generatorX = (WALLS_IN_ROW / 2 + 0.5f) * Wall
                .defaultSize;
        float generatorY = (WALLS_IN_COLUMN / 2 + 0.5f) * Wall.defaultSize;

        BallDescription description1 =
                new BallDescription(VelocityType.ANY_VELOCITY_DEFAULT_COORDINATES, Color.ORANGE);

        BallDescription description2 =
                new BallDescription(VelocityType.ANY_VELOCITY_DEFAULT_COORDINATES, Color.YELLOW);

        BallDescription description3 =
                new BallDescription(VelocityType.ANY_VELOCITY_DEFAULT_COORDINATES, Color.BLUE);

        BallDescription description4 =
                new BallDescription(VelocityType.ANY_VELOCITY_DEFAULT_COORDINATES, Color.BLUE);

        BallDescription description5 =
                new BallDescription(VelocityType.ANY_VELOCITY_DEFAULT_COORDINATES, Color.YELLOW);

        BallDescription description6 =
                new BallDescription(VelocityType.ANY_VELOCITY_DEFAULT_COORDINATES, Color.ORANGE);

        initBallGenerator(world, generatorX,
                generatorY, VELOCITY_KOEF, MIN_VELOCITY, spawnInterval,
                description1, description2, description3, description4, description5, description6);
    }


    private void initHoles(WorldInitialization world) {

        world.addHole((new Hole((2) * Wall.defaultSize,
                (2) * Wall.defaultSize, Color.ORANGE)));
        world.addHole((new Hole((WALLS_IN_ROW - 2) * Wall.defaultSize,
                (WALLS_IN_COLUMN - 2) * Wall.defaultSize, Color.ORANGE)));

        world.addHole((new Hole((WALLS_IN_ROW/2f) * Wall.defaultSize,
                (2) * Wall.defaultSize, Color.BLUE)));
        world.addHole((new Hole((WALLS_IN_ROW/2f) * Wall.defaultSize,
                (WALLS_IN_COLUMN - 2) * Wall.defaultSize, Color.BLUE)));

        world.addHole((new Hole((2) * Wall.defaultSize,
                (WALLS_IN_COLUMN - 2) * Wall.defaultSize, Color.YELLOW)));
        world.addHole((new Hole((WALLS_IN_ROW - 2) * Wall.defaultSize,
                (2) * Wall.defaultSize, Color.YELLOW)));

    }

}
