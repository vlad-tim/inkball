package ru.nsu.vtimofeev.Inkball.levels.hard;

import ru.nsu.vtimofeev.Inkball.levels.LevelInitializer;
import ru.nsu.vtimofeev.Inkball.levels.generation.BallDescription;
import ru.nsu.vtimofeev.Inkball.levels.generation.Coordinates;
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
public class Level26Initializer extends LevelInitializer {

    private float spawnInterval = 0f;

    private static float MIN_VELOCITY = 4.0f;
    private static float VELOCITY_KOEF = 5.0f;

    public void initialize(WorldInitialization world) {

        initBorderWalls(world);
        initWalls(world);
        initHoles(world);
        initBallGenerator(world);
    }


    private void initWalls(WorldInitialization world) {

            
        drawPuzzleRectangle(world, WALLS_IN_ROW/2 - 2, WALLS_IN_ROW/2 + 1, WALLS_IN_COLUMN/2 - 2,
                WALLS_IN_COLUMN/2 + 1, Color.GREEN, PuzzleState.LOCKED);

        drawPuzzleRectangle(world, WALLS_IN_ROW/2 - 4, WALLS_IN_ROW/2 + 3, WALLS_IN_COLUMN/2 - 4,
                WALLS_IN_COLUMN/2 + 3, Color.GREEN, PuzzleState.LOCKED);

    }


    private void initBallGenerator(WorldInitialization world) {


        BallDescription description1 =
                new BallDescription(VelocityType.ANY_VELOCITY, Color.GREEN,
                        new Coordinates((1 + 0.5f) * Wall.defaultSize, (WALLS_IN_COLUMN/2 + 0.5f) * Wall
                                .defaultSize));

        BallDescription description2 =
                new BallDescription(VelocityType.ANY_VELOCITY, Color.ORANGE,
                        new Coordinates((WALLS_IN_ROW/2 - 3 + 0.5f) * Wall.defaultSize,
                                (WALLS_IN_COLUMN/2 - 3 + 0.5f) * Wall
                                        .defaultSize));

        BallDescription description3 =
                new BallDescription(VelocityType.ANY_VELOCITY, Color.ORANGE,
                        new Coordinates((WALLS_IN_ROW/2 + 2 + 0.5f) * Wall.defaultSize,
                                (WALLS_IN_COLUMN/2 - 3 + 0.5f) * Wall
                                        .defaultSize));

        BallDescription description4 =
                new BallDescription(VelocityType.ANY_VELOCITY, Color.ORANGE,
                        new Coordinates((WALLS_IN_ROW/2 - 3 + 0.5f) * Wall.defaultSize,
                                (WALLS_IN_COLUMN/2 + 2 + 0.5f) * Wall
                                        .defaultSize));

        BallDescription description5 =
                new BallDescription(VelocityType.ANY_VELOCITY, Color.ORANGE,
                        new Coordinates((WALLS_IN_ROW/2 + 2 + 0.5f) * Wall.defaultSize,
                                (WALLS_IN_COLUMN/2 + 2 + 0.5f) * Wall
                                        .defaultSize));


        initBallGenerator(world, VELOCITY_KOEF, MIN_VELOCITY, spawnInterval,
                description1, description2, description3, description4, description5);

    }


    private void initHoles(WorldInitialization world) {
        world.addHole((new Hole((2) * Wall.defaultSize,
                (2) * Wall.defaultSize, Color.GREEN)));
        world.addHole((new Hole((2) * Wall.defaultSize,
                (WALLS_IN_COLUMN - 2) * Wall.defaultSize, Color.GREEN)));
        world.addHole((new Hole((WALLS_IN_ROW - 2) * Wall.defaultSize,
                (2) * Wall.defaultSize, Color.GREEN)));
        world.addHole((new Hole((WALLS_IN_ROW - 2) * Wall.defaultSize,
                (WALLS_IN_COLUMN - 2) * Wall.defaultSize, Color.GREEN)));

        world.addHole((new Hole((WALLS_IN_ROW/2) * Wall.defaultSize,
                (WALLS_IN_COLUMN/2) * Wall.defaultSize, Color.ORANGE)));
    }

}
