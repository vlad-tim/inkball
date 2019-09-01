package ru.nsu.vtimofeev.Inkball.levels.veryhard;

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
public class Level34Initializer extends LevelInitializer {

    private float spawnInterval = 0f;

    private static float MIN_VELOCITY = 3.5f;
    private static float VELOCITY_KOEF = 3.5f;

    public void initialize(WorldInitialization world) {

        initBorderWalls(world);
        initTimerWalls(world);
        initHoles(world);
        initBallGenerator(world);
    }

    private void initTimerWalls(WorldInitialization world) {

        world.addWall(new Wall((WALLS_IN_ROW / 2) * Wall.defaultSize,
                (WALLS_IN_COLUMN / 2 + 0.5f) * Wall.defaultSize));

        world.addTimerWall(new TimerWall((3 + 0.5f) * Wall.defaultSize,
                (3 + 0.5f) * Wall.defaultSize));
        world.addTimerWall(new TimerWall((WALLS_IN_ROW - 4 + 0.5f) * Wall.defaultSize,
                (3 + 0.5f) * Wall.defaultSize));
        world.addTimerWall(new TimerWall((3 + 0.5f) * Wall.defaultSize,
                (WALLS_IN_COLUMN - 4 + 0.5f) * Wall.defaultSize));
        world.addTimerWall(new TimerWall((WALLS_IN_ROW - 4 + 0.5f) * Wall.defaultSize,
                (WALLS_IN_COLUMN - 4 + 0.5f) * Wall.defaultSize));



        world.addTimerWall((new TimerWall((WALLS_IN_ROW / 2 - 5) * Wall.defaultSize,
                (WALLS_IN_COLUMN / 2 - 4.5f) * Wall.defaultSize)));
        world.addTimerWall((new TimerWall((WALLS_IN_ROW / 2 - 5) * Wall.defaultSize,
                (WALLS_IN_COLUMN / 2 + 5.5f) * Wall.defaultSize)));
        world.addTimerWall((new TimerWall((WALLS_IN_ROW / 2 + 5) * Wall.defaultSize,
                (WALLS_IN_COLUMN / 2 + 5.5f) * Wall.defaultSize)));
        world.addTimerWall((new TimerWall((WALLS_IN_ROW / 2 + 5) * Wall.defaultSize,
                (WALLS_IN_COLUMN / 2 - 4.5f) * Wall.defaultSize)));

    }


    private void initBallGenerator(WorldInitialization world) {

        float koefY = MIN_VELOCITY;
        float koefX = koefY / ((WALLS_IN_COLUMN - 3)*Wall.defaultSize - Ball.radius*4f)
                * ((WALLS_IN_ROW - 3)*Wall.defaultSize - Ball.radius*4f);
        BallDescription description1 =
                new BallDescription(VelocityType.FIXED_VELOCITY, Color.ORANGE,
                        new Coordinates((WALLS_IN_ROW / 2 - 1) * Wall.defaultSize,
                                (WALLS_IN_COLUMN / 2 + 0.5f) * Wall.defaultSize),
                        new Coordinates(-koefX, 0f));

        BallDescription description2 =
                new BallDescription(VelocityType.FIXED_VELOCITY, Color.GREEN,
                        new Coordinates((WALLS_IN_ROW / 2 + 1) * Wall.defaultSize,
                                (WALLS_IN_COLUMN / 2 + 0.5f) * Wall.defaultSize),
                        new Coordinates(koefX, 0f));

        BallDescription description3 =
                new BallDescription(VelocityType.FIXED_VELOCITY, Color.BLUE,
                        new Coordinates((WALLS_IN_ROW / 2) * Wall.defaultSize,
                                (WALLS_IN_COLUMN / 2 - 1 + 0.5f) * Wall.defaultSize),
                        new Coordinates(0f, -koefY));

        BallDescription description4 =
                new BallDescription(VelocityType.FIXED_VELOCITY, Color.YELLOW,
                        new Coordinates((WALLS_IN_ROW / 2) * Wall.defaultSize,
                                (WALLS_IN_COLUMN / 2 + 1 + 0.5f) * Wall.defaultSize),
                        new Coordinates(0f, koefY));


        initBallGenerator(world, VELOCITY_KOEF, MIN_VELOCITY, spawnInterval,
                description1, description2, description3, description4);

    }



    private void initHoles(WorldInitialization world) {

        world.addHole((new Hole((WALLS_IN_ROW/2 - 1 - 0.5f) * Wall.defaultSize,
                (WALLS_IN_COLUMN/2 - 1) * Wall.defaultSize, Color.YELLOW)));
        world.addHole((new Hole((WALLS_IN_ROW/2 - 1 - 0.5f) * Wall.defaultSize,
                (WALLS_IN_COLUMN/2 + 2) * Wall.defaultSize, Color.GREEN)));
        world.addHole((new Hole((WALLS_IN_ROW/2 + 2 - 0.5f) * Wall.defaultSize,
                (WALLS_IN_COLUMN/2 + 2) * Wall.defaultSize, Color.BLUE)));
        world.addHole((new Hole((WALLS_IN_ROW/2 + 2 - 0.5f) * Wall.defaultSize,
                (WALLS_IN_COLUMN/2 - 1) * Wall.defaultSize, Color.ORANGE)));

        world.addHole((new Hole((WALLS_IN_ROW/2 - 3 - 0.5f) * Wall.defaultSize,
                (WALLS_IN_COLUMN/2 - 3) * Wall.defaultSize, Color.YELLOW)));
        world.addHole((new Hole((WALLS_IN_ROW/2 - 3 - 0.5f) * Wall.defaultSize,
                (WALLS_IN_COLUMN/2 + 4) * Wall.defaultSize, Color.GREEN)));
        world.addHole((new Hole((WALLS_IN_ROW/2 + 4 - 0.5f) * Wall.defaultSize,
                (WALLS_IN_COLUMN/2 + 4) * Wall.defaultSize, Color.BLUE)));
        world.addHole((new Hole((WALLS_IN_ROW/2 + 4 - 0.5f) * Wall.defaultSize,
                (WALLS_IN_COLUMN/2 - 3) * Wall.defaultSize, Color.ORANGE)));

    }
}
