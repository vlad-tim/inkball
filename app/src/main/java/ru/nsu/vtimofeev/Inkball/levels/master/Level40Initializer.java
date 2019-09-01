package ru.nsu.vtimofeev.Inkball.levels.master;

import android.util.FloatMath;
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
public class Level40Initializer extends LevelInitializer {

    private float spawnInterval = 0f;

    private static float MIN_VELOCITY = 4.5f;
    private static float VELOCITY_KOEF = 5.0f;

    public void initialize(WorldInitialization world) {

        initBorderWalls(world);
        initWalls(world);
        initHoles(world);
        initBallGenerator(world);

        world.setTime(60f);

    }

    @Override
    protected void initBorderWalls(WorldInitialization world) {

        drawWallLine(world, -1, -1, 0, WALLS_IN_COLUMN - 1, Color.NONE, false);
        drawWallLine(world, WALLS_IN_ROW, WALLS_IN_ROW, 0, WALLS_IN_COLUMN - 1, Color.NONE, false);

        drawWallLine(world, 0, WALLS_IN_ROW - 1, -1, -1, Color.NONE, false);
        drawWallLine(world, 0, WALLS_IN_ROW - 1, WALLS_IN_COLUMN, WALLS_IN_COLUMN, Color.NONE, false);
    }


    private void initWalls(WorldInitialization world) {

        world.addWall(new Wall((2 + 0.5f) * Wall.defaultSize, (2 + 0.5f) * Wall.defaultSize,
                Color.ORANGE));
        world.addWall(new Wall((WALLS_IN_ROW - 2 - 0.5f) * Wall.defaultSize,
                (WALLS_IN_COLUMN - 2 - 0.5f) * Wall.defaultSize, Color.ORANGE));
        world.addWall(new Wall((2 + 0.5f) * Wall.defaultSize,
                (WALLS_IN_COLUMN - 2 - 0.5f) * Wall.defaultSize, Color.ORANGE));
        world.addWall(new Wall((WALLS_IN_ROW - 2 - 0.5f) * Wall.defaultSize,
                (2 + 0.5f) * Wall.defaultSize, Color.ORANGE));


        world.addWall(new Wall((0.5f) * Wall.defaultSize,
                (WALLS_IN_COLUMN/2f) * Wall.defaultSize, Color.ORANGE));
        world.addWall(new Wall((WALLS_IN_ROW - 0.5f) * Wall.defaultSize,
                (WALLS_IN_COLUMN/2f) * Wall.defaultSize, Color.ORANGE));

        world.addWall(new Wall((WALLS_IN_COLUMN/2f) * Wall.defaultSize,
                (WALLS_IN_COLUMN/2f) * Wall.defaultSize, Color.GREEN));


        world.addWall(new Wall((WALLS_IN_ROW - WALLS_IN_COLUMN/2f) * Wall.defaultSize,
                (WALLS_IN_COLUMN/2f) * Wall.defaultSize, Color.GREEN));
    }



    private void initBallGenerator(WorldInitialization world) {

        float vel1 = VELOCITY_KOEF;
        float vel2 = VELOCITY_KOEF / (WALLS_IN_COLUMN/2f - 1.5f) *
                (WALLS_IN_COLUMN/2f - 3.5f + (2f*Ball.radius - FloatMath.sqrt(2f) * Ball.radius));

        BallDescription description1 =
                new BallDescription(VelocityType.FIXED_VELOCITY, Color.GREEN,
                        new Coordinates((WALLS_IN_COLUMN/2f - 1) * Wall.defaultSize,
                                (WALLS_IN_COLUMN/2f) * Wall.defaultSize),
                        new Coordinates(-vel1, 0f));

        BallDescription description2 =
                new BallDescription(VelocityType.FIXED_VELOCITY, Color.GREEN,
                        new Coordinates((WALLS_IN_COLUMN/2f - 1) * Wall.defaultSize,
                                (WALLS_IN_COLUMN/2f - 1) * Wall.defaultSize),
                        new Coordinates(-vel2, -vel2));

        BallDescription description3 =
                new BallDescription(VelocityType.FIXED_VELOCITY, Color.GREEN,
                        new Coordinates((WALLS_IN_COLUMN/2f - 1) * Wall.defaultSize,
                                (WALLS_IN_COLUMN/2f + 1) * Wall.defaultSize),
                        new Coordinates(-vel2, vel2));

        BallDescription description4 =
                new BallDescription(VelocityType.FIXED_VELOCITY, Color.GREEN,
                        new Coordinates((WALLS_IN_ROW - WALLS_IN_COLUMN/2f + 1) * Wall.defaultSize,
                                (WALLS_IN_COLUMN/2f) * Wall.defaultSize),
                        new Coordinates(vel1, 0f));

        BallDescription description5 =
                new BallDescription(VelocityType.FIXED_VELOCITY, Color.GREEN,
                        new Coordinates((WALLS_IN_ROW - WALLS_IN_COLUMN/2f + 1) * Wall.defaultSize,
                                (WALLS_IN_COLUMN/2f - 1) * Wall.defaultSize),
                        new Coordinates(vel2, -vel2));

        BallDescription description6 =
                new BallDescription(VelocityType.FIXED_VELOCITY, Color.GREEN,
                        new Coordinates((WALLS_IN_ROW - WALLS_IN_COLUMN/2f + 1) * Wall.defaultSize,
                                (WALLS_IN_COLUMN/2f + 1) * Wall.defaultSize),
                        new Coordinates(vel2, vel2));

        BallDescription description7 =
                new BallDescription(VelocityType.FIXED_VELOCITY, Color.GREEN,
                        new Coordinates((WALLS_IN_COLUMN/2f + 1) * Wall.defaultSize,
                                (WALLS_IN_COLUMN/2f) * Wall.defaultSize),
                        new Coordinates(vel1, 0f));

        BallDescription description8 =
                new BallDescription(VelocityType.FIXED_VELOCITY, Color.GREEN,
                        new Coordinates((WALLS_IN_ROW - WALLS_IN_COLUMN/2f - 1) * Wall.defaultSize,
                                (WALLS_IN_COLUMN/2f) * Wall.defaultSize),
                        new Coordinates(-vel1, 0f));


        initBallGenerator(world, VELOCITY_KOEF, MIN_VELOCITY, spawnInterval,
                description1, description2, description3, description4,
                description5, description6, description7, description8);
    }


    private void initHoles(WorldInitialization world) {

        world.addHole((new Hole((1) * Wall.defaultSize,
                (1) * Wall.defaultSize, Color.GREEN)));
        world.addHole((new Hole((1) * Wall.defaultSize,
                (WALLS_IN_COLUMN - 1) * Wall.defaultSize, Color.YELLOW)));
        world.addHole((new Hole((WALLS_IN_ROW - 1) * Wall.defaultSize,
                (1) * Wall.defaultSize, Color.GREEN)));
        world.addHole((new Hole((WALLS_IN_ROW - 1) * Wall.defaultSize,
                (WALLS_IN_COLUMN - 1) * Wall.defaultSize, Color.BLUE)));
    }

}
