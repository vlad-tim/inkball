package ru.nsu.vtimofeev.Inkball.levels.master;

import android.util.FloatMath;
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
public class Level41Initializer extends LevelInitializer {

    private float spawnInterval = 3f;

    private static float MIN_VELOCITY = 5.0f;
    private static float VELOCITY_KOEF = 6.0f;
    
    private Vector2 temp = new Vector2();

    public void initialize(WorldInitialization world) {

        initBorderWalls(world);
        initWalls(world);
        initAccels(world);
        initHoles(world);
        initBallGenerator(world);

        world.setTime(50f);
    }

    @Override
    protected void initBorderWalls(WorldInitialization world) {

        drawWallLine(world, -1, -1, 0, WALLS_IN_COLUMN - 1, Color.NONE, false);
        drawWallLine(world, WALLS_IN_ROW, WALLS_IN_ROW, 0, WALLS_IN_COLUMN - 1, Color.NONE, false);

        drawWallLine(world, 0, WALLS_IN_ROW - 1, -1, -1, Color.NONE, false);
        drawWallLine(world, 0, WALLS_IN_ROW - 1, WALLS_IN_COLUMN, WALLS_IN_COLUMN, Color.NONE, false);
    }

    private void initWalls(WorldInitialization world) {

        drawWallLine(world, 0 , WALLS_IN_ROW - 4, 0, 0, Color.BLUE, false);
        drawWallLine(world, WALLS_IN_ROW - 1 , WALLS_IN_ROW - 1, 0, WALLS_IN_COLUMN - 4,
                Color.ORANGE, false);
        drawWallLine(world, 3 , WALLS_IN_ROW - 1, WALLS_IN_COLUMN - 1, WALLS_IN_COLUMN - 1,
                Color.YELLOW, false);
        drawWallLine(world, 0 , 0, 3, WALLS_IN_COLUMN - 1,
                Color.GREEN, false);

    }


    private void initAccels(WorldInitialization world) {

        world.addAccel(new Accelerator(0.5f * Wall.defaultSize, (2 + 0.5f) * Wall.defaultSize,
                Direction.UP));
        world.addAccel(new Accelerator((1 + 0.5f) * Wall.defaultSize, (1 + 0.5f) * Wall.defaultSize,
                Direction.RIGHT));

        world.addAccel(new Accelerator((1 + 0.5f) * Wall.defaultSize,
                (WALLS_IN_COLUMN - 2 + 0.5f) * Wall.defaultSize, Direction.DOWN));
        world.addAccel(new Accelerator((2 + 0.5f) * Wall.defaultSize,
                (WALLS_IN_COLUMN - 1 + 0.5f) * Wall.defaultSize,
                Direction.RIGHT));

        world.addAccel(new Accelerator((WALLS_IN_ROW - 2 + 0.5f) * Wall.defaultSize,
                (WALLS_IN_COLUMN - 2 + 0.5f) * Wall.defaultSize, Direction.LEFT));
        world.addAccel(new Accelerator((WALLS_IN_ROW - 1 + 0.5f) * Wall.defaultSize,
                (WALLS_IN_COLUMN - 3 + 0.5f) * Wall.defaultSize,
                Direction.DOWN));

        world.addAccel(new Accelerator((WALLS_IN_ROW - 3 + 0.5f) * Wall.defaultSize,
                (0.5f) * Wall.defaultSize, Direction.LEFT));
        world.addAccel(new Accelerator((WALLS_IN_ROW - 2 + 0.5f) * Wall.defaultSize,
                (1 + 0.5f) * Wall.defaultSize,
                Direction.UP));
    }



    private void initBallGenerator(WorldInitialization world) {

        getRandomPosition(temp);
        BallDescription description1 =
                new BallDescription(VelocityType.ANY_VELOCITY, Color.YELLOW,
                        new Coordinates(temp.x, temp.y));

        getRandomPosition(temp);
        BallDescription description2 =
                new BallDescription(VelocityType.ANY_VELOCITY, Color.BLUE,
                        new Coordinates(temp.x, temp.y));

        getRandomPosition(temp);
        BallDescription description3 =
                new BallDescription(VelocityType.ANY_VELOCITY, Color.GREEN,
                        new Coordinates(temp.x, temp.y));

        getRandomPosition(temp);
        BallDescription description4 =
                new BallDescription(VelocityType.ANY_VELOCITY, Color.GREEN,
                        new Coordinates(temp.x, temp.y));

        getRandomPosition(temp);
        BallDescription description5 =
                new BallDescription(VelocityType.ANY_VELOCITY, Color.GREEN,
                        new Coordinates(temp.x, temp.y));

        getRandomPosition(temp);
        BallDescription description6 =
                new BallDescription(VelocityType.ANY_VELOCITY, Color.BLUE,
                        new Coordinates(temp.x, temp.y));


        initBallGenerator(world, VELOCITY_KOEF, MIN_VELOCITY, spawnInterval,
                description1, description2, description3, description4,
                description5, description6);

    }

    private void getRandomPosition(Vector2 temp) {
        
        float r = random.nextFloat();
        
        if (r < 0.25f) {
            temp.set((0.5f) * Wall.defaultSize,
                    (1 + 0.5f) * Wall.defaultSize);
        }
        else if (r < 0.5f) {
            temp.set((1+ 0.5f) * Wall.defaultSize,
                    (WALLS_IN_COLUMN - 1 + 0.5f) * Wall.defaultSize);
        }
        else if (r < 0.75f) {
            temp.set((WALLS_IN_ROW - 1 + 0.5f) * Wall.defaultSize,
                    (WALLS_IN_COLUMN - 2 + 0.5f) * Wall.defaultSize);
        }
        else {
            temp.set((WALLS_IN_ROW -2 + 0.5f) * Wall.defaultSize,
                    (0.5f) * Wall.defaultSize);
        }
    }


    private void initHoles(WorldInitialization world) {

        world.addHole((new Hole((WALLS_IN_ROW/2f - 2) * Wall.defaultSize,
                (WALLS_IN_COLUMN/2f) * Wall.defaultSize, Color.ORANGE)));
        world.addHole((new Hole((WALLS_IN_ROW/2f + 2) * Wall.defaultSize,
                (WALLS_IN_COLUMN/2f) * Wall.defaultSize, Color.GREEN)));

        world.addHole((new Hole((WALLS_IN_ROW/2f) * Wall.defaultSize,
                (WALLS_IN_COLUMN/2f - 2) * Wall.defaultSize, Color.YELLOW)));
        world.addHole((new Hole((WALLS_IN_ROW/2f) * Wall.defaultSize,
                (WALLS_IN_COLUMN/2f + 2) * Wall.defaultSize, Color.BLUE)));

    }

}
