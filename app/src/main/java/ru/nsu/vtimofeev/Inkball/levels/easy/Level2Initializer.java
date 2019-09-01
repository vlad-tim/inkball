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
public class Level2Initializer extends LevelInitializer {

    private static float MIN_VELOCITY = 2.5f;
    private static float VELOCITY_KOEF = 2.8f;

    private static final String firstHint = "CONSIDER COLOR";

    @Override
    public String getFirstHint() {
        return firstHint;
    }

    private float spawnInterval = 4f;

    public void initialize(WorldInitialization world) {

        initBorderWalls(world);
        initHoles(world);
        initBallGenerator(world);
    }

    private void initBallGenerator(WorldInitialization world) {

        BallDescription description1 =
                new BallDescription(VelocityType.ANY_VELOCITY_DEFAULT_COORDINATES, Color.ORANGE);

        BallDescription description2 =
                new BallDescription(VelocityType.ANY_VELOCITY_DEFAULT_COORDINATES, Color.BLUE);

        initBallGenerator(world, (World.WIDTH/2 + 0.5f) * Wall.defaultSize,
                (World.HEIGHT/2 + 0.5f) * Wall.defaultSize, VELOCITY_KOEF, MIN_VELOCITY, spawnInterval,
                description1, description2);

    }

    private void initHoles(WorldInitialization world) {
        world.addHole((new Hole((2) * Wall.defaultSize,
                (WALLS_IN_COLUMN - 2) * Wall.defaultSize, Color.BLUE)));

        world.addHole((new Hole((WALLS_IN_ROW - 2) * Wall.defaultSize,
                (WALLS_IN_COLUMN - 2) * Wall.defaultSize, Color.ORANGE)));
    }

}
