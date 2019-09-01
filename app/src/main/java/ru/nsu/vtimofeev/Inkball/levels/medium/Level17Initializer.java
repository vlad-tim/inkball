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
public class Level17Initializer extends LevelInitializer {

    private float spawnInterval = 0f;

    private static float MIN_VELOCITY = 4.0f;
    private static float VELOCITY_KOEF = 5.0f;

    private static final String firstHint = "DRAWING LINES BEFOREHAND";
    private static final String secondHint = "IS HELPFUL SOMETIMES";

    @Override
    public String getFirstHint() {
        return firstHint;
    }

    @Override
    public String getSecondHint() {
        return secondHint;
    }


    public void initialize(WorldInitialization world) {

        initBorderWalls(world);
        initWalls(world) ;
        initHoles(world);
        initBallGenerator(world);
    }

    @Override
    public void initBorderWalls(WorldInitialization world) {
        drawWallLine(world, 0, 0, 0, WALLS_IN_COLUMN - 5, Color.BLUE, false);
        drawWallLine(world, 0, 0, WALLS_IN_COLUMN - 4, WALLS_IN_COLUMN - 1, Color.NONE, false);
        drawWallLine(world, 1, WALLS_IN_ROW - 5, WALLS_IN_COLUMN - 1, WALLS_IN_COLUMN - 1,
                Color.NONE, false);
        drawWallLine(world, WALLS_IN_ROW - 4, WALLS_IN_ROW - 1, WALLS_IN_COLUMN - 1,
                WALLS_IN_COLUMN - 1,
                Color.ORANGE, false);

        drawWallLine(world, 1, WALLS_IN_ROW - 1, 0, 0,
                Color.NONE, false);
        drawWallLine(world,WALLS_IN_ROW - 1, WALLS_IN_ROW - 1, 1, 4,
                Color.NONE, false);
        drawWallLine(world,WALLS_IN_ROW - 1, WALLS_IN_ROW - 1, 5, WALLS_IN_COLUMN - 2,
                Color.ORANGE, false);
    }

    private void initWalls(WorldInitialization world) {

        for (int j = 1; j <= WALLS_IN_COLUMN - 2; ++j) {

            if (j < 3 || j > 5) {
                world.addWall(new Wall((4 + 0.5f)*Wall.defaultSize, (j + 0.5f)*Wall
                        .defaultSize));
            }
        }

        for (int j = 1; j <= WALLS_IN_COLUMN - 2; ++j) {

            if (j < WALLS_IN_COLUMN - 5 || j > WALLS_IN_COLUMN - 3) {
                world.addWall(new Wall((WALLS_IN_ROW - 5 + 0.5f)*Wall.defaultSize, (j + 0.5f)*Wall
                        .defaultSize));
            }
        }
    }

    private void initBallGenerator(WorldInitialization world) {

        BallDescription description1 =
                new BallDescription(VelocityType.FIXED_VELOCITY, Color.ORANGE,
                        new Coordinates((WALLS_IN_ROW/2  + 0.5f) * Wall.defaultSize,
                                (7 + 0.5f) * Wall.defaultSize),
                        new Coordinates(MIN_VELOCITY, 0f));

        BallDescription description2 =
                new BallDescription(VelocityType.FIXED_VELOCITY, Color.BLUE,
                        new Coordinates((WALLS_IN_ROW/2  + 0.5f) * Wall.defaultSize,
                                (6 + 0.5f) * Wall.defaultSize),
                        new Coordinates(MIN_VELOCITY, 0f));


        initBallGenerator(world, VELOCITY_KOEF, MIN_VELOCITY, spawnInterval,
                description1, description2);
    }


    private void initHoles(WorldInitialization world) {
        world.addHole(new Hole((2) * Wall.defaultSize,
                (WALLS_IN_COLUMN - 2) * Wall.defaultSize, Color.ORANGE));
        world.addHole(new Hole((WALLS_IN_ROW - 2) * Wall.defaultSize,
                (2f) * Wall.defaultSize, Color.BLUE));
    }

}
