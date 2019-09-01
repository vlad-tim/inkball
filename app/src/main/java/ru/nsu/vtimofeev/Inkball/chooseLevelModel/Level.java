package ru.nsu.vtimofeev.Inkball.chooseLevelModel;

import ru.nsu.vtimofeev.GameFramework.HelperClasses.MathClasses.Rectangle;
import ru.nsu.vtimofeev.GameFramework.HelperClasses.ModelHelpers.DynamicGameObject;
import ru.nsu.vtimofeev.Inkball.levels.LevelInitializer;

/**
 * Created by IntelliJ IDEA.
 * User: vtimofeev
 * Date: 6/19/12
 * Time: 9:01 PM
 * To change this template use File | Settings | File Templates.
 */
public class Level extends DynamicGameObject{
    
    public static final float defWidth = ChooseLevelModel.width / ChooseLevelModel.levelsInRow / 2;
    public static final float defHeight = ChooseLevelModel.height / ChooseLevelModel.levelsInColumn / 2;

    public float width = defWidth;
    public float height = defHeight;

    public static final int OPEN = 0;
    public static final int COMPLETED = 1;
    public static final int LOCKED = 2;
    
    private int state = Level.LOCKED;

    private float maxSizeKoef = 1.25f;
    private float growingSpeed = 2.5f; //two times per second

    private GrowingState growingState = GrowingState.STILL;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    //means that we need less than one third of a second to move to stable state
    public static float defaultVelocity = ChooseLevelModel.width * 3f;

    private LevelInitializer initializer;


    public Level(float x, float y, LevelInitializer initializer) {
        super(x, y, defWidth, defHeight);
        this.initializer  = initializer;
    }

    @Override
    public float getWidth() {
        return width;
    }

    @Override
    public float getHeight() {
        return height;
    }

    void updatePositionX(float deltaX){
        position.add(deltaX, 0f);
        ((Rectangle) bounds).lowerLeft.add(deltaX ,
                0f);
    }

    void startMovingLeft() {
        velocity.set(-defaultVelocity, 0f);
    }
    void startMovingRight() {
        velocity.set(defaultVelocity, 0f);
    }

    void stopMoving() {
        velocity.set(0f, 0f);
    }

    boolean isMovingRight() {
        return velocity.x > 0f;
    }

    boolean isMovingLeft() {
        return velocity.x < 0f;
    }

    public void update(float deltaTime) {
        velocity.add(accel.x*deltaTime, accel.y*deltaTime);
        position.add(velocity.x * deltaTime, velocity.y * deltaTime);
        ((Rectangle) bounds).lowerLeft.add(velocity.x * deltaTime ,
                velocity.y * deltaTime);

        if (growingState == GrowingState.GROWING || growingState == GrowingState.REDUCING) {

            grow(deltaTime);
        }
    }

    private void grow(float deltaTime) {
        float koef = (growingState == GrowingState.GROWING) ?
                (1 + deltaTime * growingSpeed) : (1 - deltaTime * growingSpeed);

        width *= koef;
        height *= koef;

        if (growingState == GrowingState.GROWING && width/defWidth > maxSizeKoef) {
            width = defWidth * maxSizeKoef;
            height = defHeight * maxSizeKoef;
            growingState = GrowingState.STILL;
        }

        //Reduce
        else if (width/defWidth < 1f) {
            width = defWidth;
            height = defHeight;
            growingState = GrowingState.STILL;
        }
    }

    public LevelInitializer extractInitializer() {
        return initializer;
    }

    public void startGrowing() {

        growingState = GrowingState.GROWING;
    }

    public void startReducing() {

        growingState = GrowingState.REDUCING;
    }

}
