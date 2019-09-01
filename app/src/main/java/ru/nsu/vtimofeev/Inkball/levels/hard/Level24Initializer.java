package ru.nsu.vtimofeev.Inkball.levels.hard;

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
public class Level24Initializer extends LevelInitializer {

    private float spawnInterval = 4f;

    private static float MIN_VELOCITY = 5.0f;
    private static float VELOCITY_KOEF = 6.0f;

    public void initialize(WorldInitialization world) {

        initBorderWalls(world);
        initUnstableWalls(world);
        initHoles(world);
        initBallGenerator(world);
    }

    @Override
    protected void initBorderWalls(WorldInitialization world) {

        drawWallLine(world, -1, -1, 0, WALLS_IN_COLUMN - 1, Color.NONE, false);
        drawWallLine(world, WALLS_IN_ROW, WALLS_IN_ROW, 0, WALLS_IN_COLUMN - 1, Color.NONE, false);

        drawWallLine(world, 0, WALLS_IN_ROW - 1, -1, - 1, Color.NONE, false);
        drawWallLine(world, 0, WALLS_IN_ROW - 1, WALLS_IN_COLUMN, WALLS_IN_COLUMN, Color.NONE, false);
    }


    private void initUnstableWalls(WorldInitialization world) {

        for (int j = 0; j <= WALLS_IN_COLUMN - 1; ++j) {
            world.addTimerWall(new TimerWall((7 + 0.5f) * Wall.defaultSize, (j + 0.5f) * Wall
                    .defaultSize));
        }

        for (int j = 0; j <= WALLS_IN_COLUMN - 1; ++j) {
            world.addTimerWall(new TimerWall((14 + 0.5f) * Wall.defaultSize,
                    (j + 0.5f) * Wall
                            .defaultSize));
        }

        for (int i =  0; i <= WALLS_IN_ROW - 1; ++i) {
            if (i % 7 != 0) {
                world.addTimerWall(new TimerWall((i + 0.5f) * Wall.defaultSize,
                        (6 + 0.5f) * Wall
                                .defaultSize));
            }
        }

        world.addTimerWall(new TimerWall((0 + 0.5f) * Wall.defaultSize,
                (WALLS_IN_COLUMN/2 + 0.5f) * Wall
                .defaultSize));
        world.addTimerWall(new TimerWall((WALLS_IN_ROW - 1 + 0.5f) * Wall.defaultSize,
                (WALLS_IN_COLUMN/2 + 0.5f) * Wall
                        .defaultSize));
    }

    private void initHoles(WorldInitialization world) {
        world.addHole((new Hole((WALLS_IN_ROW - 3f) * Wall.defaultSize,
                (WALLS_IN_COLUMN - 3f) * Wall.defaultSize, Color.BLUE)));

        world.addHole((new Hole((WALLS_IN_ROW - 3f) * Wall.defaultSize,
                (3f) * Wall.defaultSize, Color.GREEN)));

        world.addHole((new Hole((3f) * Wall.defaultSize,
                (WALLS_IN_COLUMN - 3f) * Wall.defaultSize, Color.BLUE)));

        world.addHole((new Hole((3f) * Wall.defaultSize,
                (3f) * Wall.defaultSize, Color.GREEN)));
    }

    private void initBallGenerator(WorldInitialization world) {


        float generatorX = World.WIDTH/2 * Wall.defaultSize;
        float generatorY =  3.5f * Wall.defaultSize;

        BallDescription description1 =
                new BallDescription(VelocityType.ANY_VELOCITY_DEFAULT_COORDINATES, getRandomBlueOrGreenColor());

        BallDescription description2 =
                new BallDescription(VelocityType.ANY_VELOCITY_DEFAULT_COORDINATES, getRandomBlueOrGreenColor());

        BallDescription description3 =
                new BallDescription(VelocityType.ANY_VELOCITY_DEFAULT_COORDINATES, getRandomBlueOrGreenColor());

        initBallGenerator(world, generatorX,
                generatorY, VELOCITY_KOEF, MIN_VELOCITY, spawnInterval,
                description1, description2, description3);
    }

    private Color getRandomBlueOrGreenColor() {
        if (random.nextBoolean()) {
            return Color.BLUE;
        }
        else {
            return Color.GREEN;
        }
    }
}
