package ru.nsu.vtimofeev.Inkball.levels.veryhard;

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
public class Level30Initializer extends LevelInitializer {

    private float spawnInterval = 3f;

    private static float MIN_VELOCITY = 3.0f;
    private static float VELOCITY_KOEF = 3.0f;

    public void initialize(WorldInitialization world) {

        initBorderWalls(world);
        initWalls(world);
        initAccels(world);
        initHoles(world);
        initBallGenerator(world);
    }

    private void initWalls(WorldInitialization world) {

        drawWallLine(world, 3, WALLS_IN_ROW - 4, 3, 3, Color.NONE, false);
        drawWallLine(world, 3, 3, 4, WALLS_IN_COLUMN - 4, Color.NONE, false);
        drawWallLine(world, WALLS_IN_ROW - 4, WALLS_IN_ROW - 4, 4, WALLS_IN_COLUMN - 4,
                Color.NONE, false);

        drawWallLine(world, 4, WALLS_IN_ROW/2 - 1, WALLS_IN_COLUMN - 4, WALLS_IN_COLUMN - 4,
                Color.NONE, false);
        drawWallLine(world, WALLS_IN_ROW/2 + 1, WALLS_IN_ROW - 5, WALLS_IN_COLUMN - 4,
                WALLS_IN_COLUMN - 4,
                Color.NONE, false);
    }


    private void initAccels(WorldInitialization world) {

        drawAccelLine(world, 1, 2, 2, WALLS_IN_COLUMN - 4, Direction.UP);
        drawAccelLine(world, 2, WALLS_IN_ROW - 4, WALLS_IN_COLUMN - 3, WALLS_IN_COLUMN - 2,
                Direction.RIGHT);
        drawAccelLine(world, WALLS_IN_ROW - 3, WALLS_IN_ROW - 2, 3 , WALLS_IN_COLUMN - 3,
                Direction.DOWN);
        drawAccelLine(world, 3, WALLS_IN_ROW - 3, 1, 2,
                Direction.LEFT);


        drawAccelLine(world, 1, 1, WALLS_IN_COLUMN - 3, WALLS_IN_COLUMN - 3, Direction.UP);
        drawAccelLine(world, 1, 1, 1, 1, Direction.UP);

        drawAccelLine(world, 1, 1, WALLS_IN_COLUMN - 2,
                WALLS_IN_COLUMN - 2, Direction.RIGHT);
        drawAccelLine(world, WALLS_IN_ROW - 2, WALLS_IN_ROW - 2, WALLS_IN_COLUMN - 1,
                WALLS_IN_COLUMN - 1, Direction.RIGHT);

        drawAccelLine(world, WALLS_IN_ROW - 2, WALLS_IN_ROW - 2, 2,
                2, Direction.DOWN);

        drawAccelLine(world, WALLS_IN_ROW - 2, WALLS_IN_ROW - 2, 1,
                1, Direction.LEFT);
        drawAccelLine(world, 2, 2, 1,
                1, Direction.LEFT);


    }


    private void initBallGenerator(WorldInitialization world) {

        float generatorX = (WALLS_IN_ROW - 2 + 0.5f) * Wall.defaultSize;
        float generatorY = (WALLS_IN_COLUMN - 2 + 0.5f) * Wall.defaultSize;

        BallDescription description1 =
                new BallDescription(VelocityType.ANY_VELOCITY_DEFAULT_COORDINATES, Color.ORANGE);

        BallDescription description2 =
                new BallDescription(VelocityType.ANY_VELOCITY_DEFAULT_COORDINATES, Color.ORANGE);


        initBallGenerator(world, generatorX,
                generatorY, VELOCITY_KOEF, MIN_VELOCITY, spawnInterval,
                description1, description2 , description1, description2);

    }


    private void initHoles(WorldInitialization world) {
//
        world.addHole((new Hole((5) * Wall.defaultSize,
                (5) * Wall.defaultSize, Color.ORANGE)));

        world.addHole((new Hole((5) * Wall.defaultSize,
                (WALLS_IN_COLUMN - 5) * Wall.defaultSize, Color.ORANGE)));

        world.addHole((new Hole((WALLS_IN_ROW - 5) * Wall.defaultSize,
                (5) * Wall.defaultSize, Color.ORANGE)));

        world.addHole((new Hole((WALLS_IN_ROW - 5) * Wall.defaultSize,
                (WALLS_IN_COLUMN - 5) * Wall.defaultSize, Color.ORANGE)));
//
    }

}
