package ru.nsu.vtimofeev.GameFramework.HelperClasses.ModelHelpers;

import ru.nsu.vtimofeev.GameFramework.HelperClasses.MathClasses.Circle;
import ru.nsu.vtimofeev.GameFramework.HelperClasses.MathClasses.Rectangle;
import ru.nsu.vtimofeev.GameFramework.HelperClasses.MathClasses.Shape;
import ru.nsu.vtimofeev.GameFramework.HelperClasses.MathClasses.Vector2;

public abstract class GameObject {
    public final Vector2 position;
    public final Shape bounds;

    public GameObject(float x, float y, float width, float height) {
        this.position = new Vector2(x, y);
        this.bounds = new Rectangle(x - width / 2, y - height / 2, width, height);
    }

    public GameObject(float x, float y, float radius) {
        this.position = new Vector2(x, y);
        this.bounds = new Circle(x, y, radius);
    }

    public abstract float getWidth();
    public abstract float getHeight();
}
