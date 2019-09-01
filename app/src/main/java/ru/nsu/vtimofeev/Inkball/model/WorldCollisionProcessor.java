package ru.nsu.vtimofeev.Inkball.model;

import android.util.FloatMath;
import ru.nsu.vtimofeev.GameFramework.HelperClasses.MathClasses.*;
import ru.nsu.vtimofeev.GameFramework.HelperClasses.ModelHelpers.GameObject;
import ru.nsu.vtimofeev.Inkball.pool.Vector2Pool;

/**
 * Created by IntelliJ IDEA.
 * User: vtimofeev
 * Date: 4/24/12
 * Time: 8:57 PM
 * To change this template use File | Settings | File Templates.
 */
public class WorldCollisionProcessor {

    private static float deltaPosition = Ball.radius / 8;
    private static int SIDES_IN_RECTANGLE = 4;

    Vector2 un = new Vector2();
    Vector2 ut = new Vector2();

    //Four segments as sides of the rectangle
    private Segment[] segments = new Segment[SIDES_IN_RECTANGLE];
    private Vector2 closestPoint = new Vector2();

    public WorldCollisionProcessor() {
        for (int i = 0; i < SIDES_IN_RECTANGLE; ++i) {

            segments[i] = new Segment();

            segments[i].v0 = new Vector2();
            segments[i].v1 = new Vector2();
        }
    }

    //Ellastic collision of two balls with the same mass
    //Tangent componets of VelocityType remain
    //Normal componets switch
    void processCollision(Ball firstBall, Ball secondBall) {

        if (hasAlreadyCollapsed(firstBall, secondBall)) return;
        Vector2 v1 = firstBall.velocity;
        Vector2 v2 = secondBall.velocity;

        Vector2 p1 = firstBall.position;
        Vector2 p2 = secondBall.position;

        un.set(p1);
        un.sub(p2);
        un.normalize();

        ut.set(-un.y, un.x);

        float v1n = un.dotProduct(v1);
        float v1t = ut.dotProduct(v1);

        float v2n = un.dotProduct(v2);
        float v2t = ut.dotProduct(v2);

        v1.set(un);
        v1.mul(v2n);

        Vector2 temp = Vector2Pool.acquire().set(ut);
        temp.mul(v1t);
        v1.add(temp);

        v2.set(un);
        v2.mul(v1n);
        temp.set(ut);
        temp.mul(v2t);
        v2.add(temp);
        Vector2Pool.release(temp);
    }

    boolean hasAlreadyCollapsed(Ball b1, Ball b2) {

        float b1X = b1.position.x;
        float b1Y = b1.position.y;

        float b2X = b2.position.x;
        float b2Y = b2.position.y;

        float b1FutureX = b1.position.x + b1.velocity.x * 0.01f;
        float b1FutureY = b1.position.y + b1.velocity.y * 0.01f;

        float b2FutureX = b2.position.x + b2.velocity.x * 0.01f;
        float b2FutureY = b2.position.y + b2.velocity.y * 0.01f;

        return ((b1X - b2X) * (b1X - b2X) + (b1Y - b2Y) * (b1Y - b2Y) <=
                (b1FutureX - b2FutureX) * (b1FutureX - b2FutureX) + (b1FutureY - b2FutureY) * (b1FutureY - b2FutureY));
    }

    void processCollisionWithSquareGameObject(Ball ball, GameObject squareGameObject) {

        moveBallBackwards(ball, squareGameObject);

        Rectangle wallBounds = (Rectangle) squareGameObject.bounds;
        extractSegments(wallBounds);

        Vector2 ballCenter = ((Circle) ball.bounds).center;

        Segment closestSegment = findClosestSegment(ballCenter);
        Vector2 wallCenter = (Vector2Pool.acquire().set(wallBounds.lowerLeft)).add(wallBounds
                .width /
                2,
                wallBounds.height / 2);

        reflectBallFromSegmentUsingWallCenter(ball, closestSegment, wallCenter);
        Vector2Pool.release(wallCenter);
    }

