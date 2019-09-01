package ru.nsu.vtimofeev.Inkball.model;

import ru.nsu.vtimofeev.GameFramework.HelperClasses.MathClasses.Vector2;
import ru.nsu.vtimofeev.GameFramework.HelperClasses.ModelHelpers.GameObject;

/**
 * Created by IntelliJ IDEA.
 * User: vtimofeev
 * Date: 3/23/12
 * Time: 11:28 AM
 * To change this template use File | Settings | File Templates.
 */
public class Wall extends GameObject {

    public static final float defaultSize = 1.0f;

    private Color color = Color.NONE;

    private boolean cracked = false;

    public Wall(Vector2 c) {
        super(c.x, c.y, defaultSize, defaultSize);
    }

    public Wall(float x, float y) {
        super(x, y, defaultSize, defaultSize);
    }

    public Wall(float x, float y, boolean cracked) {
        super(x, y, defaultSize, defaultSize);
        this.cracked = cracked;
    }

    public Wall(Vector2 c, Color color) {
        super(c.x, c.y, defaultSize, defaultSize);
        this.color = color;
    }

    public Wall(float x, float y, Color color) {
        super(x, y, defaultSize, defaultSize);
        this.color = color;
    }
    public Wall(float x, float y, Color color, boolean cracked) {
        super(x, y, defaultSize, defaultSize);
        this.color = color;
        this.cracked = cracked;
    }

    public Color getColor() {
        return color;
    }

    public boolean isCracked() {
        return cracked;
    }

    @Override
    public float getWidth() {
        return defaultSize;
    }

    @Override
    public float getHeight() {
        return defaultSize;
    }
}
