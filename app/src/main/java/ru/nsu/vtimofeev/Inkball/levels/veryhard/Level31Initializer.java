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
public class Level31Initializer extends LevelInitializer {

    private float spawnInterval = 4f;

    private static float MIN_VELOCITY = 4.5f;
    private static float VELOCITY_KOEF = 5.5f;

    public void initialize(WorldInitialization world) {

        initBorderWalls(world);
        initWalls(world);
        initHoles(world);
        initBallGenerator(world);
    }

    @Override
    protected void initBorderWalls(WorldInitialization world) {

        drawWallLine(world, -1, -1, 0, WALLS_IN_COLUMN - 1, Color.NONE, false);
        drawWallLine(world, WALLS_IN_ROW, WALLS_IN_ROW, 0, WALLS_IN_COLUMN - 1, Color.NONE, false);

        drawWallLine(world, 0, WALLS_IN_ROW - 1, -1, -1, Color.NONE, false);
        drawWallLine(world, 0, WALLS_IN_ROW - 1, WALLS_IN_COLUMN, WALLS_IN_COLUMN, Color.NONE, false);
    }

    private void initWalls(WorldInitialization world) {

        drawWallLine(world, 0, 0, 3, 3, Color.YELLOW, false);
        drawWallLine(world, 1, 1, 3, 3, Color.ORANGE, false);
        drawWallLine(world, 2, 2, 3, 3, Color.BLUE, false);

        drawWallLine(world, 3, 4, 3, 3, Color.NONE, false);
        drawWallLine(world, 3, 4, 0, 2, Color.ORANGE, true);

        drawWallLine(world, 4, 4, WALLS_IN_COLUMN - 1, WALLS_IN_COLUMN - 1, Color.YELLOW, false);
        drawWallLine(world, 4, 4, WALLS_IN_COLUMN - 2, WALLS_IN_COLUMN - 2, Color.ORANGE, false);
        drawWallLine(world, 4, 4, WALLS_IN_COLUMN - 3, WALLS_IN_COLUMN - 3, Color.BLUE, false);

        drawWallLine(world, 4, 4, WALLS_IN_COLUMN - 5, WALLS_IN_COLUMN - 4, Color.NONE, false);
        drawWallLine(world, 12, 12, WALLS_IN_COLUMN - 1, WALLS_IN_COLUMN - 1, Color.ORANGE, false);

        drawWallLine(world, WALLS_IN_ROW - 5, WALLS_IN_ROW - 2, WALLS_IN_COLUMN - 4,
                WALLS_IN_COLUMN - 4,
                Color.NONE, false);
        drawWallLine(world, WALLS_IN_ROW - 5, WALLS_IN_ROW - 4, WALLS_IN_COLUMN - 3,
                WALLS_IN_COLUMN - 1, Color.BLUE, true);
        drawWallLine(world, WALLS_IN_ROW - 1, WALLS_IN_ROW - 1, WALLS_IN_COLUMN - 4,
                WALLS_IN_COLUMN - 4, Color.BLUE, false);

        drawWallLine(world, WALLS_IN_ROW - 4, WALLS_IN_ROW - 4, 0,
                4,
                Color.NONE, false);
        drawWallLine(world, WALLS_IN_ROW - 3, WALLS_IN_ROW - 1, 3,
                4, Color.YELLOW, true);
        drawWallLine(world, WALLS_IN_ROW - 12, WALLS_IN_ROW - 12, 0,
                0, Color.YELLOW, false);

    }


    private void initBallGenerator(WorldInitialization world) {

        float generatorX = (0 + 0.5f) * Wall.defaultSize;
        float generatorY = (WALLS_IN_COLUMN - 1 + 0.5f) * Wall
                .defaultSize;

        BallDescription description1 =
                new BallDescription(VelocityType.ANY_VELOCITY_DEFAULT_COORDINATES, Color.NONE);

        BallDescription description2 =
                new BallDescription(VelocityType.ANY_VELOCITY_DEFAULT_COORDINATES, Color.NONE);

        BallDescription description3 =
                new BallDescription(VelocityType.ANY_VELOCITY_DEFAULT_COORDINATES, Color.NONE);

        BallDescription description4 =
                new BallDescription(VelocityType.ANY_VELOCITY_DEFAULT_COORDINATES, Color.NONE);

        BallDescription description5 =
                new BallDescription(VelocityType.ANY_VELOCITY_DEFAULT_COORDINATES, Color.NONE);


        initBallGenerator(world, generatorX,
                generatorY, VELOCITY_KOEF, MIN_VELOCITY, spawnInterval,
                description1, description2, description3, description4, description5);

    }


    private void initHoles(WorldInitialization world) {

        world.addHole((new Hole((1) * Wall.defaultSize,
                (1) * Wall.defaultSize, Color.BLUE)));
        world.addHole((new Hole((WALLS_IN_ROW - 1) * Wall.defaultSize,
                (1) * Wall.defaultSize, Color.ORANGE)));
        world.addHole((new Hole((WALLS_IN_ROW - 1) * Wall.defaultSize,
                (WALLS_IN_COLUMN - 1) * Wall.defaultSize, Color.YELLOW)));
    }

}
