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
public class Level3Initializer extends LevelInitializer {

    private static float MIN_VELOCITY = 2.5f;
    private static float VELOCITY_KOEF = 2.8f;

    private float spawnInterval = 4f;

    private static final String firstHint = "TOUCH THE BALL WHILE DRAWING A LINE";
    private static final String secondHint = "TO CHANGE THE DIRECTION";

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
        initWalls(world);
        initHoles(world);
        initBallGenerator(world);
    }

    private void initWalls(WorldInitialization world) {
        world.addWall(new Wall((1 + 0.5f) * Wall.defaultSize, (1 + 0.5f) *Wall.defaultSize));
        world.addWall(new Wall((1 + 0.5f) * Wall.defaultSize,
                (WALLS_IN_COLUMN - 2 + 0.5f) * Wall.defaultSize));
        world.addWall(new Wall((WALLS_IN_ROW - 2 + 0.5f) * Wall.defaultSize,
                (1 + 0.5f) *Wall.defaultSize));
        world.addWall(new Wall((WALLS_IN_ROW - 2 + 0.5f) * Wall.defaultSize,
                (WALLS_IN_COLUMN - 2 + 0.5f) * Wall.defaultSize));

        world.addWall(new Wall((3 + 0.5f) * Wall.defaultSize, (6 + 0.5f) * Wall.defaultSize));
        world.addWall(new Wall((7 + 0.5f) * Wall.defaultSize, (9 + 0.5f) * Wall.defaultSize));
        world.addWall(new Wall((10 + 0.5f) * Wall.defaultSize, (10 + 0.5f) * Wall.defaultSize));
        world.addWall(new Wall((9 + 0.5f) * Wall.defaultSize, (6 + 0.5f) * Wall.defaultSize));
        world.addWall(new Wall((17 + 0.5f) * Wall.defaultSize, (3 + 0.5f) * Wall.defaultSize));
        world.addWall(new Wall((19 + 0.5f) * Wall.defaultSize, (6 + 0.5f) * Wall.defaultSize));
    }

    private void initBallGenerator(WorldInitialization world) {

        BallDescription description1 =
                new BallDescription(VelocityType.ANY_VELOCITY_DEFAULT_COORDINATES, Color.ORANGE);

        BallDescription description2 =
                new BallDescription(VelocityType.ANY_VELOCITY_DEFAULT_COORDINATES, Color.ORANGE);

        initBallGenerator(world, (18 + 0.5f) * Wall.defaultSize,
                (3 + 0.5f) * Wall.defaultSize, VELOCITY_KOEF, MIN_VELOCITY, spawnInterval,
                description1, description2);

    }


    private void initHoles(WorldInitialization world) {
        world.addHole((new Hole((4) * Wall.defaultSize,
                (WALLS_IN_COLUMN - 2) * Wall.defaultSize, Color.ORANGE)));
    }

}
