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
public class Level10Initializer extends LevelInitializer {

    private float spawnInterval = 4f;

    private static float MIN_VELOCITY = 3.3f;
    private static float VELOCITY_KOEF = 4.3f;

    private static final String firstHint = "REMOVABLE BLOCKS DISAPPEAR";
    private static final String secondHint = "WHEN HIT BY THE RIGHT COLORED BALL";

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


        for (int i = 1; i < WALLS_IN_ROW/3; i++) {
            
            world.addWall(new Wall((i + 0.5f) * Wall.defaultSize,
                    (WALLS_IN_COLUMN/2 + 1.5f) * Wall.defaultSize));            
        }

        for (int j = WALLS_IN_COLUMN/2 + 1; j > 3; j--) {

            world.addWall(new Wall((WALLS_IN_ROW/3 + 0.5f) * Wall.defaultSize,
                    (j + 0.5f) * Wall.defaultSize, Color.ORANGE, true));
            world.addWall(new Wall(((WALLS_IN_ROW*2)/3 + 0.5f) * Wall.defaultSize,
                    (j + 0.5f) * Wall.defaultSize, Color.BLUE, true));
        }

        for (int i = WALLS_IN_ROW/3; i <= (WALLS_IN_ROW*2)/3; i++) {

            world.addWall(new Wall((i + 0.5f) * Wall.defaultSize,
                    (3 + 0.5f) * Wall.defaultSize, Color.ORANGE, true));
        }

        for (int i = WALLS_IN_ROW/3 + 1; i <= (WALLS_IN_ROW*2)/3 - 1; i++) {

            world.addWall(new Wall((i + 0.5f) * Wall.defaultSize,
                    (WALLS_IN_COLUMN/2 + 1 + 0.5f) * Wall.defaultSize, Color.BLUE, true));
        }

        for (int j = WALLS_IN_COLUMN - 2; j >= WALLS_IN_COLUMN - 3; j--) {
            world.addWall(new Wall((WALLS_IN_ROW/3 + 1 + 0.5f) * Wall.defaultSize,
                    (j +0.5f) * Wall.defaultSize));
        }
    }

    private void initBallGenerator(WorldInitialization world) {

        float generatorX = ((WALLS_IN_ROW*2)/3 - 1 + 0.5f) * Wall.defaultSize;
        float generatorY = (WALLS_IN_COLUMN/2 + 0.5f) * Wall.defaultSize;

        BallDescription description1 =
                new BallDescription(VelocityType.ANY_VELOCITY_DEFAULT_COORDINATES, Color.BLUE);

        BallDescription description2 =
                new BallDescription(VelocityType.ANY_VELOCITY_DEFAULT_COORDINATES, Color.ORANGE);

        BallDescription description3 =
                new BallDescription(VelocityType.ANY_VELOCITY_DEFAULT_COORDINATES, Color.BLUE);

        initBallGenerator(world, generatorX,
                generatorY, VELOCITY_KOEF, MIN_VELOCITY, spawnInterval,
                description1, description2, description3);
    }

    private void initHoles(WorldInitialization world) {
        world.addHole(new Hole((2) * Wall.defaultSize,
                (WALLS_IN_COLUMN/2) * Wall.defaultSize, Color.BLUE));
        world.addHole(new Hole((2) * Wall.defaultSize,
                (WALLS_IN_COLUMN/2 + 3) * Wall.defaultSize, Color.ORANGE));
    }

}
