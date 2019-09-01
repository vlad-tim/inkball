package ru.nsu.vtimofeev.Inkball.levels.master;

import ru.nsu.vtimofeev.GameFramework.HelperClasses.MathClasses.Vector2;
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
public class Level45Initializer extends LevelInitializer {

    private float spawnInterval = 0f;

    private static float MIN_VELOCITY = 5.0f;
    private static float VELOCITY_KOEF = 6.0f;
    
    private Vector2 temp = new Vector2();

    public void initialize(WorldInitialization world) {

        initBorderWalls(world);
        initWalls(world);
        initBars(world);
        initHoles(world);
        initBallGenerator(world);

        world.setTime(60f);
    }

    @Override
    protected void initBorderWalls(WorldInitialization world) {

        drawWallLine(world, -1, -1, 0, WALLS_IN_COLUMN - 1, Color.NONE, false);
        drawWallLine(world, WALLS_IN_ROW, WALLS_IN_ROW, 0, WALLS_IN_COLUMN - 1, Color.NONE, false);

        drawWallLine(world, 0, WALLS_IN_ROW - 1, -1, -1, Color.NONE, false);
        drawWallLine(world, 0, WALLS_IN_ROW - 1, WALLS_IN_COLUMN, WALLS_IN_COLUMN, Color.NONE, false);
    }

    private void initWalls(WorldInitialization world) {

        world.addWall(new Wall((WALLS_IN_ROW/4 + 0.5f) * Wall.defaultSize,
                (3 + 0.5f) * Wall.defaultSize) );
        world.addWall(new Wall((WALLS_IN_ROW - 1 - WALLS_IN_ROW/4 + 0.5f) * Wall.defaultSize,
                (3 + 0.5f) * Wall.defaultSize) );
        world.addWall(new Wall((WALLS_IN_ROW/4 + 0.5f) * Wall.defaultSize,
                (8 + 0.5f) * Wall.defaultSize) );
        world.addWall(new Wall((WALLS_IN_ROW - 1 - WALLS_IN_ROW/4 + 0.5f) * Wall.defaultSize,
                (8 + 0.5f) * Wall.defaultSize) );


        world.addWall(new Wall((0 + 0.5f) * Wall.defaultSize, (0 + 0.5f) * Wall.defaultSize,
                Color.GREEN ));
        world.addWall(new Wall((0 + 0.5f) * Wall.defaultSize, (2 + 0.5f) * Wall.defaultSize,
                Color.ORANGE ));
        world.addWall(new Wall((WALLS_IN_ROW/2 - 1 + 0.5f) * Wall.defaultSize,
                (0 + 0.5f) * Wall.defaultSize,
                Color.ORANGE ));
        world.addWall(new Wall((WALLS_IN_ROW/2 + 0.5f) * Wall.defaultSize, (0 + 0.5f) * Wall.defaultSize,
                Color.YELLOW ));
        world.addWall(new Wall((WALLS_IN_ROW - 1 + 0.5f) * Wall.defaultSize,
                (3 + 0.5f) * Wall.defaultSize, Color.ORANGE ));
        world.addWall(new Wall((WALLS_IN_ROW - 1 + 0.5f) * Wall.defaultSize,
                (7 + 0.5f) * Wall.defaultSize, Color.YELLOW ));

        drawWallLine(world, 0, WALLS_IN_ROW/4 - 1, 8, 8, Color.ORANGE, true);
        drawWallLine(world, WALLS_IN_ROW/4, WALLS_IN_ROW/4, 4, 7, Color.YELLOW, true);
        drawWallLine(world, WALLS_IN_ROW - 1 - WALLS_IN_ROW/4, WALLS_IN_ROW - 1 - WALLS_IN_ROW/4,
                4, 7, Color.ORANGE, true);
        drawWallLine(world, 0, 9, WALLS_IN_COLUMN - 2, WALLS_IN_COLUMN - 2, Color.YELLOW, true);

        world.addWall(new Wall((1 + 0.5f) * Wall.defaultSize, (WALLS_IN_COLUMN - 1 + 0.5f) * Wall
                .defaultSize, Color.YELLOW , true));
        world.addWall(new Wall((3 + 0.5f) * Wall.defaultSize, (WALLS_IN_COLUMN - 1 + 0.5f) * Wall
                .defaultSize, Color.YELLOW , true));
        world.addWall(new Wall((5 + 0.5f) * Wall.defaultSize, (WALLS_IN_COLUMN - 1 + 0.5f) * Wall
                .defaultSize, Color.YELLOW , true));
        world.addWall(new Wall((7 + 0.5f) * Wall.defaultSize, (WALLS_IN_COLUMN - 1 + 0.5f) * Wall
                .defaultSize, Color.YELLOW , true));
        world.addWall(new Wall((9 + 0.5f) * Wall.defaultSize, (WALLS_IN_COLUMN - 1 + 0.5f) * Wall
                .defaultSize, Color.YELLOW , true));




    }

