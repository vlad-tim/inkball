package ru.nsu.vtimofeev.Inkball.graphics;

import ru.nsu.vtimofeev.GameFramework.HelperClasses.GLHelpers.SpriteBatcher;
import ru.nsu.vtimofeev.GameFramework.HelperClasses.GLHelpers.TextureRegion;
import ru.nsu.vtimofeev.Inkball.chooseLevelModel.ChooseLevelModel;
import ru.nsu.vtimofeev.Inkball.chooseLevelModel.Difficulty;
import ru.nsu.vtimofeev.Inkball.chooseLevelModel.Level;
import ru.nsu.vtimofeev.Inkball.resources.Assets;

import javax.microedition.khronos.opengles.GL10;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: vtimofeev
 * Date: 6/19/12
 * Time: 11:27 AM
 * To change this template use File | Settings | File Templates.
 */
public class ChooseLevelScreen extends AbstractScreen{

    private final int maxObjectsToRender = ChooseLevelModel.levelsInCategory*2 + 2;

    private ChooseLevelModel model;
    private ChooseLevelScreenInfo screenInfo;
    private SpriteBatcher batcher;

    public ChooseLevelScreen(ChooseLevelScreenInfo chooseLevelScreenInfo,
                             ChooseLevelModel model) {

        super(chooseLevelScreenInfo);
        this.model = model;
        this.screenInfo = chooseLevelScreenInfo;
        this.batcher = new SpriteBatcher(graphics, maxObjectsToRender);
    }

    @Override
    public void init() {
        camera.setViewportAndMatrices();
        GL10 gl = graphics.getGL();

        gl.glClearColor(183/255f, 226/255f, 247/255f, 1f);

        gl.glEnable(GL10.GL_TEXTURE_2D);
        gl.glEnable(GL10.GL_BLEND);
        gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
    }


    @Override
    public void present() {

        GL10 gl = graphics.getGL();
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

//        batcher.beginBatch(Assets.wallpaper);
//        renderBackground();
//        batcher.endBatch();

        renderLevels();
        renderMarks();
        renderPointers();

        batcher.beginBatch(Assets.diff);
        renderDiffs();
        batcher.endBatch();

        batcher.beginBatch(Assets.backButton);
        renderBackButton();
        batcher.endBatch();
    }

    private void renderPointers() {

        batcher.beginBatch(Assets.pointers);

        if (model.shouldDrawLeftPointer()) {
            batcher.drawSprite(screenInfo.leftPointerX, screenInfo.pointerY, screenInfo.pointerWidth,
                    screenInfo.pointerHeight, Assets.pointerLeft);
        }

        if (model.shouldDrawRightPointer()) {
            batcher.drawSprite(screenInfo.rightPointerX, screenInfo.pointerY, screenInfo.pointerWidth,
                    screenInfo.pointerHeight, Assets.pointerRight);
        }

        batcher.endBatch();
    }

//    private void renderBackground() {

    private void renderLevels() {

        List<Level> levels = model.getLevels();

        batcher.beginBatch(Assets.easyLevels);
        for (int i = 0; i < ChooseLevelModel.levelsInCategory; ++i) {
            Level level = levels.get(i);
            if (model.isLevelVisible(level)) {
                TextureRegion region = getLevelRegionById(i);
                renderLevel(level, region);
            }
        }
        batcher.endBatch();

        batcher.beginBatch(Assets.mediumLevels);
        for (int i = ChooseLevelModel.levelsInCategory;
             i < 2 * ChooseLevelModel.levelsInCategory;
             ++i) {
            Level level = levels.get(i);
            if (model.isLevelVisible(level)) {
                TextureRegion region = getLevelRegionById(i);
                renderLevel(level, region);
            }
        }
        batcher.endBatch();

        batcher.beginBatch(Assets.hardLevels);
        for (int i = 2*ChooseLevelModel.levelsInCategory;
             i < 3 * ChooseLevelModel.levelsInCategory;
             ++i) {
            Level level = levels.get(i);
            if (model.isLevelVisible(level)) {
                TextureRegion region = getLevelRegionById(i);
                renderLevel(level, region);
            }
        }
        batcher.endBatch();


        batcher.beginBatch(Assets.veryHardLevels);
        for (int i = 3*ChooseLevelModel.levelsInCategory;
             i < 4 * ChooseLevelModel.levelsInCategory;
             ++i) {
            Level level = levels.get(i);
            if (model.isLevelVisible(level)) {
                TextureRegion region = getLevelRegionById(i);
                renderLevel(level, region);
            }
        }
        batcher.endBatch();


        batcher.beginBatch(Assets.intenseLevels);
        for (int i = 4 *ChooseLevelModel.levelsInCategory;
             i < ChooseLevelModel.levelCategoriesNumber * ChooseLevelModel.levelsInCategory;
             ++i) {
            Level level = levels.get(i);
            if (model.isLevelVisible(level)) {
                TextureRegion region = getLevelRegionById(i);
                renderLevel(level, region);
            }
        }
        batcher.endBatch();
    }

