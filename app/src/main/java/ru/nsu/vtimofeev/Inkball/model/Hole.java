package ru.nsu.vtimofeev.Inkball.model;

import ru.nsu.vtimofeev.GameFramework.HelperClasses.MathClasses.Vector2;
import ru.nsu.vtimofeev.GameFramework.HelperClasses.ModelHelpers.GameObject;
import ru.nsu.vtimofeev.Inkball.pool.Vector2Pool;

/**
 * Created by IntelliJ IDEA.
 * User: vtimofeev
 * Date: 3/23/12
 * Time: 11:33 AM
 * To change this template use File | Settings | File Templates.
 */
public class Hole extends GameObject {

    public static final float defaultSize = (Wall.defaultSize * 2);
    public static final float defaultGravityModule = 8.0f;
    public static final float defaultFrictionForce = 3.0f;

    private Color color = Color.NONE;

    public Hole(float x, float y) {
        super(x, y, defaultSize, defaultSize);
    }

    public Hole(float x, float y, Color color) {
        super(x, y, defaultSize, defaultSize);
        this.color = color;
    }

    public void affectBall(Ball ball) {
        ((ball.accel.set(this.position, ball.position)).normalize()).mul(Hole
                .defaultGravityModule);
        Vector2 temp = Vector2Pool.acquire();
        ((temp.set(ball.velocity)).normalize()).mul(-defaultFrictionForce);
        ball.accel.add(temp);
        ball.setAffected(true);
        Vector2Pool.release(temp);
    }

    @Override
    public float getWidth() {
        return defaultSize;
    }

    @Override
    public float getHeight() {
        return defaultSize;
    }

    public Color getColor() {
        return color;
    }
}