    private void initBars(WorldInitialization world) {
        drawBarLine(world, 0, WALLS_IN_ROW/4 - 1, 3, 3, Color.ORANGE, BarAlignment.HORIZONTAL);
        drawBarLine(world, WALLS_IN_ROW/4 + 1, WALLS_IN_ROW - WALLS_IN_ROW/4 - 2, 3, 3,
                Color.ORANGE,
                BarAlignment.HORIZONTAL);
        drawBarLine(world, WALLS_IN_ROW - WALLS_IN_ROW/4, WALLS_IN_ROW - 2, 3, 3,
                Color.ORANGE, BarAlignment.HORIZONTAL);

        drawBarLine(world, WALLS_IN_ROW/4 + 1, WALLS_IN_ROW - WALLS_IN_ROW/4 - 2, 8, 8,
                Color.YELLOW,
                BarAlignment.HORIZONTAL);
        drawBarLine(world, WALLS_IN_ROW - WALLS_IN_ROW/4, WALLS_IN_ROW - 1, 8, 8,
                Color.YELLOW, BarAlignment.HORIZONTAL);


        drawBarLine(world, WALLS_IN_ROW/4, WALLS_IN_ROW/4, 0, 2, Color.GREEN,
                BarAlignment.VERTICAL);
        drawBarLine(world, WALLS_IN_ROW - 1 - WALLS_IN_ROW/4, WALLS_IN_ROW - 1 - WALLS_IN_ROW/4,
                0, 2, Color.YELLOW, BarAlignment.VERTICAL);
        drawBarLine(world, WALLS_IN_ROW - 1 - WALLS_IN_ROW/4, WALLS_IN_ROW - 1 - WALLS_IN_ROW/4,
                9, WALLS_IN_COLUMN - 1, Color.YELLOW, BarAlignment.VERTICAL);

    }



    private void initBallGenerator(WorldInitialization world) {

        BallDescription description1 =
                new BallDescription(VelocityType.ANY_VELOCITY, Color.ORANGE,
                        new Coordinates((0.5f) * Wall.defaultSize,
                                (WALLS_IN_COLUMN - 1 + 0.5f) * Wall.defaultSize));

        BallDescription description2 =
                new BallDescription(VelocityType.ANY_VELOCITY, Color.ORANGE,
                        new Coordinates((2 + 0.5f) * Wall.defaultSize,
                                (WALLS_IN_COLUMN - 1 + 0.5f) * Wall.defaultSize));

        BallDescription description3 =
                new BallDescription(VelocityType.ANY_VELOCITY, Color.ORANGE,
                        new Coordinates((4 + 0.5f) * Wall.defaultSize,
                                (WALLS_IN_COLUMN - 1 + 0.5f) * Wall.defaultSize));

        BallDescription description4 =
                new BallDescription(VelocityType.ANY_VELOCITY, Color.ORANGE,
                        new Coordinates((6 + 0.5f) * Wall.defaultSize,
                                (WALLS_IN_COLUMN - 1 + 0.5f) * Wall.defaultSize));

        BallDescription description5 =
                new BallDescription(VelocityType.ANY_VELOCITY, Color.ORANGE,
                        new Coordinates((8 + 0.5f) * Wall.defaultSize,
                                (WALLS_IN_COLUMN - 1 + 0.5f) * Wall.defaultSize));

        BallDescription description6 =
                new BallDescription(VelocityType.FIXED_VELOCITY, Color.NONE,
                        new Coordinates((0 + 0.5f) * Wall.defaultSize,
                                (1 + 0.5f) * Wall.defaultSize),
                        new Coordinates(MIN_VELOCITY, 0f));


        initBallGenerator(world, VELOCITY_KOEF, MIN_VELOCITY, spawnInterval,
                description1, description2, description3, description4, description5, description6);

    }

    private void initHoles(WorldInitialization world) {

        world.addHole((new Hole((WALLS_IN_ROW - 1) * Wall.defaultSize,
                (WALLS_IN_COLUMN - 1) * Wall.defaultSize, Color.YELLOW)));
        world.addHole((new Hole(((WALLS_IN_ROW - 2 - WALLS_IN_ROW/4)) * Wall.defaultSize,
                (WALLS_IN_COLUMN - 1) * Wall.defaultSize, Color.ORANGE)));


    }

}
