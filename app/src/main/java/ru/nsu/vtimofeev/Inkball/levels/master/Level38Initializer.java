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
public class Level38Initializer extends LevelInitializer {

    private float spawnInterval = 5f;

    private static float MIN_VELOCITY = 5.0f;
    private static float VELOCITY_KOEF = 6.0f;

    public void initialize(WorldInitialization world) {

        initBorderWalls(world);
        initWalls(world);
        initHoles(world);
        initBallGenerator(world);

        world.setTime(65f);
    }

    @Override
    protected void initBorderWalls(WorldInitialization world) {
        drawWallLine(world, 0, 0, 0, WALLS_IN_COLUMN - 4, Color.NONE, false);
        drawWallLine(world, 0, 0, WALLS_IN_COLUMN - 3, WALLS_IN_COLUMN - 1, Color.YELLOW, false);
        drawWallLine(world, 1, 2, WALLS_IN_COLUMN - 1, WALLS_IN_COLUMN - 1, Color.YELLOW, false);
        drawWallLine(world, 3, 9, WALLS_IN_COLUMN - 1, WALLS_IN_COLUMN - 1, Color.NONE, false);
        drawWallLine(world, 10, 12, WALLS_IN_COLUMN - 1, WALLS_IN_COLUMN - 1, Color.ORANGE, false);
        drawWallLine(world, 13, 18, WALLS_IN_COLUMN - 1, WALLS_IN_COLUMN - 1, Color.NONE, false);
        drawWallLine(world, 19, 21, WALLS_IN_COLUMN - 1, WALLS_IN_COLUMN - 1, Color.ORANGE, false);
        drawWallLine(world, 1, 3, 0, 0, Color.NONE, false);
        drawWallLine(world, 4, 6, 0, 0, Color.ORANGE, false);
        drawWallLine(world, 7, 21, 0, 0, Color.NONE, false);
        drawWallLine(world, WALLS_IN_ROW - 1, WALLS_IN_ROW - 1, 1, WALLS_IN_COLUMN - 2,
                Color.NONE, false);
    }

    private void initWalls(WorldInitialization world) {

        for (int i = 3, j = 7; i <=7; ++i, ++j) {
            world.addWall(new Wall((i + 0.5f) * Wall.defaultSize,
                    (j + 0.5f) * Wall.defaultSize));
        }

        for (int i = 2, j = 1; i <=9; ++i, ++j) {
            world.addWall(new Wall((i + 0.5f) * Wall.defaultSize,
                    (j + 0.5f) * Wall.defaultSize));
        }

        for (int i = 10, j = 4; i <=17; ++i, ++j) {
            world.addWall(new Wall((i + 0.5f) * Wall.defaultSize,
                    (j + 0.5f) * Wall.defaultSize));
        }

        for (int i = 12, j = 1; i <=17; ++i, ++j) {
            world.addWall(new Wall((i + 0.5f) * Wall.defaultSize,
                    (j + 0.5f) * Wall.defaultSize));
        }



    }


    private void initBallGenerator(WorldInitialization world) {

        float generatorX = (1 + 0.5f) * Wall.defaultSize;
        float generatorY = (WALLS_IN_COLUMN - 2 + 0.5f) * Wall.defaultSize;

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

        world.addHole((new Hole((15) * Wall.defaultSize,
                (2) * Wall.defaultSize, Color.YELLOW)));

    }

}
