package ru.nsu.vtimofeev.Inkball.levels.easy;

import ru.nsu.vtimofeev.Inkball.levels.LevelInitializer;
import ru.nsu.vtimofeev.Inkball.levels.generation.BallDescription;
import ru.nsu.vtimofeev.Inkball.levels.generation.VelocityType;
import ru.nsu.vtimofeev.Inkball.model.*;

/**
 * Created by IntelliJ IDEA.
 * User: vtimofeev
 * Date: 5/17/12
 * Time: 10:40 PM
 * To change this template use File | Settings | File Templates.
 */
public class Level6Initializer extends LevelInitializer {

    private float spawnInterval = 4f;

    private static float MIN_VELOCITY = 2.6f;
    private static float VELOCITY_KOEF = 2.9f;




    public void initialize(WorldInitialization world) {

        initBorderWalls(world);
        initWalls(world) ;
        initHoles(world);
        initBallGenerator(world);
    }

    @Override
    protected void initBorderWalls(WorldInitialization world) {

        //Bound Walls
        for (int i = 3; i < WALLS_IN_COLUMN - 4; ++i) {
            world.addWall(new Wall(Wall.defaultSize / 2, (i + 0.5f) * Wall.defaultSize));
            world.addWall(new Wall((WALLS_IN_ROW - 0.5f) * Wall.defaultSize,
                    (i + 0.5f) * Wall.defaultSize));
        }
        for (int i = 3; i < WALLS_IN_ROW - 3; ++i) {
            world.addWall(new Wall((i + 0.5f) * Wall.defaultSize, Wall.defaultSize / 2));
        }
        for (int i = 4; i < WALLS_IN_ROW - 4; ++i) {
            world.addWall(new Wall((i + 0.5f) * Wall.defaultSize, (WALLS_IN_COLUMN - 0.5f) * Wall.defaultSize));
        }

        for (int i = 0; i <= 2; ++i) {
            for (int j = 0; j <= 2 - i; ++j) {
                world.addWall(new Wall((i + 0.5f) * Wall.defaultSize,
                        (j + 0.5f) * Wall.defaultSize, (i + j == 2) ? Color.NONE : Color.GREEN));
                world.addWall(new Wall((WALLS_IN_ROW - 1 - i + 0.5f) * Wall.defaultSize,
                        (j + 0.5f) * Wall.defaultSize, (i + j == 2) ? Color.NONE : Color.GREEN));
            }
        }

        for (int i = 0; i <= 3; ++i) {
            for (int j = 0; j <= 3 - i; ++j) {
                world.addWall(new Wall((i + 0.5f) * Wall.defaultSize,
                        (WALLS_IN_COLUMN - 1 - j + 0.5f) * Wall.defaultSize,
                        (i + j == 3) ? Color.NONE : Color.GREEN));
                world.addWall(new Wall((WALLS_IN_ROW - 1 - i + 0.5f) * Wall.defaultSize,
                        (WALLS_IN_COLUMN - 1 - j + 0.5f) * Wall.defaultSize,
                        (i + j == 3) ? Color.NONE : Color.GREEN));
            }
        }
    }


    private void initWalls(WorldInitialization world) {

        for (int j = 1; j <= 4; ++j) {
            world.addWall(new Wall((WALLS_IN_ROW/2  - 1+ 0.5f) * Wall.defaultSize,
                    (j + 0.5f) * Wall
                    .defaultSize));
        }

        world.addWall(new Wall((WALLS_IN_ROW/2 + 0.5f) * Wall.defaultSize,
                (4 + 0.5f) * Wall
                        .defaultSize));
        world.addWall(new Wall((WALLS_IN_ROW/2 + 1.5f) * Wall.defaultSize,
                (4 + 0.5f) * Wall
                        .defaultSize));

        for (int i = WALLS_IN_ROW/2 - 3; i <= WALLS_IN_ROW/2 + 3; ++i) {
            for (int j = WALLS_IN_COLUMN/2 - Math.min(Math.abs( 3 - Math.abs(WALLS_IN_ROW/2 - i))
                    ,1);
                 j <= WALLS_IN_COLUMN/2 + Math.abs( 3 - Math.abs(WALLS_IN_ROW/2 - i)); ++j) {

                Color color;
                if (i == WALLS_IN_ROW/2 && j == WALLS_IN_COLUMN/2 ||
                        Math.abs(i - WALLS_IN_ROW/2) + Math.abs(j - WALLS_IN_COLUMN/2) == 3){
                    color = Color.NONE;
                }
                else {
                    color = Color.GREEN;
                }
                world.addWall(new Wall((i + 0.5f) * Wall.defaultSize,
                        (j + 0.5f) * Wall.defaultSize, color));
            }
        }

    }

    private void initBallGenerator(WorldInitialization world) {


        BallDescription description1 =
                new BallDescription(VelocityType.ANY_VELOCITY_DEFAULT_COORDINATES, Color.BLUE);

        BallDescription description2 =
                new BallDescription(VelocityType.ANY_VELOCITY_DEFAULT_COORDINATES, Color.BLUE);

        initBallGenerator(world, (WALLS_IN_ROW/2 + 0.5f) * Wall.defaultSize,
                (1 + 0.5f) * Wall.defaultSize, VELOCITY_KOEF, MIN_VELOCITY, spawnInterval,
                description1, description2);
    }



    private void initHoles(WorldInitialization world) {
        world.addHole(new Hole((WALLS_IN_ROW / 2 - 2f) * Wall.defaultSize,
                (2f) * Wall.defaultSize, Color.BLUE));
    }

}
