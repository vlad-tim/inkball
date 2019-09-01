package ru.nsu.vtimofeev.Inkball.graphics;

import ru.nsu.vtimofeev.GameFramework.HelperClasses.MathClasses.OverlapTester;
import ru.nsu.vtimofeev.GameFramework.HelperClasses.MathClasses.Rectangle;
import ru.nsu.vtimofeev.GameFramework.InputHandlers.TouchEvent;
import ru.nsu.vtimofeev.GameFramework.MainClasses.GLGraphics;
import ru.nsu.vtimofeev.Inkball.model.*;

/**
 * Created by IntelliJ IDEA.
 * User: vtimofeev
 * Date: 3/27/12
 * Time: 3:44 PM
 * To change this template use File | Settings | File Templates.
 */
public class GameScreenInfo extends AbstractScreenInfo{


    public final float modelWidth = World.WIDTH;
    public final float modelHeight = World.HEIGHT;

    public final float fpsX = 70;
    public final float fpsY = frustumHeight - 43;

    public final float digitWidth = 21;
    public final float digitHeight = 30;

    public final float timeX = 70;
    public final float timeY = 43;

    //Menu
    private boolean isMenuVisible = false;

    public final float menuX = frustumWidth/2f;
    public final float menuY = frustumHeight/2f;

    public final float menuWidth = frustumWidth * 0.8f * (frustumHeight/frustumWidth);
    public final float menuHeight = frustumHeight * 0.8f;

    public final Rectangle resumeArea = new Rectangle(menuX - (menuWidth/2f),
            (menuY + ((49f/512f) * menuHeight)), menuWidth, (61f/512f) * menuHeight);

    public final Rectangle restartArea = new Rectangle(menuX - (menuWidth/2f),
            (menuY - (12f/512f * menuHeight)), menuWidth, ((62f/512f) * menuHeight));

    public final Rectangle accelerateArea = new Rectangle(menuX - (menuWidth/2f),
            (menuY - ((86f/512f) * menuHeight)), menuWidth, ((65f/512f) * menuHeight));

    public final Rectangle clearArea = new Rectangle(menuX - (menuWidth/2f),
            (menuY - ((145f/512f) * menuHeight)), menuWidth, (57f/512f) * menuHeight);

    public final Rectangle exitArea = new Rectangle(menuX - (menuWidth/2f),
            (menuY - ((218f/512f) * menuHeight)), menuWidth, (72f/512f) * menuHeight);


    public final float menuIconX = frustumWidth - 1.5f * frustumWidth/World.WALLS_IN_ROW;
    public final float menuIconY = (frustumHeight*(2*World.WALLS_IN_COLUMN - 1))/(2 * World
            .WALLS_IN_COLUMN);

    public final float menuIconWidth = frustumWidth * (3f/World.WALLS_IN_ROW);
    public final float menuIconHeight = frustumHeight * (1f/World.WALLS_IN_COLUMN);

    public final Rectangle menuIconArea = new Rectangle(menuIconX - (menuIconWidth/2f),
            (menuIconY - ((menuIconHeight/2f))), menuIconWidth, menuIconHeight);

    public final float levelFailedWidth = frustumWidth/2.0f;
    public final float levelFailedHeigth = levelFailedWidth * 64f / 423f;
    public final float levelFailedX = frustumWidth/2f;
    public final float levelFailedY = frustumHeight * 0.75f;


    public final float levelCompleteHeigth = levelFailedHeigth;
    public final float levelCompleteWidth = levelCompleteHeigth * (512f/67f);
    public final float levelCompleteX = frustumWidth/2f;
    public final float levelCompleteY = levelFailedY;


    public final float wrongColorWidth = levelFailedWidth * (487f/417f);
    public final float wrongColorHeigth = wrongColorWidth * (47f/487f);
    public final float wrongColorX = frustumWidth/2f;
    public final float wrongColorY = levelFailedY - 1.1f*levelFailedHeigth;


    public final float timeIsUpHeigth = wrongColorHeigth;
    public final float timeIsUpWidth = timeIsUpHeigth * (195f / 35f);
    public final float timeIsUpX = frustumWidth/2f;
    public final float timeIsUpY = levelFailedY - 1.1f*levelFailedHeigth;

    public final float winLostNextLevelWidth = levelFailedWidth * (283f/417f);
    public final float winLostNextLevelHeigth = levelFailedHeigth * 58f / 70f;
    public final float winLostNextLevelX = frustumWidth/2f;
    public final float winLostNextLevelY = levelFailedY - 2.5f*levelFailedHeigth;
    
    public final float levelUpWidth = frustumWidth/3f;
    public final float levelUpHeight = levelUpWidth * (80f/285f);
    public final float levelUpX = frustumWidth/2f;
    public final float levelUpY = (3f/2f) * levelUpHeight;

    public final float wellDoneX = frustumWidth/2f;
    public final float wellDoneY = frustumHeight/2f;
    public final float wellDoneWidth = frustumWidth/2.4f;
    public final float wellDoneHeight = wellDoneWidth/440f*73f;

    private boolean shouldDrawNextLevelButton = true;

    private HintStatus hintStatus = HintStatus.NONE_SHOWN;

    private float YForHint = frustumHeight * (1.5f/ World.WALLS_IN_COLUMN);

