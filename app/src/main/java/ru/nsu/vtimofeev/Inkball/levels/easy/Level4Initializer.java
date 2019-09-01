package ru.nsu.vtimofeev.Inkball.levels.easy;

import ru.nsu.vtimofeev.Inkball.levels.LevelInitializer;
import ru.nsu.vtimofeev.Inkball.levels.generation.BallDescription;
import ru.nsu.vtimofeev.Inkball.levels.generation.Coordinates;
import ru.nsu.vtimofeev.Inkball.levels.generation.VelocityType;
import ru.nsu.vtimofeev.Inkball.model.*;

/**
 * Created by IntelliJ IDEA.
 * User: vtimofeev
 * Date: 5/17/12
 * Time: 10:40 PM
 * To change this template use File | Settings | File Templates.
 */
public class Level4Initializer extends LevelInitializer {
    
    private static float MIN_VELOCITY = 2.5f;
    private static float VELOCITY_KOEF = 2.8f;

    private float spawnInterval = 0f;

    private static final String firstHint = "YOU ARE LIMITED IN TIME";
    private static final String secondHint = "DIRECT HIT FORCES THE BALL TO BE HOLED";

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
        world.addWall(new Wall((WALLS_IN_ROW/2 + 0.5f) * Wall.defaultSize,
                (WALLS_IN_COLUMN/2 + 0.5f) * Wall.defaultSize));
    }

    private void initBallGenerator(WorldInitialization world) {

        BallDescription description1 =
                new BallDescription(VelocityType.FIXED_VELOCITY, Color.ORANGE,
                        new Coordinates((WALLS_IN_ROW/2 + 0.5f) * Wall.defaultSize,
                                (WALLS_IN_COLUMN/2 - 0.5f) * Wall.defaultSize),
                        new Coordinates(0f, -MIN_VELOCITY));

        BallDescription description2 =
                new BallDescription(VelocityType.FIXED_VELOCITY, Color.BLUE,
                        new Coordinates((WALLS_IN_ROW/2 + 0.5f) * Wall.defaultSize,
                                (WALLS_IN_COLUMN/2 + 1.5f) * Wall.defaultSize),
                        new Coordinates(0f, MIN_VELOCITY));

        initBallGenerator(world, VELOCITY_KOEF, MIN_VELOCITY, spawnInterval,
                description1, description2);

    }


    private void initHoles(WorldInitialization world) {
        world.addHole(new Hole((WALLS_IN_ROW / 2 - 1f) * Wall.defaultSize,
                (WALLS_IN_COLUMN / 2 - 1f) * Wall.defaultSize, Color.BLUE));
        world.addHole(new Hole((WALLS_IN_ROW / 2 + 2f) * Wall.defaultSize,
                (WALLS_IN_COLUMN / 2 + 2f) * Wall.defaultSize, Color.ORANGE));
    }
}
