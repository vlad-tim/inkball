package ru.nsu.vtimofeev.Inkball.pool;

import ru.nsu.vtimofeev.GameFramework.HelperClasses.MathClasses.Segment;
import ru.nsu.vtimofeev.GameFramework.HelperClasses.MathClasses.Vector2;
import ru.nsu.vtimofeev.GameFramework.InputHandlers.Pool;
import ru.nsu.vtimofeev.Inkball.model.MultiLine;
import ru.nsu.vtimofeev.Inkball.model.World;

/**
 * Created by IntelliJ IDEA.
 * User: vtimofeev
 * Date: 4/5/12
 * Time: 10:20 PM
 * To change this template use File | Settings | File Templates.
 */
public class SegmentPool {

    public static final int INITIAL_SIZE = (World.MAX_MULTILINES * (MultiLine.MAX_SEGMENTS + 1));
    public static final int MAX_SIZE = 1500;

    private static Pool.PoolObjectFactory<Segment> factory = new Pool.PoolObjectFactory<Segment>() {
        @Override
        public Segment createObject() {
            return new Segment();
        }
    };
    private static Pool<Segment> pool = new Pool<Segment>(factory, INITIAL_SIZE, MAX_SIZE);

    public static Segment acquire(Vector2 firstPoint, Vector2 secondPoint) {
        Segment segment = pool.acquire();
        segment.set(firstPoint, secondPoint);
        return segment;
    }

    public static void release(Segment s) {
        Vector2Pool.release(s.v0);
        Vector2Pool.release(s.v1);
        s.v0 = null;
        s.v1 = null;
        pool.release(s);
    }

    public static void init() {

    }
}