    public GameMenuItem getVisibleItem() {
        return visibleItem;
    }

    public boolean isMenuItemVisible() {
        return isMenuItemVisible;
    }

    public final Rectangle winLostNextLevelArea = new Rectangle(winLostNextLevelX - (winLostNextLevelWidth/2f),
            (winLostNextLevelY - ((winLostNextLevelHeigth/2f))), winLostNextLevelWidth,
            winLostNextLevelHeigth);

    public final float winLostRestartWidth = levelFailedWidth * (220f/427f);

    public final float winLostRestartHeigth = levelFailedHeigth * 56f / 70f;

    public final float winLostRestartX = frustumWidth/2f;
    public final float winLostRestartY = levelFailedY - 2.5f*levelFailedHeigth;
    public final Rectangle winLostRestartArea = new Rectangle(winLostRestartX - (winLostRestartWidth/2f),
            (winLostRestartY - ((winLostRestartHeigth/2f))), winLostRestartWidth, winLostRestartHeigth);
    private GameMenuItem visibleItem = null;
    private boolean isMenuItemVisible = false;

    private boolean isRatingVisible = false;
    private int rating = 0;

    public boolean isRatingVisible() {
        return isRatingVisible;
    }
    public int getRating() {
        return rating;
    }

    public GameScreenInfo(GLGraphics graphics) {
        super(graphics);
    }

    private final float ballWidthFrustum = Ball.radius * 2 / World.WIDTH * frustumWidth;

    public float translateBallWidthFrustum() {
        return ballWidthFrustum;
    }

    private final float ballHeightFrustum = Ball.radius * 2 / World.HEIGHT * frustumHeight;
    public float translateBallHeightFrustum() {
        return ballHeightFrustum;
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

    public final float multilineWidthFrustum = MultiLine.defaultRadius / World.WIDTH * frustumWidth;

    public float translateMultilineWidthFrustum() {
        return multilineWidthFrustum;
    }

    public final float multilineHeightFrustum = MultiLine.defaultRadius / World.HEIGHT * frustumHeight;
    public float translateMultilineHeightFrustum() {
        return multilineHeightFrustum;
    }

    public float translateWidthToFrustum(float width) {
        return width / World.WIDTH * frustumWidth;
    }
    public float translateHeightToFrustum(float height) {
        return height / World.HEIGHT * frustumHeight;
    }

    public void setMenuVisible(boolean value) {
        isMenuVisible = value;
    }

    public boolean isMenuVisible() {
        return isMenuVisible;
    }

    public GameMenuItem getGameMenuItem(TouchEvent event) {

        touchPoint.set(event.x, event.y);
        camera.touchToWorld(touchPoint);

        if (OverlapTester.pointInRectangle(restartArea, touchPoint.x, touchPoint.y)) {
            return GameMenuItem.RESTART;
        }
        else if (OverlapTester.pointInRectangle(accelerateArea, touchPoint.x, touchPoint.y)) {
            return GameMenuItem.ACCELERATE;
        }
        else if (OverlapTester.pointInRectangle(clearArea, touchPoint.x, touchPoint.y)) {
            return GameMenuItem.CLEAR;
        }
        else if (OverlapTester.pointInRectangle(resumeArea, touchPoint.x, touchPoint.y)) {
            return GameMenuItem.RESUME;
        }
        else if (OverlapTester.pointInRectangle(exitArea, touchPoint.x, touchPoint.y)) {
            return GameMenuItem.EXIT;
        }

        return null;
    }

    public boolean pointsInMenuIcon(TouchEvent event) {
        touchPoint.set(event.x, event.y);
        camera.touchToWorld(touchPoint);

        return OverlapTester.pointInRectangle(menuIconArea, touchPoint.x, touchPoint.y);
    }

    public boolean pointsInRestart(TouchEvent event) {
        touchPoint.set(event.x, event.y);
        camera.touchToWorld(touchPoint);

        return OverlapTester.pointInRectangle(winLostRestartArea, touchPoint.x, touchPoint.y);
    }

    public boolean pointsInNextLevel(TouchEvent event) {
        touchPoint.set(event.x, event.y);
        camera.touchToWorld(touchPoint);

        return OverlapTester.pointInRectangle(winLostNextLevelArea, touchPoint.x, touchPoint.y);
    }

    public void disableVisibleMenuItem() {
        isMenuItemVisible = false;
    }

    public void enableVisibleMenuItem(GameMenuItem item) {
        visibleItem = item;
        isMenuItemVisible = true;
    }

    public void enableVisibleMenuItem() {
        isMenuItemVisible = true;
    }

    public void reset() {
        isRatingVisible = false;
    }

    public void setRating(int rating) {
        this.rating = rating;
        isRatingVisible = true;
    }

    public HintStatus getHintStatus() {
        return hintStatus;
    }

    public void setHintStatus(HintStatus hintStatus) {
        this.hintStatus = hintStatus;
    }

    public float getYForHint() {
        return YForHint;
    }

    public boolean isShouldDrawNextLevelButton() {
        return shouldDrawNextLevelButton;
    }

    public void setShouldDrawNextLevelButton(boolean shouldDrawNextLevelButton) {
        this.shouldDrawNextLevelButton = shouldDrawNextLevelButton;
    }
}
