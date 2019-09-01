package ru.nsu.vtimofeev.Inkball.levels.easy;

import ru.nsu.vtimofeev.Inkball.levels.LevelInitializer;
import ru.nsu.vtimofeev.Inkball.levels.generation.BallDescription;
import ru.nsu.vtimofeev.Inkball.levels.generation.VelocityType;
import ru.nsu.vtimofeev.Inkball.model.*;

/**
 * Created by IntelliJ IDEA.
 * User: vtimofeev
 * Date: 5/17/12
 * Time: 10:40 PM
 * To change this template use File | Settings | File Templates.
 */
public class Level7Initializer extends LevelInitializer {

    private float spawnInterval = 3f;

    private static float MIN_VELOCITY = 2.5f;
    private static float VELOCITY_KOEF = 3.0f;

    public void initialize(WorldInitialization world) {

        initBorderWalls(world);
        initWalls(world) ;
        initHoles(world);
        initBallGenerator(world);
    }

    private void initWalls(WorldInitialization world) {

        world.addWall(new Wall((WALLS_IN_ROW/2 + 0.5f) * Wall.defaultSize,
                (1 + 0.5f) * Wall.defaultSize));
        world.addWall(new Wall((WALLS_IN_ROW/2 + 0.5f) * Wall.defaultSize,
                (WALLS_IN_COLUMN/3 + 0.5f) * Wall.defaultSize));
        world.addWall(new Wall((WALLS_IN_ROW/2 + 0.5f) * Wall.defaultSize,
                ((WALLS_IN_COLUMN/3)*2 - 1 + 0.5f) * Wall.defaultSize));
        world.addWall(new Wall((WALLS_IN_ROW/2 + 0.5f) * Wall.defaultSize,
                (WALLS_IN_COLUMN - 3 + 0.5f) * Wall.defaultSize));

        world.addWall(new Wall((WALLS_IN_ROW/2 + 0.5f) * Wall.defaultSize,
                (WALLS_IN_COLUMN - 2 + 0.5f) * Wall.defaultSize));
        world.addWall(new Wall((WALLS_IN_ROW/2 - 0.5f) * Wall.defaultSize,
                (WALLS_IN_COLUMN - 2 + 0.5f) * Wall.defaultSize));
        world.addWall(new Wall((WALLS_IN_ROW/2 + 1.5f) * Wall.defaultSize,
                (WALLS_IN_COLUMN - 2 + 0.5f) * Wall.defaultSize));

        world.addWall(new Wall((WALLS_IN_ROW - 2.5f) * Wall.defaultSize,
                (2 + 0.5f) * Wall.defaultSize));
        world.addWall(new Wall((WALLS_IN_ROW - 2.5f) * Wall.defaultSize,
                (3 + 0.5f) * Wall.defaultSize));
        world.addWall(new Wall((WALLS_IN_ROW - 3.5f) * Wall.defaultSize,
                (2 + 0.5f) * Wall.defaultSize));
        world.addWall(new Wall((WALLS_IN_ROW - 4.5f) * Wall.defaultSize,
                (2 + 0.5f) * Wall.defaultSize));
        world.addWall(new Wall((WALLS_IN_ROW - 5.5f) * Wall.defaultSize,
                (2 + 0.5f) * Wall.defaultSize));
        world.addWall(new Wall((WALLS_IN_ROW - 5.5f) * Wall.defaultSize,
                (3 + 0.5f) * Wall.defaultSize));

    }

    private void initBallGenerator(WorldInitialization world) {

        float generatorX = (WALLS_IN_ROW - 2 + 0.5f) * Wall.defaultSize;
        float generatorY = (WALLS_IN_COLUMN - 2 + 0.5f) * Wall.defaultSize;

        BallDescription description1 =
                new BallDescription(VelocityType.ANY_VELOCITY_DEFAULT_COORDINATES, Color.BLUE);

        BallDescription description2 =
                new BallDescription(VelocityType.ANY_VELOCITY_DEFAULT_COORDINATES, Color.BLUE);

        initBallGenerator(world, generatorX,
                generatorY, VELOCITY_KOEF, MIN_VELOCITY, spawnInterval,
                description1, description2);
    }


    private void initHoles(WorldInitialization world) {
        world.addHole((new Hole((2) * Wall.defaultSize,
                (2) * Wall.defaultSize, Color.BLUE)));

        world.addHole(new Hole((WALLS_IN_ROW - 4f) * Wall.defaultSize,
                (4f) * Wall.defaultSize, Color.ORANGE));

    }

}
