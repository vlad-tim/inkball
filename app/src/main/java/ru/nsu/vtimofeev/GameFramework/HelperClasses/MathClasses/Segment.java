package ru.nsu.vtimofeev.GameFramework.HelperClasses.MathClasses;

import ru.nsu.vtimofeev.Inkball.model.MultiLine;
import ru.nsu.vtimofeev.Inkball.pool.Vector2Pool;

/**
 * Created by IntelliJ IDEA.
 * User: vtimofeev
 * Date: 3/28/12
 * Time: 7:33 PM
 * To change this template use File | Settings | File Templates.
 */
public class Segment {

    public Vector2 v0;
    public Vector2 v1;
    private Vector2 v = new Vector2();
    private Vector2 w = new Vector2();
    private Vector2 closestPoint = new Vector2();

    public MultiLine getMultiLine() {
        return multiLine;
    }

    public void setMultiLine(MultiLine multiLine) {
        this.multiLine = multiLine;
    }

    private MultiLine multiLine = null;

    public Segment() {
    }

    public Segment(Vector2 v0, Vector2 v1) {
        this.v0 = v0;
        this.v1 = v1;
    }

    public float distanceToVectorSquared(Vector2 vector2) {

        float distance = vector2.distSquared(closestPoint(vector2));
        return distance;
    }

    //from http://algolist.manual.ru/maths/geom/distance/pointline.php
    public Vector2 closestPoint(Vector2 vector2) {

        v.set(v1, v0);

        w.set(vector2, v0);

        float c1 = v.dotProduct(w);
        if (c1 <= 0) {
            closestPoint.set(v0);
            return closestPoint;
        }

        float c2 = v.dotProduct(v);
        if (c2 <= c1) {
            closestPoint.set(v1);
            return closestPoint;
        }

        float b = c1 / c2;

        closestPoint.set(v0);
        v.mul(b);
        closestPoint.add(v);
        return closestPoint;
    }

    public void set(Vector2 v0, Vector2 v1) {
        this.v0 = v0;
        this.v1 = v1;
    }
}
