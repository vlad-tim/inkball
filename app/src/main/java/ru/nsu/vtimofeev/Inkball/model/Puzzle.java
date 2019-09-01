package ru.nsu.vtimofeev.Inkball.model;

import ru.nsu.vtimofeev.GameFramework.HelperClasses.MathClasses.Rectangle;
import ru.nsu.vtimofeev.GameFramework.HelperClasses.ModelHelpers.GameObject;

/**
 * Created by IntelliJ IDEA.
 * User: vtimofeev
 * Date: 3/23/12
 * Time: 11:28 AM
 * To change this template use File | Settings | File Templates.
 */
public class Puzzle extends GameObject {

    public static final float defaultSize = Wall.defaultSize;

    private Color color = Color.NONE;
    private PuzzleState state;

    private boolean canChangeState = true;
    private boolean shouldChangeState = false;


    public Puzzle(float x, float y, Color color, PuzzleState state) {

        super(x, y, defaultSize, defaultSize);
        this.color = color;
        this.state = state;
    }

    public Color getColor() {
        return color;
    }

    @Override
    public float getWidth() {
        return defaultSize;
    }

    @Override
    public float getHeight() {
        return defaultSize;
    }

    public void setCanChangeState(boolean canChangeState) {
        this.canChangeState = canChangeState;
    }

    public void changeState() {
        if (canChangeState) {
            state = (state == PuzzleState.LOCKED) ? PuzzleState.UNLOCKED : PuzzleState.LOCKED;
        }
        else {
            shouldChangeState = (!shouldChangeState);
        }
    }
    public PuzzleState getState() {
        return state;
    }

    public void update() {
        if (shouldChangeState && canChangeState) {
            changeState();
            shouldChangeState = false;
        }
    }
}
