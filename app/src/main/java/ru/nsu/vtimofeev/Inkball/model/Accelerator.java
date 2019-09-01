package ru.nsu.vtimofeev.Inkball.model;

import ru.nsu.vtimofeev.GameFramework.HelperClasses.ModelHelpers.GameObject;

/**
 * Created by IntelliJ IDEA.
 * User: vtimofeev
 * Date: 5/3/12
 * Time: 10:58 AM
 * To change this template use File | Settings | File Templates.
 */
public class Accelerator extends GameObject{

    public static final float defaultSize = Wall.defaultSize;
    public static final float acceleration = 8.0f;

    public final Direction direction;

    public Accelerator(float x, float y, Direction direction) {
        super(x, y, defaultSize, defaultSize);
        this.direction = direction;
    }

    public void affectBall(Ball ball) {
        if (direction == Direction.UP) {
            ball.accel.set(0f, acceleration);
        }
        else if (direction == Direction.LEFT) {
            ball.accel.set(-acceleration, 0f);
        }
        else if (direction == Direction.DOWN) {
            ball.accel.set(0f, -acceleration);
        }
        //direction == Direction.RIGHT
        else {
            ball.accel.set(acceleration, 0f);
        }
        ball.setAffected(true);
    }

    @Override
    public float getWidth() {
        return  defaultSize;
    }

    @Override
    public float getHeight() {
        return  defaultSize;
    }
}
