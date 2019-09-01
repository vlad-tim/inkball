package ru.nsu.vtimofeev.Inkball.levels.medium;

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
public class Level16Initializer extends LevelInitializer {

    private float spawnInterval = 4f;

    private static float MIN_VELOCITY = 4.0f;
    private static float VELOCITY_KOEF = 3.5f;

    public void initialize(WorldInitialization world) {

        initBorderWalls(world);
        initWalls(world) ;
        initHoles(world);
        initBallGenerator(world);
    }

    private void initWalls(WorldInitialization world) {

        drawWallLine(world, WALLS_IN_ROW/2 - 3, WALLS_IN_ROW/2 + 3 , 1, 1, Color.ORANGE, false);

        drawWallLine(world, WALLS_IN_ROW/2 - 4, WALLS_IN_ROW/2 - 3 , 2, 2, Color.ORANGE, false);
        drawWallLine(world, WALLS_IN_ROW/2 + 3, WALLS_IN_ROW/2  + 4 , 2, 2, Color.ORANGE, false);
        drawWallLine(world, WALLS_IN_ROW/2 - 2, WALLS_IN_ROW/2  + 2 , 2, 2, Color.NONE, false);

        drawWallLine(world, WALLS_IN_ROW/2 - 5, WALLS_IN_ROW/2 - 3 , 3, 3, Color.ORANGE, false);
        drawWallLine(world, WALLS_IN_ROW/2 + 3, WALLS_IN_ROW/2  + 5 , 3, 3, Color.ORANGE, false);
        drawWallLine(world, WALLS_IN_ROW/2 - 1, WALLS_IN_ROW/2  + 1 , 3, 3, Color.ORANGE, false);
        drawWallLine(world, WALLS_IN_ROW/2 - 2, WALLS_IN_ROW/2  - 2 , 3, 3, Color.NONE, false);
        drawWallLine(world, WALLS_IN_ROW/2 + 2, WALLS_IN_ROW/2  + 2 , 3, 3, Color.NONE, false);

        drawWallLine(world, WALLS_IN_ROW/2 - 5, WALLS_IN_ROW/2 + 5 , 4, 4, Color.ORANGE, false);

        drawWallLine(world, WALLS_IN_ROW/2 - 5, WALLS_IN_ROW/2 - 3 , 5, 5, Color.ORANGE, false);
        drawWallLine(world, WALLS_IN_ROW/2 + 3, WALLS_IN_ROW/2  + 5 , 5, 5, Color.ORANGE, false);
        drawWallLine(world, WALLS_IN_ROW/2 - 1, WALLS_IN_ROW/2  + 1 , 5, 5, Color.ORANGE, false);
        drawWallLine(world, WALLS_IN_ROW/2 - 2, WALLS_IN_ROW/2  - 2 , 5, 5, Color.NONE, false);
        drawWallLine(world, WALLS_IN_ROW/2 + 2, WALLS_IN_ROW/2  + 2 , 5, 5, Color.NONE, false);

        drawWallLine(world, WALLS_IN_ROW/2 - 4, WALLS_IN_ROW/2 + 5 , 6, 6, Color.ORANGE, false);

        drawWallLine(world, WALLS_IN_ROW/2 - 3, WALLS_IN_ROW/2 - 1 , 7, 7, Color.ORANGE, false);
        drawWallLine(world, WALLS_IN_ROW/2 + 2, WALLS_IN_ROW/2 + 4 , 7, 7, Color.ORANGE, false);
        drawWallLine(world, WALLS_IN_ROW/2 , WALLS_IN_ROW/2 + 1 , 7, 7, Color.GREEN, false);

        drawWallLine(world, WALLS_IN_ROW/2 , WALLS_IN_ROW/2 , 8, 8, Color.GREEN, false);
        drawWallLine(world, WALLS_IN_ROW/2 , WALLS_IN_ROW/2 + 1 , 9, 9, Color.GREEN, false);
    }

    private void initBallGenerator(WorldInitialization world) {


        float generatorX = (1  + 0.5f) * Wall.defaultSize;
        float generatorY = (WALLS_IN_COLUMN/2 + 0.5f) * Wall.defaultSize;

        BallDescription description1 =
                new BallDescription(VelocityType.ANY_VELOCITY_DEFAULT_COORDINATES, Color.BLUE);

        BallDescription description2 =
                new BallDescription(VelocityType.ANY_VELOCITY_DEFAULT_COORDINATES, Color.BLUE);

        BallDescription description3 =
                new BallDescription(VelocityType.ANY_VELOCITY_DEFAULT_COORDINATES, Color.BLUE);

        initBallGenerator(world, generatorX,
                generatorY, VELOCITY_KOEF, MIN_VELOCITY, spawnInterval,
                description1, description2, description3);

    }


    private void initHoles(WorldInitialization world) {
        world.addHole(new Hole((WALLS_IN_ROW - 2) * Wall.defaultSize,
                (WALLS_IN_COLUMN - 2) * Wall.defaultSize, Color.GREEN));
        world.addHole(new Hole((WALLS_IN_ROW - 2) * Wall.defaultSize,
                (2f) * Wall.defaultSize, Color.ORANGE));
    }
}
