package ru.nsu.vtimofeev.Inkball.chooseLevelModel;

import ru.nsu.vtimofeev.GameFramework.HelperClasses.MathClasses.OverlapTester;
import ru.nsu.vtimofeev.GameFramework.HelperClasses.MathClasses.Rectangle;
import ru.nsu.vtimofeev.GameFramework.HelperClasses.MathClasses.Vector2;
import ru.nsu.vtimofeev.Inkball.levels.LevelInitializer;
import ru.nsu.vtimofeev.Inkball.levels.LevelList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: vtimofeev
 * Date: 6/19/12
 * Time: 8:34 PM
 * To change this template use File | Settings | File Templates.
 */
public class ChooseLevelModel {

    public static final float width = 1024;
    public static final float height = 600;

    public static final int levelCategoriesNumber = 5;

    public static final int levelsInRow = 3;
    public static final int levelsInColumn = 3;
    
    public static final int levelsInCategory = levelsInRow * levelsInColumn;
    public static final int totalLevels = levelCategoriesNumber * levelsInCategory;


    private List<Level> levels = new ArrayList<Level>(totalLevels);

    private List<Difficulty> diffs = new ArrayList<Difficulty>(levelCategoriesNumber);

    private final Level middleLevel;

    private final float initialMiddleLevelX;
    //Position of middle easyLevels is global position
    private final Vector2 globalPosition;
    private boolean isStabilizing = false;

    private boolean stable = true;
    private float movedDistance = 0f;


    public boolean isStable() {
        return stable;
    }

    public ChooseLevelModel() {

        initLevels();

        middleLevel = levels.get(levelsInRow/2);
        initialMiddleLevelX = middleLevel.position.x;
        globalPosition = middleLevel.position;

        initCategories();
    }

    private void initCategories() {
        for (int i = 0; i < levelCategoriesNumber; ++i) {

            float x = getDiffXById(i);
            float y = getDiffYById(i);

            diffs.add(new Difficulty(x, y));
        }
    }

    private void initLevels() {
        for (int i = 0; i < totalLevels; ++i) {

            float x = getLevelXById(i);
            float y = getLevelYById(i);

            LevelInitializer initializer = getLevelInitializerById(i);

            levels.add(new Level(x, y, initializer));
        }
    }

    private float getDiffYById(int i) {
        return Difficulty.height * (0.5f + i);
    }

    private float getDiffXById(int i) {
        return Difficulty.width/2 + Level.defHeight /4f;
    }

    private LevelInitializer getLevelInitializerById(int i) {
        return LevelList.LEVELS[i];
    }

    static public float getLevelXById(int id) {
        return width*(id/levelsInCategory) +
                Level.defWidth *(13f/8f + (25f / 16f) *(id % levelsInRow));
    }

    static public float getLevelYById(int id) {
        return height - Level.defHeight *(7f/4f - (1f/6f) + (11f / 8f)*((id % levelsInCategory) /
                levelsInRow));
    }

    public void moveX(float deltaX) {

        //Some limits for moving
//        if (globalPosition.x + deltaX > initialMiddleLevelX ||
//                globalPosition.x + deltaX < initialMiddleLevelX - defWidth*(levelCategoriesNumber -
//                        1)) {
//            return;
//        }

        for (int i = 0; i < levels.size(); ++i) {
            levels.get(i).updatePositionX(deltaX);
        }

        float deltaY = deltaX * height / width;

        for (int i = 0; i < diffs.size(); ++i) {
            diffs.get(i).updatePositionY(deltaY);
        }

        stable = false;
    }

    public void moveLeft() {

        //Some limits for moving
        if (!stable || globalPosition.x - Level.defWidth < initialMiddleLevelX - width*
                (levelCategoriesNumber - 1)) {
            return;
        }

        for (int i = 0; i < levels.size(); ++i) {
            levels.get(i).startMovingLeft();
        }

        for (int i = 0; i < diffs.size(); ++i) {
            diffs.get(i).startMovingLeft();
        }

        stable = false;
    }