    private TextureRegion getLevelRegionById(int i) {

        if (Assets.levels[i] != null) {
            return Assets.levels[i];
        }
        else {
            return Assets.noLevel;
        }
    }

    private void renderLevel(Level level, TextureRegion region) {

        float realX = screenInfo.toFrustrumPositionX(level.position.x);
        float realY = screenInfo.toFrustrumPositionY(level.position.y);

        float width = screenInfo.translateWidthToFrustum(level.getWidth());
        float height = screenInfo.translateHeightToFrustum(level.getHeight());

        batcher.drawSprite(realX, realY, width, height, region);

    }

    private void renderMarks() {
        List<Level> levels = model.getLevels();

        batcher.beginBatch(Assets.checkmarkLock);

        for (int i = 0; i < ChooseLevelModel.levelsInCategory * ChooseLevelModel.levelCategoriesNumber; ++i) {
            Level level = levels.get(i);
            if (model.isLevelVisible(level)) {

                int levelState = level.getState();

                if (levelState == Level.COMPLETED || levelState == Level.LOCKED) {

                    TextureRegion region =  levelState == Level.COMPLETED ? Assets.checkmark
                            : Assets.lock;

                    float realX = screenInfo.toFrustrumPositionX(level.position.x);
                    float realY = screenInfo.toFrustrumPositionY(level.position.y);

                    float width = screenInfo.translateWidthToFrustum(screenInfo.markSize);
                    float height = screenInfo.translateHeightToFrustum(screenInfo.markSize);

                    batcher.drawSprite(realX, realY, width, height, region);
                }
            }
        }

//        graphics.getGL().glColor4f(1f, 1f, 1f, 0.6f);
        batcher.endBatch();
//        graphics.getGL().glColor4f(1f, 1f, 1f, 1f);
    }

    private void renderDiffs() {

        List<Difficulty> diffs = model.getDiffs();

        for (int i = 0; i < diffs.size(); ++i) {
            Difficulty diff = diffs.get(i);
            if (model.isDiffVisible(diff)) {
                TextureRegion region = getDiffRegionById(i);
                renderDiff(diff, region);
            }
        }
    }

    private TextureRegion getDiffRegionById(int i) {
        if (i == 0) {
            return Assets.diffEasy;
        }
        else if (i == 1) {
            return Assets.diffNormal;
        }
        else if (i == 2) {
            return Assets.diffHard;
        }
        else if (i == 3) {
            return Assets.diffVeryHard;
        }
        else {
            return Assets.diffIntense;
        }
    }

    private void renderDiff(Difficulty diff, TextureRegion region) {

        float realX = screenInfo.toFrustrumPositionX(diff.position.x);
        float realY = screenInfo.toFrustrumPositionY(diff.position.y);

        float width = screenInfo.translateWidthToFrustum(diff.getWidth());
        float height = screenInfo.translateHeightToFrustum(diff.getHeight());

        batcher.drawSprite(realX, realY, width, height, region);
    }

    //    }
//        batcher.drawSprite(realX, realY, defWidth, defHeight, Assets.wallpaperRegion);
//
//        float defHeight = screenInfo.frustumHeight;
//        float defWidth = screenInfo.frustumWidth;
//
//        float realY = screenInfo.toFrustrumPositionY(ChooseLevelModel.defHeight / 2);
//        float realX = screenInfo.toFrustrumPositionX(ChooseLevelModel.defWidth / 2);
    private void renderBackButton() {

        float realX = screenInfo.backButtonX;
        float realY = screenInfo.backButtonY;

        float width = screenInfo.backButtonWidth;
        float height = screenInfo.backButtonHeight;

        batcher.drawSprite(realX, realY, width, height, Assets.backButtonReg);
    }

    @Override
    public void pause() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void resume() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void dispose() {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
