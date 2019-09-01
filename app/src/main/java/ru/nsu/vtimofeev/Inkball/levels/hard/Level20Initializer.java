package ru.nsu.vtimofeev.Inkball.levels.hard;

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
public class Level20Initializer extends LevelInitializer {

    private float spawnInterval = 7f;

    private static float MIN_VELOCITY = 2.3f;
    private static float VELOCITY_KOEF = 3.0f;

    private static final String firstHint = "YOU SHOULD BE CAREFUL THIS TIME";

    @Override
    public String getFirstHint() {
        return firstHint;
    }

    public void initialize(WorldInitialization world) {

        initBorderWalls(world);
        initWalls(world);
        initHoles(world);
        initBallGenerator(world);
    }

    @Override
    public void initBorderWalls(WorldInitialization world) {
        drawWallLine(world, 0, 0, 0, WALLS_IN_COLUMN - 1, Color.NONE, false);
        drawWallLine(world, 0, WALLS_IN_ROW - 1, 0, 0, Color.NONE, false);

        drawWallLine(world, 1, WALLS_IN_ROW - 5, WALLS_IN_COLUMN - 1, WALLS_IN_COLUMN - 1, Color.NONE, false);
        drawWallLine(world, WALLS_IN_ROW - 4, WALLS_IN_ROW - 1, WALLS_IN_COLUMN - 1,
                WALLS_IN_COLUMN - 1,
                Color.GREEN, false);

        drawWallLine(world, WALLS_IN_ROW - 1, WALLS_IN_ROW - 1, 1, WALLS_IN_COLUMN - 4,
                 Color.NONE, false);
        drawWallLine(world, WALLS_IN_ROW - 1, WALLS_IN_ROW - 1, WALLS_IN_COLUMN - 3,
                WALLS_IN_COLUMN - 1,
                Color.GREEN, false);
    }

    private void initWalls(WorldInitialization world) {
        
        int delta = WALLS_IN_ROW / 5;
        
        for (int i = WALLS_IN_ROW - 1 - delta, j = 0; i >= delta; i-= delta, ++j ) {

            if ((j % 2) == 0) {
                drawWallLine(world, i, i, delta, WALLS_IN_COLUMN - 1, Color.NONE, false);
            }
            else {
                drawWallLine(world, i, i, 1, WALLS_IN_COLUMN - 1 - delta, Color.NONE,
                        false);
            }
            
            drawWallLine(world, i - 1, i - 1, WALLS_IN_COLUMN/2, WALLS_IN_COLUMN/2,
                    Color.ORANGE, false);

        }
    }


    private void initBallGenerator(WorldInitialization world) {

        float generatorX = (WALLS_IN_ROW - 2 + 0.5f) * Wall.defaultSize;
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
        world.addHole((new Hole((2) * Wall.defaultSize,
                (2) * Wall.defaultSize, Color.GREEN)));
    }

}
