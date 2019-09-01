package ru.nsu.vtimofeev.Inkball.levels.medium;

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
public class Level15Initializer extends LevelInitializer {

    private float spawnInterval = 0f;

    private static float MIN_VELOCITY = 4.0f;
    private static float VELOCITY_KOEF = 3.5f;

    public void initialize(WorldInitialization world) {

        initBorderWalls(world);
        initAccels(world) ;
        initHoles(world);
        initBallGenerator(world);
    }

    private void initAccels(WorldInitialization world) {

        world.addAccel(new Accelerator((4 + 0.5f) * Wall.defaultSize,
                (4 + 0.5f) * Wall.defaultSize,
                Direction.RIGHT));
        world.addAccel(new Accelerator((WALLS_IN_ROW - 5 + 0.5f) * Wall.defaultSize,
                (4 + 0.5f) * Wall.defaultSize,
                Direction.LEFT));
        world.addAccel(new Accelerator((3 + 0.5f) * Wall.defaultSize,
                (4 + 0.5f) * Wall.defaultSize,
                Direction.RIGHT));
        world.addAccel(new Accelerator((WALLS_IN_ROW - 4 + 0.5f) * Wall.defaultSize,
                (4 + 0.5f) * Wall.defaultSize,
                Direction.LEFT));

        world.addAccel(new Accelerator((4 + 0.5f) * Wall.defaultSize,
                (WALLS_IN_COLUMN - 5 + 0.5f) * Wall.defaultSize,
                Direction.RIGHT));
        world.addAccel(new Accelerator((WALLS_IN_ROW - 5 + 0.5f) * Wall.defaultSize,
                (WALLS_IN_COLUMN - 5 + 0.5f) * Wall.defaultSize,
                Direction.LEFT));
        world.addAccel(new Accelerator((3 + 0.5f) * Wall.defaultSize,
                (WALLS_IN_COLUMN - 5 + 0.5f) * Wall.defaultSize,
                Direction.RIGHT));
        world.addAccel(new Accelerator((WALLS_IN_ROW - 4 + 0.5f) * Wall.defaultSize,
                (WALLS_IN_COLUMN - 5 + 0.5f) * Wall.defaultSize,
                Direction.LEFT));


    }

    private void initBallGenerator(WorldInitialization world) {

        BallDescription description1 =
                new BallDescription(VelocityType.FIXED_VELOCITY, Color.ORANGE,
                        new Coordinates((WALLS_IN_ROW/2  + 0.5f) * Wall.defaultSize,
                                (4 + 0.5f) * Wall.defaultSize),
                        new Coordinates(MIN_VELOCITY, 0f));

        BallDescription description2 =
                new BallDescription(VelocityType.FIXED_VELOCITY, Color.BLUE,
                        new Coordinates((WALLS_IN_ROW/2  + 0.5f) * Wall.defaultSize,
                                (WALLS_IN_COLUMN - 5 + 0.5f) * Wall.defaultSize),
                        new Coordinates(MIN_VELOCITY, 0f));


        initBallGenerator(world, VELOCITY_KOEF, MIN_VELOCITY, spawnInterval,
                description1, description2);

    }

    private void initHoles(WorldInitialization world) {
        world.addHole(new Hole((WALLS_IN_ROW/2) * Wall.defaultSize,
                (WALLS_IN_COLUMN - 2) * Wall.defaultSize, Color.ORANGE));
        world.addHole(new Hole((WALLS_IN_ROW/2) * Wall.defaultSize,
                (2f) * Wall.defaultSize, Color.BLUE));
    }

}
