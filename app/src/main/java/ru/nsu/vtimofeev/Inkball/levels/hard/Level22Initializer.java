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
public class Level22Initializer extends LevelInitializer {

    private float spawnInterval = 0f;

    private static float MIN_VELOCITY = 2.5f;
    private static float VELOCITY_KOEF = 3.0f;

    public void initialize(WorldInitialization world) {

        initBorderWalls(world);
        initWalls(world);
        initHoles(world);
        initBallGenerator(world);
    }


    @Override
    protected void initBorderWalls(WorldInitialization world) {
        
        drawWallLine(world, -1, -1, 0, WALLS_IN_COLUMN - 1, Color.NONE, false);
        drawWallLine(world, WALLS_IN_ROW, WALLS_IN_ROW, 0, WALLS_IN_COLUMN - 1, Color.NONE, false);

        drawWallLine(world, 0, WALLS_IN_ROW - 1, -1, - 1, Color.NONE, false);
        drawWallLine(world, 0, WALLS_IN_ROW - 1, WALLS_IN_COLUMN, WALLS_IN_COLUMN, Color.NONE, false);


    }


    protected void initWalls(WorldInitialization world) {

        world.addWall(new Wall(WALLS_IN_ROW/2 + 0.5f, WALLS_IN_COLUMN/2 + 0.5f, Color.GREEN));

        world.addWall(new Wall(0.5f, WALLS_IN_COLUMN/2 + 0.5f, Color.ORANGE));
        world.addWall(new Wall(WALLS_IN_ROW - 1 + 0.5f, WALLS_IN_COLUMN/2 + 0.5f, Color.ORANGE));

        world.addWall(new Wall(WALLS_IN_ROW/2 + 0.5f, 0.5f, Color.ORANGE));
        world.addWall(new Wall(WALLS_IN_ROW/2 + 0.5f, WALLS_IN_COLUMN - 1 + 0.5f, Color.ORANGE));

        world.addWall(new Wall(2 + 0.5f, 2 + 0.5f, Color.ORANGE));
        world.addWall(new Wall(2 + 0.5f, WALLS_IN_COLUMN - 3 + 0.5f, Color.ORANGE));

        world.addWall(new Wall(WALLS_IN_ROW - 3 + 0.5f, 2 + 0.5f, Color.ORANGE));
        world.addWall(new Wall(WALLS_IN_ROW - 3 + 0.5f, WALLS_IN_COLUMN - 3 + 0.5f, Color.ORANGE));
    }


    private void initBallGenerator(WorldInitialization world) {

        float koefY = MIN_VELOCITY;
        float d = (Wall.defaultSize/2f - Ball.radius)*1.8f;


        BallDescription description1 =
                new BallDescription(VelocityType.FIXED_VELOCITY, Color.GREEN,
                        new Coordinates((WALLS_IN_ROW/2 + 1 + 0.5f) * Wall.defaultSize,
                                (WALLS_IN_COLUMN/2 + 0.5f) * Wall.defaultSize),
                        new Coordinates(koefY * ((8f + d) / (4f + d)), 0f));

        BallDescription description2 =
                new BallDescription(VelocityType.FIXED_VELOCITY, Color.GREEN,
                        new Coordinates((WALLS_IN_ROW/2 - 1 + 0.5f) * Wall.defaultSize,
                                (WALLS_IN_COLUMN/2 + 0.5f) * Wall.defaultSize),
                        new Coordinates(-koefY * ((9f + d) / (4f + d)), 0f));

        BallDescription description3 =
                new BallDescription(VelocityType.FIXED_VELOCITY, Color.GREEN,
                        new Coordinates((WALLS_IN_ROW/2 + 0.5f) * Wall.defaultSize,
                                (WALLS_IN_COLUMN/2 + 1 + 0.5f) * Wall.defaultSize),
                        new Coordinates(0f, koefY));

        BallDescription description4 =
                new BallDescription(VelocityType.FIXED_VELOCITY, Color.GREEN,
                        new Coordinates((WALLS_IN_ROW/2 + 0.5f) * Wall.defaultSize,
                                (WALLS_IN_COLUMN/2 - 1 + 0.5f) * Wall.defaultSize),
                        new Coordinates(0f, -koefY));


        initBallGenerator(world, VELOCITY_KOEF, MIN_VELOCITY, spawnInterval,
                description1, description2, description3, description4);


    }

    private void initHoles(WorldInitialization world) {
        world.addHole((new Hole(( 1) * Wall.defaultSize,
                (1) * Wall.defaultSize, Color.GREEN)));
        world.addHole((new Hole((1) * Wall.defaultSize,
                (WALLS_IN_COLUMN - 1) * Wall.defaultSize, Color.GREEN)));
        world.addHole((new Hole((WALLS_IN_ROW - 1) * Wall.defaultSize,
                (1) * Wall.defaultSize, Color.GREEN)));
        world.addHole((new Hole((WALLS_IN_ROW - 1) * Wall.defaultSize,
                (WALLS_IN_COLUMN - 1) * Wall.defaultSize, Color.GREEN)));
    }

}
