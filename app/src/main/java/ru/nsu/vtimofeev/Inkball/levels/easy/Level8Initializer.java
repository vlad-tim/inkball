package ru.nsu.vtimofeev.Inkball.levels.easy;

import ru.nsu.vtimofeev.Inkball.levels.LevelInitializer;
import ru.nsu.vtimofeev.Inkball.levels.generation.BallDescription;
import ru.nsu.vtimofeev.Inkball.levels.generation.Coordinates;
import ru.nsu.vtimofeev.Inkball.levels.generation.VelocityType;
import ru.nsu.vtimofeev.Inkball.model.*;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: vtimofeev
 * Date: 5/17/12
 * Time: 10:40 PM
 * To change this template use File | Settings | File Templates.
 */
public class Level8Initializer extends LevelInitializer {

    private float spawnInterval = 0f;

    private static float MIN_VELOCITY = 2.6f;
    private static float VELOCITY_KOEF = 2.9f;

    public void initialize(WorldInitialization world) {

        initBorderWalls(world);
        initWalls(world) ;
        initHoles(world);
        initBallGenerator(world);
    }

    private void initWalls(WorldInitialization world) {

        world.addWall(new Wall((11 + 0.5f) * Wall.defaultSize,
                (1 + 0.5f) * Wall.defaultSize));
        world.addWall(new Wall((9 + 0.5f) * Wall.defaultSize,
                (3 + 0.5f) * Wall.defaultSize));
        world.addWall(new Wall((11 + 0.5f) * Wall.defaultSize,
                (2 + 0.5f) * Wall.defaultSize));

        world.addWall(new Wall((WALLS_IN_ROW - 10 - 1.5f) * Wall.defaultSize,
                (1 + 0.5f) * Wall.defaultSize));
        world.addWall(new Wall((WALLS_IN_ROW - 10 - 1.5f) * Wall.defaultSize,
                (2 + 0.5f) * Wall.defaultSize));
        world.addWall(new Wall((WALLS_IN_ROW - 8 - 1.5f) * Wall.defaultSize,
                (3 + 0.5f) * Wall.defaultSize));

        world.addWall(new Wall((WALLS_IN_ROW - 4 + 0.5f) * Wall.defaultSize,
                (WALLS_IN_COLUMN - 4 + 0.5f) * Wall.defaultSize));
        world.addWall(new Wall((3 + 0.5f) * Wall.defaultSize,
                (WALLS_IN_COLUMN - 4 + 0.5f) * Wall.defaultSize));


    }

    private void initBallGenerator(WorldInitialization world) {


        BallDescription description1 =
                new BallDescription(VelocityType.FIXED_VELOCITY, Color.BLUE,
                    new Coordinates((8 + 0.5f) * Wall.defaultSize,
                        (4 + 0.5f) * Wall.defaultSize),
                    new Coordinates(-MIN_VELOCITY, MIN_VELOCITY));

        BallDescription description2 =
                new BallDescription(VelocityType.FIXED_VELOCITY, Color.ORANGE,
                        new Coordinates((WALLS_IN_ROW - 7 - 1.5f) * Wall.defaultSize,
                                (4 + 0.5f) * Wall.defaultSize),
                        new Coordinates(MIN_VELOCITY, MIN_VELOCITY));


        initBallGenerator(world, VELOCITY_KOEF, MIN_VELOCITY, spawnInterval,
                description1, description2);

    }


    private void initHoles(WorldInitialization world) {
        world.addHole((new Hole((2) * Wall.defaultSize,
                (WALLS_IN_COLUMN - 2) * Wall.defaultSize, Color.ORANGE)));

        world.addHole(new Hole((WALLS_IN_ROW - 2f) * Wall.defaultSize,
                (WALLS_IN_COLUMN - 2) * Wall.defaultSize, Color.BLUE));

    }

}
