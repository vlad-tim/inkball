package ru.nsu.vtimofeev.Inkball.levels;

import android.util.FloatMath;
import ru.nsu.vtimofeev.GameFramework.HelperClasses.MathClasses.Vector2;
import ru.nsu.vtimofeev.Inkball.levels.generation.BallDescription;
import ru.nsu.vtimofeev.Inkball.levels.generation.VelocityType;
import ru.nsu.vtimofeev.Inkball.model.*;

import java.util.List;
import java.util.Random;

/**
 * Created by IntelliJ IDEA.
 * User: vtimofeev
 * Date: 6/21/12
 * Time: 3:06 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class LevelInitializer {

    public static final int WALLS_IN_COLUMN = World.WALLS_IN_COLUMN;
    public static final int WALLS_IN_ROW = World.WALLS_IN_ROW;

    protected  Random random = new Random();

    //In this method we add different objects
    public abstract void initialize(WorldInitialization worldInitialization);

    //Constructor is protected
    protected LevelInitializer() {}

    public String getFirstHint() {
        return null;
    }
    public String getSecondHint(){
        return null;
    }

    //Standart way to create border walls
    protected void initBorderWalls(WorldInitialization world) {

        //Bound Walls
        for (int i = 0; i < WALLS_IN_COLUMN; ++i) {
            world.addWall(new Wall(Wall.defaultSize / 2, (i + 0.5f) * Wall.defaultSize));
            world.addWall(new Wall((WALLS_IN_ROW - 0.5f) * Wall.defaultSize,
                    (i + 0.5f) * Wall.defaultSize));
        }
        for (int i = 0; i < WALLS_IN_ROW; ++i) {
            world.addWall(new Wall((i + 0.5f) * Wall.defaultSize, Wall.defaultSize / 2));
            world.addWall(new Wall((i + 0.5f) * Wall.defaultSize, (WALLS_IN_COLUMN - 0.5f) * Wall.defaultSize));
        }
    }

    //If our random gave us speed which is too low, we increase it till MIN_VELOCITY
    protected void correctVelocity(Vector2 velocity, float MIN_VELOCITY) {

        float velocityAbs = velocity.len();

        if (velocityAbs < MIN_VELOCITY) {

            float velocityAbsSquared = velocityAbs * velocityAbs;
            float koef = MIN_VELOCITY * MIN_VELOCITY / velocityAbsSquared;
            velocity.mul(FloatMath.sqrt(koef));
        }
    }
    
    public void drawWallLine(WorldInitialization worldInitialization, int minX, 
                             int maxX, int minY, int maxY, Color color, boolean isCracked) {
        
        for (int  i = minX; i <= maxX; ++i ) {
            for (int j = minY; j <= maxY; ++j) {
                worldInitialization.addWall(new Wall((i + 0.5f) * Wall.defaultSize, 
                        (j + 0.5f)*Wall.defaultSize, color, isCracked));
            }
        }
    }

    public void drawTimerWallLine(WorldInitialization worldInitialization, int minX,
                             int maxX, int minY, int maxY) {

        for (int  i = minX; i <= maxX; ++i ) {
            for (int j = minY; j <= maxY; ++j) {
                worldInitialization.addTimerWall(new TimerWall((i + 0.5f) * Wall.defaultSize,
                        (j + 0.5f) * Wall.defaultSize));
            }
        }
    }
    public void drawTimerWallLine(WorldInitialization worldInitialization, int minX,
                                  int maxX, int minY, int maxY, TimerWallState state) {

        for (int  i = minX; i <= maxX; ++i ) {
            for (int j = minY; j <= maxY; ++j) {
                worldInitialization.addTimerWall(new TimerWall((i + 0.5f) * Wall.defaultSize,
                        (j + 0.5f) * Wall.defaultSize, state));
            }
        }
    }


    public void drawBarLine(WorldInitialization worldInitialization, int minX,
                             int maxX, int minY, int maxY, Color color, Direction direction) {

        for (int  i = minX; i <= maxX; ++i ) {
            for (int j = minY; j <= maxY; ++j) {
                worldInitialization.addBar(new Bar((i + 0.5f) * Wall.defaultSize,
                        (j + 0.5f) * Wall.defaultSize, color, direction));
            }
        }
    }

    public void drawBarLine(WorldInitialization worldInitialization, int minX,
                            int maxX, int minY, int maxY, Color color, BarAlignment alignment) {

        for (int  i = minX; i <= maxX; ++i ) {
            for (int j = minY; j <= maxY; ++j) {
                worldInitialization.addBar(new Bar((i + 0.5f) * Wall.defaultSize,
                        (j + 0.5f) * Wall.defaultSize, color, alignment));
            }
        }
    }

    public void drawAccelLine(WorldInitialization worldInitialization, int minX,
                            int maxX, int minY, int maxY, Direction direction) {

        for (int  i = minX; i <= maxX; ++i ) {
            for (int j = minY; j <= maxY; ++j) {
                worldInitialization.addAccel(new Accelerator((i + 0.5f) * Wall.defaultSize,
                        (j + 0.5f) * Wall.defaultSize, direction));
            }
        }
    }

    public void drawPuzzleLine(WorldInitialization worldInitialization, int minX,
                            int maxX, int minY, int maxY, Color color, PuzzleState state) {

        for (int  i = minX; i <= maxX; ++i ) {
            for (int j = minY; j <= maxY; ++j) {
                worldInitialization.addPuzzle(new Puzzle((i + 0.5f) * Wall.defaultSize,
                        (j + 0.5f) * Wall.defaultSize, color, state));
            }
        }
    }

    public void drawPuzzleLine(WorldInitialization worldInitialization, float minX,
                               float maxX, float minY, float maxY, Color color, PuzzleState state) {

        for (float  i = minX; i <= maxX; ++i ) {
            for (float j = minY; j <= maxY; ++j) {
                worldInitialization.addPuzzle(new Puzzle((i + 0.5f) * Wall.defaultSize,
                        (j + 0.5f) * Wall.defaultSize, color, state));
            }
        }
    }


    public void drawPuzzleRectangle(WorldInitialization worldInitialization, int minX,
                               int maxX, int minY, int maxY, Color color, PuzzleState state) {

        for (int  i = minX; i <= maxX; ++i ) {
            for (int j = minY; j <= maxY; ++j) {
                if (j != minY && j != maxY && i != minX && i != maxX) {
                    continue;
                }
                worldInitialization.addPuzzle(new Puzzle((i + 0.5f) * Wall.defaultSize,
                        (j + 0.5f) * Wall.defaultSize, color, state));
            }
        }
    }

    protected void generateBallAnyVelocityDefaultCoordinates
            (BallGenerator generator, Color color, float velocityCoef, float minVel) {

        Ball newBall = new Ball(generator.position.x, generator.position.y,
                color);

        float velX = (-1f + 2*random.nextFloat())*velocityCoef;
        float velY = (-1f + 2*random.nextFloat())*velocityCoef;

        newBall.velocity.set(velX, velY);
        correctVelocity(newBall.velocity, minVel);

        generator.scheduleBall(newBall);
    }

    protected void generateBallAnyVelocity
            (float x, float y ,
             BallGenerator generator, Color color,
             float velocityCoef, float minVel) {

        Ball newBall = new Ball(x, y,
                color);

        float velX = (-1f + 2*random.nextFloat())*velocityCoef;
        float velY = (-1f + 2*random.nextFloat())*velocityCoef;

        newBall.velocity.set(velX, velY);
        correctVelocity(newBall.velocity, minVel);

        generator.scheduleBall(newBall);
    }

    protected void generateBallYVelocity
            (float x, float y ,
             BallGenerator generator, Color color,
             float velocityCoef, float minVel) {

        Ball newBall = new Ball(x, y,
                color);

        float velX = 0f;
        float velY = (-1f + 2*random.nextFloat())*velocityCoef;

        newBall.velocity.set(velX, velY);
        correctVelocity(newBall.velocity, minVel);

        generator.scheduleBall(newBall);
    }

    protected void generateBallXVelocity
            (float x, float y ,
             BallGenerator generator, Color color,
             float velocityCoef, float minVel) {

        Ball newBall = new Ball(x, y,
                color);

        float velY = 0f;
        float velX = (-1f + 2*random.nextFloat())*velocityCoef;

        newBall.velocity.set(velX, velY);
        correctVelocity(newBall.velocity, minVel);

        generator.scheduleBall(newBall);
    }

    protected void generateBallFixedVelocity(float x, float y, BallGenerator generator,
                                           Color color, float Vx, float Vy) {
        Ball newBall = new Ball(x, y,
                color);

        float velY = Vy;
        float velX = Vx;

        newBall.velocity.set(velX, velY);

        generator.scheduleBall(newBall);
    }

    protected void initBallGenerator(WorldInitialization world, float generatorX,
        float generatorY, float VELOCITY_KOEF, float MIN_VELOCITY, float spawnInterval,
        BallDescription... descriptions) {

        List<Ball> balls = world.getBalls();
        BallGenerator ballGenerator = new BallGenerator(generatorX, generatorY, balls);

        for (BallDescription description : descriptions) {
            VelocityType velocity = description.velocity;

            if (velocity == VelocityType.ANY_VELOCITY_DEFAULT_COORDINATES) {
                generateBallAnyVelocityDefaultCoordinates(ballGenerator, description.color,
                        VELOCITY_KOEF, MIN_VELOCITY);
            }
        }

        ballGenerator.setSpawnInterval(spawnInterval);
        world.setBallGenerator(ballGenerator);

    }

    protected void initBallGenerator(WorldInitialization world, float VELOCITY_KOEF, float MIN_VELOCITY, float spawnInterval,
                                     BallDescription... descriptions) {

        List<Ball> balls = world.getBalls();
        BallGenerator ballGenerator = new BallGenerator(balls);

        for (BallDescription description : descriptions) {
            VelocityType velocity = description.velocity;

            if (velocity == VelocityType.FIXED_VELOCITY) {
                generateBallFixedVelocity(description.pos.x, description.pos.y,
                        ballGenerator, description.color, description.vel.x,
                        description.vel.y);
            }

            else if (velocity == VelocityType.ANY_VELOCITY) {
                generateBallAnyVelocity(description.pos.x, description.pos.y,
                        ballGenerator, description.color, VELOCITY_KOEF,
                        MIN_VELOCITY);
            }
        }

        ballGenerator.setSpawnInterval(spawnInterval);
        world.setBallGenerator(ballGenerator);

    }
}
