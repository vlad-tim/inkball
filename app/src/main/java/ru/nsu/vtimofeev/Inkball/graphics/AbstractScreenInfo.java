package ru.nsu.vtimofeev.Inkball.graphics;

import ru.nsu.vtimofeev.GameFramework.HelperClasses.GLHelpers.Camera2D;
import ru.nsu.vtimofeev.GameFramework.HelperClasses.MathClasses.Vector2;
import ru.nsu.vtimofeev.GameFramework.InputHandlers.TouchEvent;
import ru.nsu.vtimofeev.GameFramework.MainClasses.GLGraphics;
import ru.nsu.vtimofeev.Inkball.model.World;

/**
 * Created by IntelliJ IDEA.
 * User: vtimofeev
 * Date: 6/19/12
 * Time: 8:19 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class AbstractScreenInfo {

    protected Camera2D camera;
    protected GLGraphics graphics;

    public final float frustumWidth = 1024;
    public final float frustumHeight = 600;

    //Vector for internal needs(memory efficiency purposes)
    protected Vector2 touchPoint = new Vector2();

    public AbstractScreenInfo(GLGraphics graphics) {
        this.graphics = graphics;
        this.camera = new Camera2D(graphics, frustumWidth, frustumHeight);
    }

    public Camera2D getCamera() {
        return camera;
    }

    public GLGraphics getGraphics() {
        return graphics;
    }

    public abstract float translateWidthToFrustum(float width);
    public abstract float translateHeightToFrustum(float height);

    public abstract float toFrustrumPositionX(float worldX);
    public abstract float toFrustrumPositionY(float worldY);

    public abstract float getModelWidth();
    public abstract float getModelHeight();

    public Vector2 toModelPosition(TouchEvent event) {
        touchPoint.set(event.x, event.y);
        camera.touchToWorld(touchPoint);

        touchPoint.x = touchPoint.x / frustumWidth * getModelWidth();
        touchPoint.y = touchPoint.y / frustumHeight * getModelHeight();

        return touchPoint;
    }
}
