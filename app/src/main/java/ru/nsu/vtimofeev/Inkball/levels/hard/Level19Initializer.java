package ru.nsu.vtimofeev.Inkball.levels.hard;

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
public class Level19Initializer extends LevelInitializer {

    private float spawnInterval = 0f;

    private static float MIN_VELOCITY = 2.5f;
    private static float VELOCITY_KOEF = 2.5f;

    public void initialize(WorldInitialization world) {

        initBorderWalls(world);
        initHoles(world);
        initBallGenerator(world);
    }


    private void initBallGenerator(WorldInitialization world) {

        BallDescription description1 =
                new BallDescription(VelocityType.FIXED_VELOCITY, Color.GREEN,
                        new Coordinates((3 + 0.5f) * Wall.defaultSize,
                                (1 + 0.5f) * Wall.defaultSize),
                        new Coordinates(0f, MIN_VELOCITY));

        BallDescription description2 =
                new BallDescription(VelocityType.FIXED_VELOCITY, Color.BLUE,
                        new Coordinates((8  + 0.5f) * Wall.defaultSize,
                                (1 + 0.5f) * Wall.defaultSize),
                        new Coordinates(0f, MIN_VELOCITY));

        BallDescription description3 =
                new BallDescription(VelocityType.FIXED_VELOCITY, Color.YELLOW,
                        new Coordinates((13  + 0.5f) * Wall.defaultSize,
                                (1 + 0.5f) * Wall.defaultSize),
                        new Coordinates(0f, MIN_VELOCITY));

        BallDescription description4 =
                new BallDescription(VelocityType.FIXED_VELOCITY, Color.ORANGE,
                        new Coordinates((18  + 0.5f) * Wall.defaultSize,
                                (1 + 0.5f) * Wall.defaultSize),
                        new Coordinates(0f, MIN_VELOCITY));


        initBallGenerator(world, VELOCITY_KOEF, MIN_VELOCITY, spawnInterval,
                description1, description2, description3, description4);
    }


    private void initHoles(WorldInitialization world) {
        world.addHole((new Hole((2) * Wall.defaultSize,
                (WALLS_IN_COLUMN - 2) * Wall.defaultSize, Color.ORANGE)));
        world.addHole((new Hole((4) * Wall.defaultSize,
                (WALLS_IN_COLUMN - 2) * Wall.defaultSize, Color.ORANGE)));

        world.addHole((new Hole((7) * Wall.defaultSize,
                (WALLS_IN_COLUMN - 2) * Wall.defaultSize, Color.GREEN)));
        world.addHole((new Hole((10) * Wall.defaultSize,
                (WALLS_IN_COLUMN - 2) * Wall.defaultSize, Color.GREEN)));

        world.addHole((new Hole((13) * Wall.defaultSize,
                (WALLS_IN_COLUMN - 2) * Wall.defaultSize, Color.BLUE)));
        world.addHole((new Hole((15) * Wall.defaultSize,
                (WALLS_IN_COLUMN - 2) * Wall.defaultSize, Color.BLUE)));

        world.addHole((new Hole((17) * Wall.defaultSize,
                (WALLS_IN_COLUMN - 2) * Wall.defaultSize, Color.YELLOW)));
        world.addHole((new Hole((20) * Wall.defaultSize,
                (WALLS_IN_COLUMN - 2) * Wall.defaultSize, Color.YELLOW)));
    }

}
