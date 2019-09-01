package ru.nsu.vtimofeev.Inkball.levels.medium;

import ru.nsu.vtimofeev.Inkball.levels.LevelInitializer;
import ru.nsu.vtimofeev.Inkball.levels.generation.BallDescription;
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
public class Level14Initializer extends LevelInitializer {

    private float spawnInterval = 3f;

    private static float MIN_VELOCITY = 3.4f;
    private static float VELOCITY_KOEF = 4.4f;

    private static final String firstHint = "COLORED BLOCKS CHANGE";
    private static final String secondHint = "THE COLOR OF THE BALLS";

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

        drawWallLine(world, 3, 3, 1, WALLS_IN_COLUMN/2 , Color.NONE, false);
        drawWallLine(world, WALLS_IN_ROW - 4, WALLS_IN_ROW - 4,
                1, WALLS_IN_COLUMN/2 , Color.NONE, false);

        drawWallLine(world, 1, 2, 1, 1, Color.ORANGE, false);
        drawWallLine(world, WALLS_IN_ROW - 3, WALLS_IN_ROW - 2, 1, 1, Color.ORANGE, false);
    }

    private void initBallGenerator(WorldInitialization world) {

        float generatorX = (WALLS_IN_ROW/2  + 0.5f) * Wall.defaultSize;
        float generatorY = (WALLS_IN_COLUMN - 3 + 0.5f) * Wall.defaultSize;

        BallDescription description1 =
                new BallDescription(VelocityType.ANY_VELOCITY_DEFAULT_COORDINATES, Color.BLUE);

        BallDescription description2 =
                new BallDescription(VelocityType.ANY_VELOCITY_DEFAULT_COORDINATES, Color.BLUE);

        initBallGenerator(world, generatorX,
                generatorY, VELOCITY_KOEF, MIN_VELOCITY, spawnInterval,
                description1, description2);
    }


    private void initHoles(WorldInitialization world) {
        world.addHole(new Hole((5) * Wall.defaultSize,
                (2f) * Wall.defaultSize, Color.ORANGE));
        world.addHole(new Hole((WALLS_IN_ROW - 5) * Wall.defaultSize,
                (2f) * Wall.defaultSize, Color.ORANGE));
    }

}
