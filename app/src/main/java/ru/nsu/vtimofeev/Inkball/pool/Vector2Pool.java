package ru.nsu.vtimofeev.Inkball.pool;

import android.util.Log;
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
public class Vector2Pool {

    public static final int INITIAL_SIZE = 2*(World.MAX_MULTILINES * (MultiLine.MAX_SEGMENTS + 1));
    public static final int MAX_SIZE = 3000;

    private static Pool.PoolObjectFactory<Vector2> factory = new Pool.PoolObjectFactory<Vector2>() {
        @Override
        public Vector2 createObject() {
            return new Vector2();
        }
    };
    private static Pool<Vector2> pool = new Pool<Vector2>(factory, INITIAL_SIZE, MAX_SIZE);

    public static Vector2 acquire() {
        return pool.acquire();
    }

    public static void release(Vector2 v) {
        pool.release(v);
    }

    public static void init() {

    }
}
