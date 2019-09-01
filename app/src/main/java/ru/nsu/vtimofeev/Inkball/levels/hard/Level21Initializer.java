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
public class Level21Initializer extends LevelInitializer {

    private float spawnInterval = 4f;

    private static float MIN_VELOCITY = 4.0f;
    private static float VELOCITY_KOEF = 5.0f;

    public void initialize(WorldInitialization world) {

        initBorderWalls(world);
        initWalls(world);
        initHoles(world);
        initBallGenerator(world);
    }


    private void initWalls(WorldInitialization world) {
        
        drawBarLine(world, 1, WALLS_IN_ROW/2 - 4, 3, 3, Color.NONE, Direction.DOWN);

        drawBarLine(world, 7, WALLS_IN_ROW - 1, WALLS_IN_COLUMN - 4, WALLS_IN_COLUMN - 4,
                Color.NONE,
                Direction.DOWN);
        drawBarLine(world, 6, 6, WALLS_IN_COLUMN - 7, WALLS_IN_COLUMN - 5,
                Color.NONE,
                Direction.RIGHT);
    }


    private void initBallGenerator(WorldInitialization world) {

        float generatorX = (1 + 0.5f) * Wall.defaultSize;
        float generatorY = (2 + 0.5f) * Wall.defaultSize;

        BallDescription description1 =
                new BallDescription(VelocityType.ANY_VELOCITY_DEFAULT_COORDINATES, Color.GREEN);

        BallDescription description2 =
                new BallDescription(VelocityType.ANY_VELOCITY_DEFAULT_COORDINATES, Color.GREEN);

        BallDescription description3 =
                new BallDescription(VelocityType.ANY_VELOCITY_DEFAULT_COORDINATES, Color.GREEN);

        initBallGenerator(world, generatorX,
                generatorY, VELOCITY_KOEF, MIN_VELOCITY, spawnInterval,
                description1, description2, description3);

    }


    private void initHoles(WorldInitialization world) {
        world.addHole((new Hole((WALLS_IN_ROW - 2) * Wall.defaultSize,
                (WALLS_IN_COLUMN - 2) * Wall.defaultSize, Color.GREEN)));
    }

}
