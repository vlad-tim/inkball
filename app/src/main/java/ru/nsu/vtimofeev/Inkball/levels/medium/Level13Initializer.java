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
public class Level13Initializer extends LevelInitializer {

    private float spawnInterval = 0f;

    private static float MIN_VELOCITY = 3.4f;
    private static float VELOCITY_KOEF = 4.4f;

    public void initialize(WorldInitialization world) {

        initBorderWalls(world);
        initWalls(world) ;
        initHoles(world);
        initBallGenerator(world);
    }

    private void initWalls(WorldInitialization world) {

        world.addBar(new Bar((WALLS_IN_ROW/2 + 1 + 0.5f) * Wall.defaultSize,
                (WALLS_IN_COLUMN - 2 + 0.5f) * Wall.defaultSize, Color.ORANGE, BarAlignment.VERTICAL));
        world.addBar(new Bar((WALLS_IN_ROW/2 + 1 + 0.5f) * Wall.defaultSize,
                (WALLS_IN_COLUMN - 3 + 0.5f) * Wall.defaultSize, Color.ORANGE,
                BarAlignment.VERTICAL));

        drawWallLine(world, 4, (WALLS_IN_ROW - 1) - 4, WALLS_IN_COLUMN - 4, WALLS_IN_COLUMN - 4,
                Color.NONE, false);

        world.addWall(new Wall((1 + 0.5f)*Wall.defaultSize, (1 + 0.5f)*Wall.defaultSize));
        world.addWall(new Wall((WALLS_IN_ROW - 2 + 0.5f)*Wall.defaultSize,
                (1 + 0.5f)*Wall.defaultSize));
        world.addWall(new Wall((1 + 0.5f)*Wall.defaultSize, (WALLS_IN_COLUMN - 2 + 0.5f)*Wall
                .defaultSize));
        world.addWall(new Wall((WALLS_IN_ROW - 2 + 0.5f)*Wall.defaultSize,
                (WALLS_IN_COLUMN - 2 + 0.5f)*Wall.defaultSize));

        drawWallLine(world, 3, 3, 3, WALLS_IN_COLUMN - 5,
                Color.NONE, false);
        world.addWall(new Wall((4 + 0.5f)*Wall.defaultSize,
                (WALLS_IN_COLUMN - 5 + 0.5f)*Wall.defaultSize));

        drawWallLine(world, WALLS_IN_ROW / 2, WALLS_IN_ROW - 4,
                WALLS_IN_COLUMN - 5, WALLS_IN_COLUMN - 5, Color.NONE, false);
        drawWallLine(world, WALLS_IN_ROW / 2 + 1, WALLS_IN_ROW - 4,
                WALLS_IN_COLUMN - 6, WALLS_IN_COLUMN - 6, Color.NONE, false);
        drawWallLine(world, WALLS_IN_ROW / 2 + 3, WALLS_IN_ROW - 4,
                WALLS_IN_COLUMN - 7, WALLS_IN_COLUMN - 7, Color.NONE, false);
        drawWallLine(world, WALLS_IN_ROW / 2 + 4, WALLS_IN_ROW - 4,
                WALLS_IN_COLUMN - 8, WALLS_IN_COLUMN -8, Color.NONE, false);
        drawWallLine(world, WALLS_IN_ROW / 2 + 6, WALLS_IN_ROW - 4,
                WALLS_IN_COLUMN - 9, WALLS_IN_COLUMN -9, Color.NONE, false);
        drawWallLine(world, WALLS_IN_ROW / 2 + 7, WALLS_IN_ROW - 4,
                WALLS_IN_COLUMN - 10, WALLS_IN_COLUMN -10, Color.NONE, false);

        drawWallLine(world, WALLS_IN_ROW / 2 - 1, WALLS_IN_ROW/2 - 1,
                1, 3, Color.NONE, false);
        drawWallLine(world, WALLS_IN_ROW / 2, WALLS_IN_ROW/2,
                1, 4, Color.NONE, false);
        drawWallLine(world, WALLS_IN_ROW / 2 + 1, WALLS_IN_ROW/2 + 1,
                1, 3, Color.NONE, false);
        drawWallLine(world, WALLS_IN_ROW / 2 + 2, WALLS_IN_ROW/2 + 2,
                1, 2, Color.NONE, false);
        drawWallLine(world, WALLS_IN_ROW / 2 + 3, WALLS_IN_ROW/2 + 3,
                1, 1, Color.NONE, false);

    }

    private void initBallGenerator(WorldInitialization world) {


        BallDescription description1 =
                new BallDescription(VelocityType.ANY_VELOCITY, Color.BLUE,
                        new Coordinates((WALLS_IN_ROW/2 + 2 + 0.5f) * Wall.defaultSize,
                                (WALLS_IN_COLUMN - 3 + 0.5f) * Wall.defaultSize));


        initBallGenerator(world, VELOCITY_KOEF, MIN_VELOCITY, spawnInterval,
                description1);
    }

    private void initHoles(WorldInitialization world) {
        world.addHole(new Hole((WALLS_IN_ROW / 2) * Wall.defaultSize,
                (WALLS_IN_COLUMN - 2f) * Wall.defaultSize, Color.BLUE));
    }

}
