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
public class Level11Initializer extends LevelInitializer {

    private float spawnInterval = 4f;

    private static float MIN_VELOCITY = 3.3f;
    private static float VELOCITY_KOEF = 4.3f;

    private static final String firstHint = "GRAY IS A NEUTRAL COLOR";

    @Override
    public String getFirstHint() {
        return firstHint;
    }


    public void initialize(WorldInitialization world) {

        initBorderWalls(world);
        initWalls(world) ;
        initHoles(world);
        initBallGenerator(world);
    }

    private void initWalls(WorldInitialization world) {


        for (int i = 2; i < 6; i++) {

            for (int j = 2; j < 6; j++) {
                
                Color color = Color.NONE;
                if (i != 2 && j != 2 && i != 5 && j !=5) {
                    color = Color.GREEN;
                }
            
                world.addWall(new Wall((i + 0.5f) * Wall.defaultSize,
                        (j + 0.5f) * Wall.defaultSize, color, true));
            }
        }

        int i = 5;

        for (int j = 6; j <= WALLS_IN_COLUMN - 2; j++ ) {

            world.addWall(new Wall((i + 0.5f) * Wall.defaultSize,
                    (j + 0.5f) * Wall.defaultSize, Color.NONE, true));
            world.addWall(new Wall((i + 1 + 0.5f) * Wall.defaultSize,
                    (j + 0.5f) * Wall.defaultSize, Color.NONE, true));

            i+= 2;
        }
    }

    private void initBallGenerator(WorldInitialization world) {

        float generatorX = ((WALLS_IN_ROW/2) + 0.5f) * Wall.defaultSize;
        float generatorY = (WALLS_IN_COLUMN - 2 + 0.5f) * Wall.defaultSize;

        BallDescription description1 =
                new BallDescription(VelocityType.ANY_VELOCITY_DEFAULT_COORDINATES, Color.GREEN);

        BallDescription description2 =
                new BallDescription(VelocityType.ANY_VELOCITY_DEFAULT_COORDINATES, Color.GREEN);

        initBallGenerator(world, generatorX,
                generatorY, VELOCITY_KOEF, MIN_VELOCITY, spawnInterval,
                description1, description2);

    }


    private void initHoles(WorldInitialization world) {
        world.addHole(new Hole((WALLS_IN_ROW - 2) * Wall.defaultSize,
                (WALLS_IN_COLUMN - 2) * Wall.defaultSize, Color.ORANGE));
        world.addHole(new Hole((4) * Wall.defaultSize,
                (4) * Wall.defaultSize, Color.GREEN));
    }

}
