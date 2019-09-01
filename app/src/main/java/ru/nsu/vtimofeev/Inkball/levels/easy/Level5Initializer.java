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
public class Level5Initializer extends LevelInitializer {
    
    private static float MIN_VELOCITY = 2.6f;
    private static float VELOCITY_KOEF = 2.9f;

    private float spawnInterval = 5f;

    private static final String firstHint = "YOU CAN CLEAR THE SCREEN";
    private static final String secondHint = "OR ACCELERATE THE BALLS";

    @Override
    public String getFirstHint() {
        return firstHint;
    }

    @Override
    public String getSecondHint() {
        return secondHint;
    }


    public void initialize(WorldInitialization world) {

        initBorderWalls(world);
        initWalls(world) ;
        initHoles(world);
        initBallGenerator(world);
    }

    private void initWalls(WorldInitialization world) {

        world.addWall(new Wall((WALLS_IN_ROW/2 + 2.5f + 1) * Wall.defaultSize,
                (WALLS_IN_COLUMN/2 + 1.5f) * Wall.defaultSize));
        world.addWall(new Wall((WALLS_IN_ROW/2 + 2.5f + 1) * Wall.defaultSize,
                (WALLS_IN_COLUMN/2 + 0.5f) * Wall.defaultSize));
        world.addWall(new Wall((WALLS_IN_ROW/2 + 2.5f + 1) * Wall.defaultSize,
                (WALLS_IN_COLUMN/2 - 0.5f) * Wall.defaultSize));

        world.addWall(new Wall((WALLS_IN_ROW/2 + 2.5f) * Wall.defaultSize,
                (WALLS_IN_COLUMN/2 + 1.5f) * Wall.defaultSize));
        world.addWall(new Wall((WALLS_IN_ROW/2 + 2.5f) * Wall.defaultSize,
                (WALLS_IN_COLUMN/2 + 2.5f) * Wall.defaultSize));
        world.addWall(new Wall((WALLS_IN_ROW/2 + 1.5f) * Wall.defaultSize,
                (WALLS_IN_COLUMN/2 + 2.5f) * Wall.defaultSize));
        world.addWall(new Wall((WALLS_IN_ROW/2 + 0.5f) * Wall.defaultSize,
                (WALLS_IN_COLUMN/2 + 2.5f) * Wall.defaultSize));

        world.addWall(new Wall((WALLS_IN_ROW/2 - 0.5f) * Wall.defaultSize,
                (WALLS_IN_COLUMN/2 + 2.5f) * Wall.defaultSize));
        world.addWall(new Wall((WALLS_IN_ROW/2 - 1.5f) * Wall.defaultSize,
                (WALLS_IN_COLUMN/2 + 2.5f) * Wall.defaultSize));
        world.addWall(new Wall((WALLS_IN_ROW/2 - 2.5f) * Wall.defaultSize,
                (WALLS_IN_COLUMN/2 + 2.5f) * Wall.defaultSize));
        world.addWall(new Wall((WALLS_IN_ROW/2 - 1.5f) * Wall.defaultSize,
                (WALLS_IN_COLUMN/2 + 3.5f) * Wall.defaultSize));

        world.addWall(new Wall((WALLS_IN_ROW/2 + 2.5f) * Wall.defaultSize,
                (WALLS_IN_COLUMN/2 - 1.5f) * Wall.defaultSize));
        world.addWall(new Wall((WALLS_IN_ROW/2 + 1.5f) * Wall.defaultSize,
                (WALLS_IN_COLUMN/2 - 1.5f) * Wall.defaultSize));
        world.addWall(new Wall((WALLS_IN_ROW/2 + 0.5f) * Wall.defaultSize,
                (WALLS_IN_COLUMN/2 - 1.5f) * Wall.defaultSize));
        world.addWall(new Wall((WALLS_IN_ROW/2 - 0.5f) * Wall.defaultSize,
                (WALLS_IN_COLUMN/2 - 1.5f) * Wall.defaultSize));
        world.addWall(new Wall((WALLS_IN_ROW/2 - 1.5f) * Wall.defaultSize,
                (WALLS_IN_COLUMN/2 - 1.5f) * Wall.defaultSize));

        world.addWall(new Wall((WALLS_IN_ROW/2 - 1.5f) * Wall.defaultSize,
                (WALLS_IN_COLUMN/2 - 2.5f) * Wall.defaultSize));
        world.addWall(new Wall((WALLS_IN_ROW/2 - 2.5f) * Wall.defaultSize,
                (WALLS_IN_COLUMN/2 - 2.5f) * Wall.defaultSize));

    }

    private void initBallGenerator(WorldInitialization world) {

        BallDescription description1 =
                new BallDescription(VelocityType.ANY_VELOCITY_DEFAULT_COORDINATES, Color.ORANGE);

        BallDescription description2 =
                new BallDescription(VelocityType.ANY_VELOCITY_DEFAULT_COORDINATES, Color.BLUE);

        initBallGenerator(world, (WALLS_IN_ROW/2 + 1.5f) * Wall.defaultSize,
                (WALLS_IN_COLUMN/2 + 0.5f) * Wall.defaultSize, VELOCITY_KOEF, MIN_VELOCITY, spawnInterval,
                description1, description2);
    }

    private void initHoles(WorldInitialization world) {
        world.addHole(new Hole((2) * Wall.defaultSize,
                (WALLS_IN_COLUMN - 2f) * Wall.defaultSize, Color.ORANGE));
        world.addHole((new Hole((WALLS_IN_ROW - 2) * Wall.defaultSize,
                (WALLS_IN_COLUMN - 2) * Wall.defaultSize, Color.BLUE)));
    }

}