    private void moveBallBackwards(Ball ball, GameObject squareGameObject) {

        float totalDistance = 0f;

        do {
            ball.moveBackwards(deltaPosition);
            totalDistance += deltaPosition;
        } while (OverlapTester.overlapCircleRectangle((Circle) ball.bounds,
                (Rectangle) squareGameObject.bounds));

        if (totalDistance > Ball.radius) {
            //Rollback
            ball.moveForward(totalDistance);
        }

    }

    private void extractSegments(Rectangle bounds) {
        segments[0].v0.set(bounds.lowerLeft);
        (segments[0].v1.set(bounds.lowerLeft)).add(0f, bounds.height);

        (segments[1].v0.set(bounds.lowerLeft)).add(0f, bounds.height);
        (segments[1].v1.set(bounds.lowerLeft)).add(bounds.width, bounds.height);

        (segments[2].v0.set(bounds.lowerLeft)).add(bounds.width, bounds.height);
        (segments[2].v1.set(bounds.lowerLeft)).add(bounds.width, 0f);

        (segments[3].v0.set(bounds.lowerLeft)).add(bounds.width, 0f);
        segments[3].v1.set(bounds.lowerLeft);
    }

    private Segment findClosestSegment(Vector2 ballCenter) {

        Segment closestSegment = segments[0];
        float closestDistanceSquared = closestSegment.distanceToVectorSquared(ballCenter);

        for (int i = 1; i < SIDES_IN_RECTANGLE; ++i) {

            Segment currentSegment = segments[i];
            if (currentSegment.distanceToVectorSquared(ballCenter) < closestDistanceSquared) {

                closestSegment = currentSegment;
                closestDistanceSquared = closestSegment.distanceToVectorSquared(ballCenter);
            }
        }

        return closestSegment;
    }

    private float getDistanceToClosestSegment(Ball ball, GameObject squareGameObject) {

        Rectangle wallBounds = (Rectangle) squareGameObject.bounds;
        extractSegments(wallBounds);

        Vector2 ballCenter = ((Circle) ball.bounds).center;
        Segment closestSegment = findClosestSegment(ballCenter);

        return FloatMath.sqrt(closestSegment.distanceToVectorSquared(ballCenter));
    }

    private void reflectBallFromSegmentUsingWallCenter(Ball ball, Segment closestSegment,
                                                       Vector2 wallCenter) {

        Vector2 ballCenter = ((Circle) ball.bounds).center;
        Vector2 middlePoint = Vector2Pool.acquire();
        middlePoint.set((closestSegment.v0.x + closestSegment.v1.x)/2 ,
                (closestSegment.v0.y + closestSegment.v1.y)/2);                

        closestPoint.set(closestSegment.closestPoint(ballCenter));
        Vector2 v1 = ball.velocity;

        //We we collide with corner...
        if (closestPoint.identical(closestSegment.v0) ||
                (closestPoint.identical(closestSegment.v1))) {

            if (closestPoint.identical(closestSegment.v0)) {
                (un.set(ballCenter, closestSegment.v0)).normalize();
            }
            else {
                (un.set(ballCenter, closestSegment.v1)).normalize();
            }

            //Stub for particular case
            if (un.x * v1.x <=0 && un.y * v1.y <=0
                    && (Math.abs(Math.abs(v1.x) - Math.abs(v1.y)) <= Float.MIN_VALUE)) {
                v1.mul(-1f);
                Vector2Pool.release(middlePoint);
                return;
            }
        }
        else {
            (un.set(middlePoint, wallCenter)).normalize();
        }

        ut.set(-un.y, un.x);

        float v1n = un.dotProduct(v1);
        float v1t = ut.dotProduct(v1);

        v1.set(un);
        v1.mul(-v1n);

        Vector2 temp = Vector2Pool.acquire().set(ut);
        temp.mul(v1t);
        v1.add(temp);

        Vector2Pool.release(temp);
        Vector2Pool.release(middlePoint);
    }

    public float getCollisionDistance(Ball ball, GameObject squareGameObject) {
        return getDistanceToClosestSegment(ball, squareGameObject);
    }
}
