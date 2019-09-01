package ru.nsu.vtimofeev.Inkball.activities;

import android.app.Activity;
import android.graphics.Color;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import com.google.ads.AdRequest;
import com.google.ads.AdView;
import ru.nsu.vtimofeev.GameFramework.InputHandlers.TouchEvent;
import ru.nsu.vtimofeev.GameFramework.MainClasses.FileIO;
import ru.nsu.vtimofeev.GameFramework.MainClasses.GLGraphics;
import ru.nsu.vtimofeev.GameFramework.MainClasses.Input;
import ru.nsu.vtimofeev.Inkball.controller.AbstractController;
import ru.nsu.vtimofeev.Inkball.graphics.AbstractScreen;
import ru.nsu.vtimofeev.Inkball.graphics.AbstractScreenInfo;
import ru.nsu.vtimofeev.Inkball.resources.Settings;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: vtimofeev
 * Date: 3/23/12
 * Time: 12:30 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class AbstractGLActivity extends Activity implements GLSurfaceView.Renderer {

    //General
    protected GLSurfaceView glView;
    protected GLGraphics glGraphics;
    protected AbstractScreen screen;
    protected FileIO fileIO;
    protected Input input;

    //Controller
    protected AbstractController controller;

    // ScreenInfo
    protected AbstractScreenInfo screenInfo;

    //Indication
    private boolean firstTimeCreated = true;
    private float currentTime;

    //Ad
    protected AdView adView;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        initRenderer();

        glGraphics = new GLGraphics(glView);
        fileIO = new FileIO(getAssets());
        input = new Input(glView, 1, 1);

        //Order of initialization matters
        screenInfo = createScreenInfo();
        controller = createController();

        Settings.loadSettings(this);
    }

    protected void initRenderer() {

        //FullScreen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.gl_layout);

        glView = (GLSurfaceView) findViewById(R.id.glSurface);
        glView.setEGLConfigChooser(8, 8, 8, 8, 0, 0);
        glView.setRenderer(this);

        initAd();

        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
    }

    protected void initAd() {

        adView = (AdView)findViewById(R.id.ad);
        refreshAd();
    }

    protected final void hideAd() {
        adView.setVisibility(AdView.GONE);
    }

    protected final void showAd() {
        adView.setVisibility(AdView.VISIBLE);
    }

    protected final void refreshAd() {
        AdRequest adRequest = new AdRequest();
        adView.loadAd(adRequest);
    }


    @Override
    public void onDestroy() {

        super.onDestroy();
        adView.destroy();
    }


    @Override
    public void onResume() {

        super.onResume();
        glView.onResume();
        refreshAd();
    }

    @Override
    public void onPause() {

        super.onPause();
        glView.onPause();
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {

        glGraphics.setGL(gl);

        screen = createScreen();
        currentTime = System.nanoTime();

        if (firstTimeCreated) {
            loadTextures();
            firstTimeCreated = false;
        } else {
            reloadTextures();
        }
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        screen.init();
    }
    @Override
    public void onDrawFrame(GL10 gl) {

        float deltaTime = (System.nanoTime() - currentTime) / 1000000000.0f;
        currentTime = System.nanoTime();

        List<TouchEvent> events = input.getTouchEvents();
        controller.update(deltaTime, events);
        screen.present();

    }

    //Abstract methods
    abstract protected AbstractController createController();

    abstract protected AbstractScreen createScreen();

    abstract protected AbstractScreenInfo createScreenInfo();

    abstract protected void loadTextures();

    abstract protected void reloadTextures();
}
