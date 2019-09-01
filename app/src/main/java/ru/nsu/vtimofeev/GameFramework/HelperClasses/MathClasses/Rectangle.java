package ru.nsu.vtimofeev.GameFramework.HelperClasses.MathClasses;

public class Rectangle extends Shape {
    public final Vector2 lowerLeft;
    public float width, height;

    public Rectangle(float x, float y, float width, float height) {
        checkWidthAndHeightValue(width, height);
        this.lowerLeft = new Vector2(x, y);
        this.width = width;
        this.height = height;
    }

    private void checkWidthAndHeightValue(float width, float height) {
        if ((width < 0.0f) || (height < 0.0f)) {
            throw new IllegalArgumentException("Width and defHeight must be possitive");
        }
    }

    public void set(Rectangle rectangle) {
        this.lowerLeft.x = rectangle.lowerLeft.x;
        this.lowerLeft.y = rectangle.lowerLeft.y;
        this.width = rectangle.width;
        this.height = rectangle.height;
    }
}
