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
public class Level1Initializer extends LevelInitializer {
    
    private static float MIN_VELOCITY = 2.5f;
    private static float VELOCITY_KOEF = 2.8f;

    private float spawnInterval = 4f;

    private static final String firstHint = "DRAW LINES WITH YOUR FINGER";
    private static final String secondHint = "GUIDE THE BALLS INTO THE HOLES";

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
        initHoles(world);
        initBallGenerator(world);
    }


    private void initBallGenerator(WorldInitialization world) {

        BallDescription description =
                new BallDescription(VelocityType.ANY_VELOCITY_DEFAULT_COORDINATES, Color.ORANGE);

        initBallGenerator(world, (World.WIDTH/2 + 0.5f) * Wall.defaultSize,
            (World.HEIGHT/2 + 0.5f) * Wall.defaultSize, VELOCITY_KOEF, MIN_VELOCITY, spawnInterval,
        description, description);
    }

    private void initHoles(WorldInitialization world) {
        world.addHole((new Hole((WALLS_IN_ROW - 2) * Wall.defaultSize,
                (WALLS_IN_COLUMN - 2) * Wall.defaultSize, Color.ORANGE)));
    }
}
