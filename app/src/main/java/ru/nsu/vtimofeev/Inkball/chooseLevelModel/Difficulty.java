package ru.nsu.vtimofeev.Inkball.chooseLevelModel;

import ru.nsu.vtimofeev.GameFramework.HelperClasses.MathClasses.Rectangle;
import ru.nsu.vtimofeev.GameFramework.HelperClasses.ModelHelpers.DynamicGameObject;

/**
 * Created by IntelliJ IDEA.
 * User: vtimofeev
 * Date: 6/22/12
 * Time: 6:10 PM
 * To change this template use File | Settings | File Templates.
 */
public class Difficulty extends DynamicGameObject{

    public static final float width = ChooseLevelModel.width / ChooseLevelModel.levelsInRow / 3;
    public static final float height = ChooseLevelModel.height;

    public static float defaultVelocity = ChooseLevelModel.height * 3f;

    public Difficulty(float x, float y) {
        super(x, y, width, height);
    }

    @Override
    public float getWidth() {
        return width;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public float getHeight() {
        return height;  //To change body of implemented methods use File | Settings | File Templates.
    }

    void updatePositionY(float deltaY){
        position.add(0f, deltaY);
        ((Rectangle) bounds).lowerLeft.add(0f, deltaY);
    }
    void startMovingLeft() {
        velocity.set(0f, -defaultVelocity);
    }

    void startMovingRight() {
        velocity.set(0f, defaultVelocity);
    }

    void stopMoving() {
        velocity.set(0f, 0f);
    }

    public void update(float deltaTime) {
        velocity.add(accel.x*deltaTime, accel.y*deltaTime);
        position.add(velocity.x * deltaTime, velocity.y * deltaTime);
        ((Rectangle) bounds).lowerLeft.add(velocity.x * deltaTime ,
                velocity.y * deltaTime);
    }
}
