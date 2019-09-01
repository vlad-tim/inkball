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
public class Level18Initializer extends LevelInitializer {

    private float spawnInterval = 0f;

    private static float MIN_VELOCITY = 3.5f;
    private static float VELOCITY_KOEF = 4.5f;

    public void initialize(WorldInitialization world) {

        initBorderWalls(world);
        initWalls(world) ;
        initHoles(world);
        initBallGenerator(world);
    }

    private void initWalls(WorldInitialization world) {

        drawWallLine(world, WALLS_IN_ROW/2 + 1, WALLS_IN_ROW/2 + 1, 1 , WALLS_IN_COLUMN - 2,
            Color.ORANGE, true);
        drawWallLine(world, WALLS_IN_ROW/2 + 2, WALLS_IN_ROW/2 + 2, 1 , WALLS_IN_COLUMN - 2,
                Color.BLUE, true);

        drawWallLine(world, 3, 3, WALLS_IN_COLUMN/2 - 2 , WALLS_IN_COLUMN/2 + 1,
                Color.GREEN, true);

        drawWallLine(world, 1, 2, WALLS_IN_COLUMN/2 - 2 , WALLS_IN_COLUMN/2 - 2,
                Color.NONE, false);
        drawWallLine(world, 1, 2, WALLS_IN_COLUMN/2 + 1 , WALLS_IN_COLUMN/2 + 1,
                Color.NONE, false);


    }

    private void initBallGenerator(WorldInitialization world) {


        BallDescription description1 =
                new BallDescription(VelocityType.ANY_VELOCITY, Color.BLUE,
                        new Coordinates((WALLS_IN_ROW - 2  + 0.5f) * Wall.defaultSize,
                                (1 + 0.5f) * Wall.defaultSize));

        BallDescription description2 =
                new BallDescription(VelocityType.ANY_VELOCITY, Color.GREEN,
                        new Coordinates((WALLS_IN_ROW - 2  + 0.5f) * Wall.defaultSize,
                                (WALLS_IN_COLUMN/2 + 0.5f) * Wall.defaultSize));

        BallDescription description3 =
                new BallDescription(VelocityType.ANY_VELOCITY, Color.ORANGE,
                        new Coordinates((WALLS_IN_ROW - 2  + 0.5f) * Wall.defaultSize,
                                (WALLS_IN_COLUMN - 2 + 0.5f) * Wall.defaultSize));


        initBallGenerator(world, VELOCITY_KOEF, MIN_VELOCITY, spawnInterval,
                description1, description2, description3);

    }


    private void initHoles(WorldInitialization world) {
        world.addHole(new Hole((2) * Wall.defaultSize,
                (2) * Wall.defaultSize, Color.ORANGE));
        world.addHole(new Hole((2) * Wall.defaultSize,
                (WALLS_IN_COLUMN/2) * Wall.defaultSize, Color.GREEN ));
        world.addHole(new Hole((2) * Wall.defaultSize,
                (WALLS_IN_COLUMN - 2) * Wall.defaultSize, Color.BLUE ));
    }

}
