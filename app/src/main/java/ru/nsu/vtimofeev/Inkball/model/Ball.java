package ru.nsu.vtimofeev.Inkball.model;

import android.util.FloatMath;
import android.util.Log;
import ru.nsu.vtimofeev.GameFramework.HelperClasses.MathClasses.Circle;
import ru.nsu.vtimofeev.GameFramework.HelperClasses.MathClasses.OverlapTester;
import ru.nsu.vtimofeev.GameFramework.HelperClasses.MathClasses.Segment;
import ru.nsu.vtimofeev.GameFramework.HelperClasses.MathClasses.Vector2;
import ru.nsu.vtimofeev.GameFramework.HelperClasses.ModelHelpers.DynamicGameObject;
import ru.nsu.vtimofeev.GameFramework.HelperClasses.ModelHelpers.GameObject;
import ru.nsu.vtimofeev.Inkball.pool.Vector2Pool;

/**
 * Created by IntelliJ IDEA.
 * User: vtimofeev
 * Date: 3/23/12
 * Time: 10:20 AM
 * To change this template use File | Settings | File Templates.
 */
public class Ball extends DynamicGameObject {


    public static float radius = (Wall.defaultSize / 3);
    public static float MAX_VELOCITY = 15.0f;
    private Color color = Color.NONE;

    private boolean isAffected = false;

    Vector2 un = new Vector2();
    Vector2 ut = new Vector2();

    private Vector2 closestPoint = new Vector2();

    public Ball(float x, float y, Color color) {
        super(x, y, radius);
        this.color = color;
    }


    public void moveBackwards(float distance) {
        //Some math involved. Though this method does exactly what you expect

        float deltaX = (velocity.x == 0f) ? 0.0f :
                (float) (Math.signum(velocity.x) * (distance / Math.sqrt(1 + (velocity.y / velocity
                        .x) * (velocity.y / velocity.x))));
        float deltaY = (velocity.y == 0f) ? 0.0f :
                (float) (Math.signum(velocity.y) * distance / Math.sqrt(1 + (velocity.x / velocity.y)
                        * (velocity.x / velocity.y)));
        position.add(-deltaX, -deltaY);
        ((Circle) bounds).center.set(position);
    }

    public void moveForward(float distance) {
        //Some math involved. Though this method does exactly what you expect

        float deltaX = (velocity.x == 0f) ? 0.0f :
                (float) (Math.signum(velocity.x) * (distance / Math.sqrt(1 + (velocity.y / velocity
                        .x) * (velocity.y / velocity.x))));
        float deltaY = (velocity.y == 0f) ? 0.0f :
                (float) (Math.signum(velocity.y) * distance / Math.sqrt(1 + (velocity.x / velocity.y)
                        * (velocity.x / velocity.y)));
        position.add(deltaX, deltaY);
        ((Circle) bounds).center.set(position);
    }

    public boolean overlapsWithAnotherBall(Ball ball) {
        if (this.equals(ball)) {
            return false;
        } else {
            return OverlapTester.overlapCircles((Circle) this.bounds, (Circle) ball.bounds);
        }
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public void reflectFromSegment(Segment overlapedSegment) {


        closestPoint.set(overlapedSegment.closestPoint(((Circle) bounds).center));

        Vector2 v1 = velocity;
        Vector2 p1 = position;
        un.set(closestPoint.x - p1.x, closestPoint.y - p1.y);

        un.normalize();
        ut.set(-un.y, un.x);

        float v1n = un.dotProduct(v1);
        float v1t = ut.dotProduct(v1);

        v1.set(un);
        v1.mul(-v1n);

        un.set(ut);
        un.mul(v1t);
        v1.add(un);
    }

    public boolean isAffected() {
        return isAffected;
    }

    public void setAffected(boolean affected) {
        isAffected = affected;

        if (!affected) {
            accel.set(0f, 0f);
        }
    }

    @Override
    public float getWidth() {
        return radius * 2;
    }

    @Override
    public float getHeight() {
        return radius * 2;
    }

    public void update(float deltaTime) {
        velocity.add(accel.x*deltaTime, accel.y*deltaTime);

        if (velocity.lenSquared() > MAX_VELOCITY * MAX_VELOCITY) {

            float velocityAbsSquared = velocity.lenSquared();
            float koef = MAX_VELOCITY * MAX_VELOCITY / velocityAbsSquared;
            velocity.mul(FloatMath.sqrt(koef));
        }
        position.add(velocity.x * deltaTime, velocity.y * deltaTime);
        ((Circle) bounds).center.set(position);
    }

    public boolean overlapsSegment(Segment lastSegment) {

        float criticalDistanceSquared =
                (radius + MultiLine.defaultRadius) * (radius + MultiLine.defaultRadius);

        return (lastSegment.distanceToVectorSquared(((Circle)bounds).center) <
                criticalDistanceSquared);
    }

    public void reflectFromSegmentDirect(Segment lastSegment) {

        //Use un as segment vector
        un.set(lastSegment.v1, lastSegment.v0);

        float velAngle = velocity.angle();
        float segmentAngle = un.angle();

        velocity.rotate(segmentAngle - velAngle);
    }

    public void accelerate() {
        if (velocity.lenSquared() > 9f) {
            velocity.mul(1.33f);
        }
        else {
            un.set(velocity);
            un.normalize();
            velocity.add(un);
        }
    }
}
