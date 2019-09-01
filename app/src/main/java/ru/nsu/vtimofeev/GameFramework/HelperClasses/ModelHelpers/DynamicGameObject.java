package ru.nsu.vtimofeev.GameFramework.HelperClasses.ModelHelpers;

import ru.nsu.vtimofeev.GameFramework.HelperClasses.MathClasses.Circle;
import ru.nsu.vtimofeev.GameFramework.HelperClasses.MathClasses.Vector2;

import java.io.Serializable;

public abstract class DynamicGameObject extends GameObject implements Serializable {
    public final Vector2 velocity;
    public final Vector2 accel;

    public DynamicGameObject(float x, float y, float width, float height) {
        super(x, y, width, height);
        velocity = new Vector2();
        accel = new Vector2();
    }

    public DynamicGameObject(float x, float y, float radius) {
        super(x, y, radius);
        velocity = new Vector2();
        accel = new Vector2();
    }
}
