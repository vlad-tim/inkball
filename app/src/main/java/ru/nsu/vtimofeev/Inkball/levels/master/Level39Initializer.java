package ru.nsu.vtimofeev.Inkball.levels.master;

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
public class Level39Initializer extends LevelInitializer {

    private float spawnInterval = 5f;

    private static float MIN_VELOCITY = 7.5f;
    private static float VELOCITY_KOEF = 8.5f;

    public void initialize(WorldInitialization world) {

        initBorderWalls(world);
        initWalls(world);
        initHoles(world);
        initBallGenerator(world);

        world.setTime(60f);

    }


    private void initWalls(WorldInitialization world) {

        drawWallLine(world, 3, 8, 5, 5, Color.NONE, false);
        drawWallLine(world, 5, 5, 3, 4, Color.NONE, false);
        drawWallLine(world, 5, 5, 6, 8, Color.NONE, false);

        drawWallLine(world, WALLS_IN_ROW - 9, WALLS_IN_ROW - 4, WALLS_IN_COLUMN - 6,
                WALLS_IN_COLUMN - 6, Color.NONE, false);
        drawWallLine(world, WALLS_IN_ROW - 6, WALLS_IN_ROW - 6, WALLS_IN_COLUMN - 5,
                WALLS_IN_COLUMN - 4, Color.NONE, false);
        drawWallLine(world, WALLS_IN_ROW - 6, WALLS_IN_ROW - 6, WALLS_IN_COLUMN - 9 ,
                WALLS_IN_COLUMN - 7, Color.NONE, false);



    }


    private void initBallGenerator(WorldInitialization world) {

        float generatorX = (1 + 0.5f) * Wall.defaultSize;
        float generatorY = (WALLS_IN_COLUMN - 2 + 0.5f) * Wall.defaultSize;

        BallDescription description1 =
                new BallDescription(VelocityType.ANY_VELOCITY_DEFAULT_COORDINATES, Color.BLUE);

        BallDescription description2 =
                new BallDescription(VelocityType.ANY_VELOCITY_DEFAULT_COORDINATES, Color.BLUE);

        BallDescription description3 =
                new BallDescription(VelocityType.ANY_VELOCITY_DEFAULT_COORDINATES, Color.ORANGE);

        BallDescription description4 =
                new BallDescription(VelocityType.ANY_VELOCITY_DEFAULT_COORDINATES, Color.YELLOW);

        BallDescription description5 =
                new BallDescription(VelocityType.ANY_VELOCITY_DEFAULT_COORDINATES, Color.ORANGE);

        BallDescription description6 =
                new BallDescription(VelocityType.ANY_VELOCITY_DEFAULT_COORDINATES, Color.GREEN);

        initBallGenerator(world, generatorX,
                generatorY, VELOCITY_KOEF, MIN_VELOCITY, spawnInterval,
                description1, description2, description3, description4, description5, description6);
    }


    private void initHoles(WorldInitialization world) {

        world.addHole((new Hole((4) * Wall.defaultSize,
                (7) * Wall.defaultSize, Color.ORANGE)));
        world.addHole((new Hole((7) * Wall.defaultSize,
                (4) * Wall.defaultSize, Color.GREEN)));

        world.addHole((new Hole((WALLS_IN_ROW - 4) * Wall.defaultSize,
                (WALLS_IN_COLUMN - 7) * Wall.defaultSize, Color.YELLOW)));
        world.addHole((new Hole((WALLS_IN_ROW - 7) * Wall.defaultSize,
                (WALLS_IN_COLUMN - 4) * Wall.defaultSize, Color.BLUE)));

    }

}
