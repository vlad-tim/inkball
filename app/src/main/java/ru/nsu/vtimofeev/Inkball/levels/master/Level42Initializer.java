package ru.nsu.vtimofeev.Inkball.levels.master;

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
public class Level42Initializer extends LevelInitializer {

    private float spawnInterval = 0f;

    private static float MIN_VELOCITY = 4.2f;
    private static float VELOCITY_KOEF = 4.2f;

    public void initialize(WorldInitialization world) {

        initBorderWalls(world);
        initWalls(world);
        initHoles(world);
        initBallGenerator(world);

        world.setTime(50f);
    }

    @Override
    protected void initBorderWalls(WorldInitialization world) {

        drawWallLine(world, -1, -1, 0, WALLS_IN_COLUMN - 1, Color.NONE, false);
        drawWallLine(world, WALLS_IN_ROW, WALLS_IN_ROW, 0, WALLS_IN_COLUMN - 1, Color.NONE, false);

        drawWallLine(world, 0, WALLS_IN_ROW - 1, -1, -1, Color.NONE, false);
        drawWallLine(world, 0, WALLS_IN_ROW - 1, WALLS_IN_COLUMN, WALLS_IN_COLUMN, Color.NONE, false);
    }

    private void initWalls(WorldInitialization world) {

        drawWallLine(world, 0, 7, WALLS_IN_COLUMN - 4, WALLS_IN_COLUMN - 4,
            Color.GREEN, false);
        drawWallLine(world, 7, 7, WALLS_IN_COLUMN - 3, WALLS_IN_COLUMN - 3,
                Color.GREEN, false);
        drawWallLine(world, 0, 6, WALLS_IN_COLUMN - 3, WALLS_IN_COLUMN - 3,
                Color.NONE, false);
        drawWallLine(world, 0, 5, WALLS_IN_COLUMN - 2, WALLS_IN_COLUMN - 1,
                Color.NONE, false);

        drawWallLine(world, WALLS_IN_ROW - 8, WALLS_IN_ROW - 8, WALLS_IN_COLUMN - 4,
                WALLS_IN_COLUMN - 1, Color.BLUE, false);
        drawWallLine(world, WALLS_IN_ROW - 7, WALLS_IN_ROW - 3, WALLS_IN_COLUMN - 4,
                WALLS_IN_COLUMN - 4, Color.BLUE, false);
        drawWallLine(world, WALLS_IN_ROW - 7, WALLS_IN_ROW - 1, WALLS_IN_COLUMN - 2,
                WALLS_IN_COLUMN - 1, Color.NONE, false);
        drawWallLine(world, WALLS_IN_ROW - 7, WALLS_IN_ROW - 3, WALLS_IN_COLUMN - 3,
                WALLS_IN_COLUMN - 3, Color.NONE, false);

        drawWallLine(world, 2, 7, 3, 3, Color.BLUE, false);
        drawWallLine(world, 7, 7, 0, 2, Color.BLUE, false);
        drawWallLine(world, 0, 7, 0, 1,Color.NONE, false);
        drawWallLine(world, 2, 7, 2, 2,Color.NONE, false);

        drawWallLine(world, WALLS_IN_ROW - 8, WALLS_IN_ROW - 8, 2, 2, Color.GREEN, false);
        drawWallLine(world, WALLS_IN_ROW - 8, WALLS_IN_ROW - 1, 3, 3, Color.GREEN, false);
        drawWallLine(world, WALLS_IN_ROW - 6, WALLS_IN_ROW - 1, 0, 1,Color.NONE, false);
        drawWallLine(world, WALLS_IN_ROW - 7, WALLS_IN_ROW - 1, 2, 2,Color.NONE, false);

    }



    private void initBallGenerator(WorldInitialization world) {

        float velCoefY = MIN_VELOCITY;
        float velCoefX = velCoefY * (WALLS_IN_ROW* Wall.defaultSize - 2f* Ball.radius)
         / (WALLS_IN_COLUMN * Wall.defaultSize - 2f* Ball.radius);


        BallDescription description1 =
                new BallDescription(VelocityType.FIXED_VELOCITY, Color.YELLOW,
                        new Coordinates((8 + 0.5f) * Wall.defaultSize,
                                (WALLS_IN_COLUMN/2 + 0.5f) * Wall.defaultSize),
                        new Coordinates(0f, -velCoefY));

        BallDescription description2 =
                new BallDescription(VelocityType.FIXED_VELOCITY, Color.YELLOW,
                        new Coordinates((10 +0.5f) * Wall.defaultSize,
                                (WALLS_IN_COLUMN/2 + 0.5f) * Wall.defaultSize),
                        new Coordinates(0f, -velCoefY));

        BallDescription description3 =
                new BallDescription(VelocityType.FIXED_VELOCITY, Color.YELLOW,
                        new Coordinates((12 +0.5f) * Wall.defaultSize,
                                (WALLS_IN_COLUMN/2 + 0.5f) * Wall.defaultSize),
                        new Coordinates(0f, -velCoefY));

        //////////////////////////////////

        BallDescription description4 =
                new BallDescription(VelocityType.FIXED_VELOCITY, Color.YELLOW,
                        new Coordinates((0 + 0.5f) * Wall.defaultSize,
                                (4 + 0.5f) * Wall.defaultSize),
                        new Coordinates(velCoefX, 0f));

        BallDescription description5 =
                new BallDescription(VelocityType.FIXED_VELOCITY, Color.YELLOW,
                        new Coordinates((0 + 0.5f) * Wall.defaultSize,
                                (5 + 0.5f) * Wall.defaultSize),
                        new Coordinates(velCoefX, 0f));

        BallDescription description6 =
                new BallDescription(VelocityType.FIXED_VELOCITY, Color.YELLOW,
                        new Coordinates((0 + 0.5f) * Wall.defaultSize,
                                (6 + 0.5f) * Wall.defaultSize),
                        new Coordinates(velCoefX, 0f));

        BallDescription description7 =
                new BallDescription(VelocityType.FIXED_VELOCITY, Color.YELLOW,
                        new Coordinates((0 + 0.5f) * Wall.defaultSize,
                                (7 + 0.5f) * Wall.defaultSize),
                        new Coordinates(velCoefX, 0f));

        BallDescription description8 =
                new BallDescription(VelocityType.FIXED_VELOCITY, Color.YELLOW,
                        new Coordinates((0 + 0.5f) * Wall.defaultSize,
                                (8 + 0.5f) * Wall.defaultSize),
                        new Coordinates(velCoefX, 0f));


        initBallGenerator(world, VELOCITY_KOEF, MIN_VELOCITY, spawnInterval,
                description1, description2, description3, description4, description5,
                description6, description7, description8);
    }


    private void initHoles(WorldInitialization world) {

        world.addHole((new Hole((7) * Wall.defaultSize,
                (WALLS_IN_COLUMN - 1) * Wall.defaultSize, Color.BLUE)));
        world.addHole((new Hole((WALLS_IN_ROW - 1) * Wall.defaultSize,
                (WALLS_IN_COLUMN - 3) * Wall.defaultSize, Color.GREEN)));
        world.addHole((new Hole(1 * Wall.defaultSize,
                (3) * Wall.defaultSize, Color.GREEN)));
        world.addHole((new Hole((WALLS_IN_ROW - 7) * Wall.defaultSize,
                (1) * Wall.defaultSize, Color.BLUE)));

    }

}
