package ru.nsu.vtimofeev.Inkball.levels.medium;

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
public class Level12Initializer extends LevelInitializer {

    private float spawnInterval = 0f;

    private static float MIN_VELOCITY = 3.5f;
    private static float VELOCITY_KOEF = 4f;

    private static final String firstHint = "GUIDING BLOCKS CAN BE CROSSED";
    private static final String secondHint = "ONLY IN CERTAIN DIRECTION";

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
        
        for (int i = 1; i <= WALLS_IN_ROW - 2; ++i) {

            if (i < WALLS_IN_ROW/2 - 2) {
                world.addBar(new Bar((i + 0.5f) * Wall.defaultSize,
                        (WALLS_IN_COLUMN/2 + 0.5f) * Wall.defaultSize, Color.NONE, Direction.UP));
            }
            else if (i >= WALLS_IN_ROW/2 + 2) {
                world.addBar(new Bar((i + 0.5f) * Wall.defaultSize,
                        (WALLS_IN_COLUMN/2 + 0.5f) * Wall.defaultSize, Color.NONE,
                        Direction.DOWN));
            }
            else {
                world.addWall(new Wall((i + 0.5f) * Wall.defaultSize,
                        (WALLS_IN_COLUMN/2 + 0.5f) * Wall.defaultSize));
            }
        }

        world.addWall(new Wall((WALLS_IN_ROW / 2 - 2 + 0.5f) * Wall.defaultSize,
                (WALLS_IN_COLUMN / 2 + 2f - 0.5f) * Wall.defaultSize));
        world.addWall(new Wall((WALLS_IN_ROW / 2 - 2 + 0.5f) * Wall.defaultSize,
                (WALLS_IN_COLUMN / 2 + 2f + 0.5f) * Wall.defaultSize));

        world.addWall(new Wall((WALLS_IN_ROW / 2 + 1 + 0.5f) * Wall.defaultSize,
                (WALLS_IN_COLUMN / 2 - 1f - 0.5f) * Wall.defaultSize));
        world.addWall(new Wall((WALLS_IN_ROW / 2 + 1 + 0.5f) * Wall.defaultSize,
                (WALLS_IN_COLUMN / 2 - 1f + 0.5f) * Wall.defaultSize));
    }

    private void initBallGenerator(WorldInitialization world) {

        BallDescription description1 =
                new BallDescription(VelocityType.FIXED_VELOCITY, Color.BLUE,
                        new Coordinates((WALLS_IN_ROW / 2 - 3 + 0.5f) * Wall.defaultSize,
                                (WALLS_IN_COLUMN / 2 + 2f - 0.5f) * Wall.defaultSize),
                        new Coordinates(-MIN_VELOCITY, 0f));

        BallDescription description2 =
                new BallDescription(VelocityType.FIXED_VELOCITY, Color.GREEN,
                        new Coordinates((WALLS_IN_ROW / 2 + 2 + 0.5f) * Wall.defaultSize,
                                (WALLS_IN_COLUMN / 2 -1f + 0.5f) * Wall.defaultSize),
                        new Coordinates(MIN_VELOCITY, 0f));


        initBallGenerator(world, VELOCITY_KOEF, MIN_VELOCITY, spawnInterval,
                description1, description2);


    }

    private void initHoles(WorldInitialization world) {
        world.addHole(new Hole((WALLS_IN_ROW / 2) * Wall.defaultSize,
                (WALLS_IN_COLUMN / 2 + 2f) * Wall.defaultSize, Color.GREEN));
        world.addHole(new Hole((WALLS_IN_ROW / 2) * Wall.defaultSize,
                (WALLS_IN_COLUMN / 2 - 1f) * Wall.defaultSize, Color.BLUE));
    }

}
