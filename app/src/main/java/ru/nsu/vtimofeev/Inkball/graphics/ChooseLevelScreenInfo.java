package ru.nsu.vtimofeev.Inkball.graphics;

import ru.nsu.vtimofeev.GameFramework.HelperClasses.MathClasses.OverlapTester;
import ru.nsu.vtimofeev.GameFramework.HelperClasses.MathClasses.Rectangle;
import ru.nsu.vtimofeev.GameFramework.HelperClasses.MathClasses.Vector2;
import ru.nsu.vtimofeev.GameFramework.InputHandlers.TouchEvent;
import ru.nsu.vtimofeev.GameFramework.MainClasses.GLGraphics;
import ru.nsu.vtimofeev.Inkball.chooseLevelModel.ChooseLevelModel;
import ru.nsu.vtimofeev.Inkball.chooseLevelModel.Level;

/**
 * Created by IntelliJ IDEA.
 * User: vtimofeev
 * Date: 6/19/12
 * Time: 8:16 PM
 * To change this template use File | Settings | File Templates.
 */
public class ChooseLevelScreenInfo extends AbstractScreenInfo{

    public final float modelWidth = ChooseLevelModel.width;
    public final float modelHeight = ChooseLevelModel.height;

    public final float markSize = Level.defHeight / 1.5f;

    public final float pointerWidth = translateWidthToFrustum(Level.defWidth *(2f/3f));
    public final float pointerHeight = translateWidthToFrustum(Level.defWidth *(2f/3f))* (180f/256f);

    public final float pointerY =
            translateHeightToFrustum(ChooseLevelModel.getLevelYById(6) - Level.defHeight*(7f/6f));
    public final float leftPointerX = translateWidthToFrustum(ChooseLevelModel.getLevelXById(6));
    public final float rightPointerX = translateWidthToFrustum(ChooseLevelModel.getLevelXById(8));

    public final Rectangle leftPointerArea = new Rectangle(leftPointerX -
            (pointerWidth/2f), (pointerY - ((pointerHeight/2f))), pointerWidth, pointerHeight);

    public final Rectangle rightPointerArea = new Rectangle(rightPointerX -
            (pointerWidth/2f), (pointerY - ((pointerHeight/2f))), pointerWidth, pointerHeight);

    public final float backButtonWidth = translateWidthToFrustum(Level.defWidth/3f);
    public final float backButtonHeight = 4*backButtonWidth;

    public final float backButtonX = translateWidthToFrustum(modelWidth)
            - backButtonWidth*(3f/4f);
    public final float backButtonY = translateHeightToFrustum(modelHeight/2f);

    public final Rectangle backButtonArea = new Rectangle(backButtonX - backButtonWidth/2f,
            backButtonY - backButtonHeight/2f, backButtonWidth, backButtonHeight);


    public ChooseLevelScreenInfo(GLGraphics graphics) {
        super(graphics);
    }

    public float toFrustrumPositionX(float worldX) {
        return worldX / modelWidth * frustumWidth;
    }

    public float toFrustrumPositionY(float worldY) {
        return worldY / modelHeight * frustumHeight;
    }

    @Override
    public float getModelWidth() {
        return modelWidth;
    }

    @Override
    public float getModelHeight() {
        return modelHeight;
    }

    public float translateWidthToFrustum(float width) {
        return width / modelWidth * frustumWidth;
    }

    public float translateHeightToFrustum(float height) {
        return height / modelHeight * frustumHeight;
    }

    public float getDeltaX(Vector2 lastTouch, Vector2 currentTouch) {
        return (currentTouch.x - lastTouch.x);
    }

    public boolean pointsInLeftPointer(TouchEvent event) {
        touchPoint.set(event.x, event.y);
        camera.touchToWorld(touchPoint);

        return OverlapTester.pointInRectangle(leftPointerArea, touchPoint.x, touchPoint.y);
    }

    public boolean pointsInRightPointer(TouchEvent event) {
        touchPoint.set(event.x, event.y);
        camera.touchToWorld(touchPoint);

        return OverlapTester.pointInRectangle(rightPointerArea, touchPoint.x, touchPoint.y);
    }

    public boolean pointsInBackButton(TouchEvent event) {

        touchPoint.set(event.x, event.y);
        camera.touchToWorld(touchPoint);

        return OverlapTester.pointInRectangle(backButtonArea, touchPoint.x, touchPoint.y);
    }
}
