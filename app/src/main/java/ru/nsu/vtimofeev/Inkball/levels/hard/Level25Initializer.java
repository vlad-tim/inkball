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
public class Level25Initializer extends LevelInitializer {

    private float spawnInterval = 0f;

    private static float MIN_VELOCITY = 4.0f;
    private static float VELOCITY_KOEF = 5.0f;

    public void initialize(WorldInitialization world) {

        initBorderWalls(world);
        initAccels(world);
        initWalls(world);
        initHoles(world);
        initBallGenerator(world);
    }

    private void initAccels(WorldInitialization world) {
        world.addAccel(new Accelerator((2+0.5f) * Wall.defaultSize, (2+0.5f) * Wall.defaultSize,
                Direction.UP));
        world.addAccel(new Accelerator((WALLS_IN_ROW - 3 +0.5f) * Wall.defaultSize,
                (2+0.5f) * Wall.defaultSize,
                Direction.UP));

        world.addAccel(new Accelerator((WALLS_IN_ROW/2 + 0.5f) * Wall.defaultSize,
                (2+0.5f) * Wall.defaultSize,
                Direction.UP));
        world.addAccel(new Accelerator((WALLS_IN_ROW/2 - 1 +0.5f) * Wall.defaultSize,
                (2+0.5f) * Wall.defaultSize,
                Direction.UP));
        world.addAccel(new Accelerator((WALLS_IN_ROW/2 + 1 +0.5f) * Wall.defaultSize,
                (2+0.5f) * Wall.defaultSize,
                Direction.UP));
    }

    private void initWalls(WorldInitialization world) {

            
        drawWallLine(world, 1, WALLS_IN_ROW - 1, 5, 5,
                Color.BLUE, true);
        drawWallLine(world, 1, WALLS_IN_ROW - 1, 6, 6,
                Color.ORANGE, true);
        drawWallLine(world, 1, WALLS_IN_ROW - 1, 7, 7,
                Color.YELLOW, true);

    }


    private void initBallGenerator(WorldInitialization world) {

        BallDescription description1 =
                new BallDescription(VelocityType.ANY_VELOCITY, Color.BLUE,
                        new Coordinates((3 + 0.5f) * Wall.defaultSize, (2 + 0.5f) * Wall.defaultSize));

        BallDescription description2 =
                new BallDescription(VelocityType.ANY_VELOCITY, Color.YELLOW,
                        new Coordinates((8 + 0.5f) * Wall.defaultSize, (2 + 0.5f) * Wall.defaultSize));

        BallDescription description3 =
                new BallDescription(VelocityType.ANY_VELOCITY, Color.GREEN,
                        new Coordinates((13 + 0.5f) * Wall.defaultSize, (2 + 0.5f) * Wall.defaultSize));

        BallDescription description4 =
                new BallDescription(VelocityType.ANY_VELOCITY, Color.ORANGE,
                        new Coordinates((18 + 0.5f) * Wall.defaultSize, (2 + 0.5f) * Wall.defaultSize));


        initBallGenerator(world, VELOCITY_KOEF, MIN_VELOCITY, spawnInterval,
                description1, description2, description3, description4);

    }


    private void initHoles(WorldInitialization world) {
        world.addHole((new Hole((2) * Wall.defaultSize,
                (WALLS_IN_COLUMN - 2) * Wall.defaultSize, Color.ORANGE)));
        world.addHole((new Hole((WALLS_IN_ROW - 2) * Wall.defaultSize,
                (WALLS_IN_COLUMN - 2) * Wall.defaultSize, Color.BLUE)));
        world.addHole((new Hole((WALLS_IN_ROW /2 - 4) * Wall.defaultSize,
                (WALLS_IN_COLUMN - 2) * Wall.defaultSize, Color.GREEN)));
        world.addHole((new Hole((WALLS_IN_ROW /2 + 4) * Wall.defaultSize,
                (WALLS_IN_COLUMN - 2) * Wall.defaultSize, Color.YELLOW)));
    }

}
