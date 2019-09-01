package ru.nsu.vtimofeev.Inkball.graphics;

import ru.nsu.vtimofeev.GameFramework.HelperClasses.GLHelpers.Camera2D;
import ru.nsu.vtimofeev.GameFramework.HelperClasses.GLHelpers.SpriteBatcher;
import ru.nsu.vtimofeev.GameFramework.HelperClasses.GLHelpers.TextureRegion;
import ru.nsu.vtimofeev.GameFramework.HelperClasses.MathClasses.Segment;
import ru.nsu.vtimofeev.GameFramework.HelperClasses.ModelHelpers.GameObject;
import ru.nsu.vtimofeev.GameFramework.MainClasses.GLGraphics;
import ru.nsu.vtimofeev.Inkball.model.*;
import ru.nsu.vtimofeev.Inkball.resources.Assets;
import ru.nsu.vtimofeev.Inkball.utils.FPSCounter;

import javax.microedition.khronos.opengles.GL10;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: vtimofeev
 * Date: 6/19/12
 * Time: 11:33 AM
 * To change this template use File | Settings | File Templates.
 */
public abstract class AbstractScreen {

    protected GLGraphics graphics;
    protected Camera2D camera;

    //Default init
    public  void init() {

        camera.setViewportAndMatrices();
        GL10 gl = graphics.getGL();

        gl.glEnable(GL10.GL_TEXTURE_2D);
        gl.glEnable(GL10.GL_BLEND);
        gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
    }

    public abstract void present();

    public abstract void pause();

    public abstract void resume();

    public abstract void dispose();


    protected AbstractScreen(AbstractScreenInfo abstractScreenInfo) {

        this.graphics = abstractScreenInfo.getGraphics();
        this.camera = abstractScreenInfo.getCamera();
    }
}