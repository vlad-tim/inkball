package ru.nsu.vtimofeev.Inkball.model;

import ru.nsu.vtimofeev.GameFramework.HelperClasses.ModelHelpers.GameObject;

/**
 * Created by IntelliJ IDEA.
 * User: vtimofeev
 * Date: 5/3/12
 * Time: 1:29 PM
 * To change this template use File | Settings | File Templates.
 */
public class TimerWall extends GameObject{

    public static final float defaultSize = Wall.defaultSize;
    public static final float defaultChangeTime = 3.0f;

    private float currentStateTime = 0.0f;
    private TimerWallState state = TimerWallState.OPAQUE;

    protected boolean canChangeState = true;

    public TimerWall(float x, float y) {
        super(x, y, defaultSize, defaultSize);
    }

    public TimerWall(float x, float y, TimerWallState state) {
        super(x, y, defaultSize, defaultSize);
        this.state = state;
    }

    public void update(float deltaTime) {
        currentStateTime += deltaTime;

        if (!canChangeState) return;

        if (currentStateTime > defaultChangeTime) {
            currentStateTime -= defaultChangeTime;
            nextState();
        }
    }

    private void nextState() {
        state = (state == TimerWallState.OPAQUE)
                ? TimerWallState.TRANSPARENT
                : (TimerWallState.OPAQUE);
    }

    public TimerWallState getState() {
        return state;
    }

    public void setCanChangeState(boolean canChangeState) {
        this.canChangeState = canChangeState;
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
