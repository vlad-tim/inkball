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
public class Level44Initializer extends LevelInitializer {

    private float spawnInterval = 4f;

    private static float MIN_VELOCITY = 6.0f;
    private static float VELOCITY_KOEF = 7.0f;

    public void initialize(WorldInitialization world) {

        initBorderWalls(world);
        initWalls(world);
        initHoles(world);
        initBallGenerator(world);


    }

    @Override
    protected void initBorderWalls(WorldInitialization world) {

        drawWallLine(world, -1, -1, 0, WALLS_IN_COLUMN - 1, Color.NONE, false);
        drawWallLine(world, WALLS_IN_ROW, WALLS_IN_ROW, 0, WALLS_IN_COLUMN - 1, Color.NONE, false);

        drawWallLine(world, 0, WALLS_IN_ROW - 1, -1, -1, Color.NONE, false);
        drawWallLine(world, 0, WALLS_IN_ROW - 1, WALLS_IN_COLUMN, WALLS_IN_COLUMN, Color.NONE, false);
    }

    private void initWalls(WorldInitialization world) {

        drawWallLine(world, 0, WALLS_IN_ROW/2 + 3, WALLS_IN_COLUMN - 7, WALLS_IN_COLUMN - 7,
                Color.NONE, false);
        drawWallLine(world, WALLS_IN_ROW/2 + 3, WALLS_IN_ROW/2 + 3, WALLS_IN_COLUMN - 6,
                WALLS_IN_COLUMN - 4, Color.NONE, false);
        drawWallLine(world, WALLS_IN_ROW/2 - 1, WALLS_IN_ROW/2 - 1, WALLS_IN_COLUMN - 6,
                WALLS_IN_COLUMN - 4, Color.NONE, false);

        drawWallLine(world, 8, 8, 0, 1, Color.NONE, false);
        drawWallLine(world, 8, 8, 4, WALLS_IN_COLUMN - 8, Color.NONE, false);

        drawWallLine(world, 2, 3, WALLS_IN_COLUMN - 4, WALLS_IN_COLUMN - 1, Color.GREEN, true);
        drawWallLine(world, 4, 9, WALLS_IN_COLUMN - 4, WALLS_IN_COLUMN - 3, Color.GREEN, true);

        world.addWall(new Wall((WALLS_IN_ROW - 2 + 0.5f) * Wall.defaultSize,
                (WALLS_IN_COLUMN - 9 + 0.5f) * Wall.defaultSize));
        world.addWall(new Wall((WALLS_IN_ROW - 2 + 0.5f) * Wall.defaultSize,
                (WALLS_IN_COLUMN - 3 + 0.5f) * Wall.defaultSize));
        world.addWall(new Wall((WALLS_IN_ROW/2 + 6 + 0.5f) * Wall.defaultSize,
                (WALLS_IN_COLUMN - 5 + 0.5f) * Wall.defaultSize));
    }



    private void initBallGenerator(WorldInitialization world) {

        float generatorX = (0 + 0.5f) * Wall
                .defaultSize;
        float generatorY = (2 + 0.5f) * Wall.defaultSize;

        BallDescription description1 =
                new BallDescription(VelocityType.ANY_VELOCITY_DEFAULT_COORDINATES, Color.GREEN);

        BallDescription description2 =
                new BallDescription(VelocityType.ANY_VELOCITY_DEFAULT_COORDINATES, Color.ORANGE);

        // x12
        initBallGenerator(world, generatorX,
                generatorY, VELOCITY_KOEF, MIN_VELOCITY, spawnInterval,
                description1, description1, description1, description1, description1,
                description1, description1, description1, description1, description1,
                description1, description1, description2);

    }


    private void initHoles(WorldInitialization world) {

        world.addHole((new Hole((1) * Wall.defaultSize,
                (WALLS_IN_COLUMN - 1) * Wall.defaultSize, Color.GREEN)));
        world.addHole((new Hole((1) * Wall.defaultSize,
                (WALLS_IN_COLUMN - 3) * Wall.defaultSize, Color.GREEN)));

        world.addHole((new Hole((3) * Wall.defaultSize,
                (WALLS_IN_COLUMN - 5) * Wall.defaultSize, Color.GREEN)));
        world.addHole((new Hole((5) * Wall.defaultSize,
                (WALLS_IN_COLUMN - 5) * Wall.defaultSize, Color.GREEN)));
        world.addHole((new Hole((7) * Wall.defaultSize,
                (WALLS_IN_COLUMN - 5) * Wall.defaultSize, Color.GREEN)));
        world.addHole((new Hole((9) * Wall.defaultSize,
                (WALLS_IN_COLUMN - 5) * Wall.defaultSize, Color.ORANGE)));

    }

}