    public void moveRight() {

        //Some limits for moving
        if (!stable || globalPosition.x > initialMiddleLevelX - Level.defWidth) {
            return;
        }

        for (int i = 0; i < levels.size(); ++i) {
            levels.get(i).startMovingRight();
        }

        for (int i = 0; i < diffs.size(); ++i) {
            diffs.get(i).startMovingRight();
        }

        stable = false;
    }

//    public void startStabilizing() {
//        isStabilizing = true;
//        correctDirection();
//    }

//    private boolean correctDirection() {
//
//        boolean moveRight;
//        moveRight = isDirectionRight();
//
//        if (moveRight) {
//            for (int i = 0; i < levels.size(); ++i) {
//                (levels.get(i)).startMovingRight();
//            }
//            for (int i = 0; i < diffs.size(); ++i) {
//                diffs.get(i).startMovingRight();
//            }
//        }
//        else {
//            for (int i = 0; i < levels.size(); ++i) {
//                (levels.get(i)).startMovingLeft();
//            }
//            for (int i = 0; i < diffs.size(); ++i) {
//                diffs.get(i).startMovingLeft();
//            }
//        }
//
//        return moveRight;
//    }

//    public void stopStabilizing() {
//
//        isStabilizing = false;
//
//        for (int i = 0; i < levels.size(); ++i) {
//            levels.get(i).stopMoving();
//        }
//        for (int i = 0; i < diffs.size(); ++i) {
//            diffs.get(i).stopMoving();
//        }
//    }

    public void update(float deltaTime) {



        for (int i = 0; i < levels.size(); ++i) {
            levels.get(i).update(deltaTime);
        }
        for (int i = 0; i < diffs.size(); ++i) {
            diffs.get(i).update(deltaTime);
        }

        if (stable) {
            return;
        }


        Level level = levels.get(0);
        boolean levelMovesRight = level.isMovingRight();

        movedDistance += Level.defaultVelocity * deltaTime;

        if (movedDistance > width) {

            correctLevelPositions(levelMovesRight);
            movedDistance = 0f;
            stable = true;
        }
    }

    private void correctLevelPositions(boolean levelMovedRight) {

        float deltaX;

        if (!levelMovedRight) {
            deltaX = (initialMiddleLevelX + levelCategoriesNumber*width -
                    middleLevel.position.x) % width;
        }
        else {
            deltaX = -((middleLevel.position.x  + levelCategoriesNumber*width -
                    initialMiddleLevelX) % width);
        }

        moveX(deltaX);

        stopLevels();
        stable = true;
    }

    private void stopLevels() {
        for (int i = 0; i < levels.size(); ++i) {
            levels.get(i).stopMoving();
        }

        for (int i = 0; i < diffs.size(); ++i) {
            diffs.get(i).stopMoving();
        }
    }

//    private boolean isDirectionRight() {
//
//        if (globalPosition.x > initialMiddleLevelX) {
//            return false;
//        }
//
//        else if (globalPosition.x < initialMiddleLevelX - defWidth*(levelCategoriesNumber - 1)
//        ) {
//            return true;
//        }
//
//        else {
//            return (((globalPosition.x + defWidth*levelCategoriesNumber) % defWidth)
//                    < initialMiddleLevelX);
//        }
//    }

    public List<Level> getLevels() {
        return levels;
    }


    public boolean isLevelVisible(Level level) {
        return ((level.position.x > -Level.defWidth /2) &&
                (level.position.x < width + Level.defWidth /2));
    }

    //This method can return null
    public Level getPointedLevel(Vector2 pos) {

        Level chosenLevel = null;

        for (int i = 0; i < levels.size(); ++i) {
            Level level = levels.get(i);
            if (OverlapTester.pointInRectangle((Rectangle)level.bounds, pos)) {
                chosenLevel = level;
                break;
            }
        }

        return chosenLevel;
    }

    public List<Difficulty> getDiffs() {
        return diffs;
    }

    public boolean isDiffVisible(Difficulty diff) {
        return ((diff.position.y > -Difficulty.height/2) &&
                (diff.position.y < height + Difficulty.height/2));
    }

    public boolean shouldDrawLeftPointer() {

        Level level = levels.get(ChooseLevelModel.levelsInCategory);

        if (level.position.x < width/2f) {
            return true;
        }

        return false;
    }

    public boolean shouldDrawRightPointer() {

        Level level = levels.get(ChooseLevelModel.levelsInCategory *
        (ChooseLevelModel.levelCategoriesNumber - 1));

        if (level.position.x > width/2f) {
            return true;
        }

        return false;
    }
}
