package ru.nsu.vtimofeev.GameFramework.HelperClasses.MathClasses;

public class Circle extends Shape {
    public final Vector2 center = new Vector2();
    public float radius;

    public Circle(float x, float y, float radius) {
        checkRadiusValue(radius);
        this.center.set(x, y);
        this.radius = radius;
    }

    private void checkRadiusValue(float radius) {
        if (radius < 0.0f) {
            throw new IllegalArgumentException("Radius must be possitive");
        }
    }
}
