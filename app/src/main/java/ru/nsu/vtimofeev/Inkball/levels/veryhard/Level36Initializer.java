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
public class Level36Initializer extends LevelInitializer {

    private float spawnInterval = 4f;

    private static float MIN_VELOCITY = 4.5f;
    private static float VELOCITY_KOEF = 5.5f;

    public void initialize(WorldInitialization world) {

        initBorderWalls(world);
        initWalls(world);
        initHoles(world);
        initBallGenerator(world);
    }

    private void initWalls(WorldInitialization world) {

        drawBarLine(world, WALLS_IN_ROW - 4, WALLS_IN_ROW - 4, 1, WALLS_IN_COLUMN - 5,
                Color.NONE, Direction.RIGHT);
        world.addWall(new Wall((WALLS_IN_ROW - 4 + 0.5f) * Wall.defaultSize,
                (WALLS_IN_COLUMN - 4 + 0.5f) * Wall.defaultSize));

        world.addWall(new Wall((3 + 0.5f) * Wall.defaultSize,
                (WALLS_IN_COLUMN - 4 + 0.5f) * Wall.defaultSize));
        drawBarLine(world, 4, WALLS_IN_ROW - 5, WALLS_IN_COLUMN - 4, WALLS_IN_COLUMN - 4,
                Color.NONE, Direction.UP);

        world.addWall(new Wall((3 + 0.5f) * Wall.defaultSize,
                (3 + 0.5f) * Wall.defaultSize));
        drawBarLine(world, 3, 3, 4, WALLS_IN_COLUMN - 5,
                Color.NONE, Direction.LEFT);

        world.addWall(new Wall((WALLS_IN_ROW - 7 + 0.5f) * Wall.defaultSize,
                (3 + 0.5f) * Wall.defaultSize));
        drawBarLine(world, 4, WALLS_IN_ROW - 8, 3, 3,
                Color.NONE, Direction.DOWN);

        world.addWall(new Wall((WALLS_IN_ROW - 7 + 0.5f) * Wall.defaultSize,
                (WALLS_IN_COLUMN - 7 + 0.5f) * Wall.defaultSize));
        drawBarLine(world, WALLS_IN_ROW - 7, WALLS_IN_ROW - 7, 4, WALLS_IN_COLUMN - 8,
                Color.NONE, Direction.RIGHT);

    }

    private void initBallGenerator(WorldInitialization world) {

        float generatorX = (WALLS_IN_ROW - 2 + 0.5f) * Wall
                .defaultSize;
        float generatorY = (1 + 0.5f) * Wall.defaultSize;

        BallDescription description1 =
                new BallDescription(VelocityType.ANY_VELOCITY_DEFAULT_COORDINATES, Color.YELLOW);

        BallDescription description2 =
                new BallDescription(VelocityType.ANY_VELOCITY_DEFAULT_COORDINATES, Color.YELLOW);

        BallDescription description3 =
                new BallDescription(VelocityType.ANY_VELOCITY_DEFAULT_COORDINATES, Color.YELLOW);

        initBallGenerator(world, generatorX,
                generatorY, VELOCITY_KOEF, MIN_VELOCITY, spawnInterval,
                description1, description2, description3);
    }



    private void initHoles(WorldInitialization world) {

        world.addHole((new Hole((WALLS_IN_ROW - 8) * Wall.defaultSize,
                (WALLS_IN_COLUMN - 8) * Wall.defaultSize, Color.YELLOW)));
    }
}
