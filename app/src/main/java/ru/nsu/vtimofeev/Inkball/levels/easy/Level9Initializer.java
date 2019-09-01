package ru.nsu.vtimofeev.Inkball.levels.easy;

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
public class Level9Initializer extends LevelInitializer {

    private float spawnInterval = 0f;

    private static float MIN_VELOCITY = 4.2f;
    private static float VELOCITY_KOEF = 4.2f;


    public void initialize(WorldInitialization world) {

        initBorderWalls(world);
        initWalls(world) ;
        initHoles(world);
        initBallGenerator(world);
    }

    @Override
    protected void initBorderWalls(WorldInitialization world) {

        //Bound Walls
        for (int i = 1; i < WALLS_IN_COLUMN - 1; ++i) {
            world.addWall(new Wall(Wall.defaultSize / 2, (i + 0.5f) * Wall.defaultSize));
            world.addWall(new Wall((WALLS_IN_ROW - 0.5f) * Wall.defaultSize,
                    (i + 0.5f) * Wall.defaultSize));
        }
        for (int i = 1; i < WALLS_IN_ROW - 1; ++i) {
            world.addWall(new Wall((i + 0.5f) * Wall.defaultSize, Wall.defaultSize / 2));
            world.addWall(new Wall((i + 0.5f) * Wall.defaultSize, (WALLS_IN_COLUMN - 0.5f) * Wall.defaultSize));
        }

        world.addWall(new Wall((0.5f) * Wall.defaultSize, (0.5f) * Wall.defaultSize , Color.YELLOW));
        world.addWall(new Wall((0.5f) * Wall.defaultSize, (WALLS_IN_COLUMN- 0.5f) * Wall
                .defaultSize , Color.YELLOW));
        world.addWall(new Wall((WALLS_IN_ROW - 0.5f) * Wall.defaultSize, (0.5f) * Wall.defaultSize , Color.YELLOW));
        world.addWall(new Wall((WALLS_IN_ROW - 0.5f) * Wall.defaultSize,
                (WALLS_IN_COLUMN- 0.5f) * Wall.defaultSize , Color.YELLOW));
    }

    private void initWalls(WorldInitialization world) {

        for (int i = 1; i <5; ++i) {

            world.addWall(new Wall((i + 0.5f) * Wall.defaultSize,
                    (i + 0.5f) * Wall.defaultSize, Color.YELLOW));
            world.addWall(new Wall((i + 0.5f) * Wall.defaultSize,
                    (i + 1 + 0.5f) * Wall.defaultSize));
            world.addWall(new Wall((i + 1 + 0.5f) * Wall.defaultSize,
                    (i + 0.5f) * Wall.defaultSize));

            world.addWall(new Wall((WALLS_IN_ROW - 1 - i + 0.5f) * Wall.defaultSize,
                    (i + 0.5f) * Wall.defaultSize, Color.YELLOW));
            world.addWall(new Wall((WALLS_IN_ROW - 1 - i  - 1+ 0.5f) * Wall.defaultSize,
                    (i + 0.5f) * Wall.defaultSize));
            world.addWall(new Wall((WALLS_IN_ROW - 1 - i + 0.5f) * Wall.defaultSize,
                    (i + 1 + 0.5f) * Wall.defaultSize));

            world.addWall(new Wall((i + 0.5f) * Wall.defaultSize,
                    (WALLS_IN_COLUMN - 1 - i + 0.5f) * Wall.defaultSize, Color.YELLOW));
            world.addWall(new Wall((i + 1 + 0.5f) * Wall.defaultSize,
                    (WALLS_IN_COLUMN - 1 - i + 0.5f) * Wall.defaultSize));
            world.addWall(new Wall((i + 0.5f) * Wall.defaultSize,
                    (WALLS_IN_COLUMN - 1 - i - 1+ 0.5f) * Wall.defaultSize));

            world.addWall(new Wall((WALLS_IN_ROW - 1 - i + 0.5f) * Wall.defaultSize,
                    (WALLS_IN_COLUMN - 1 - i + 0.5f) * Wall.defaultSize, Color.YELLOW));
            world.addWall(new Wall((WALLS_IN_ROW - 1 - i - 1 + 0.5f) * Wall.defaultSize,
                    (WALLS_IN_COLUMN - 1 - i + 0.5f) * Wall.defaultSize));
            world.addWall(new Wall((WALLS_IN_ROW - 1 - i + 0.5f) * Wall.defaultSize,
                    (WALLS_IN_COLUMN - 1 - i - 1 + 0.5f) * Wall.defaultSize));

        }

    }

    private void initBallGenerator(WorldInitialization world) {

        BallDescription description1 =
                new BallDescription(VelocityType.FIXED_VELOCITY, Color.ORANGE,
                        new Coordinates((6 + 0.5f) * Wall.defaultSize,
                                (4 + 0.5f) * Wall.defaultSize),
                        new Coordinates(MIN_VELOCITY, 0f));

        BallDescription description2 =
                new BallDescription(VelocityType.FIXED_VELOCITY, Color.BLUE,
                        new Coordinates((6 + 0.5f) * Wall.defaultSize,
                                (8 + 0.5f) * Wall.defaultSize),
                        new Coordinates(MIN_VELOCITY, 0f));


        initBallGenerator(world, VELOCITY_KOEF, MIN_VELOCITY, spawnInterval,
                description1, description2);
    }

    private void initHoles(WorldInitialization world) {
        world.addHole((new Hole((WALLS_IN_ROW / 2) * Wall.defaultSize,
                (2) * Wall.defaultSize, Color.BLUE)));

        world.addHole((new Hole((WALLS_IN_ROW / 2 + 1) * Wall.defaultSize,
                (WALLS_IN_COLUMN - 2) * Wall.defaultSize, Color.ORANGE)));
    }
}
