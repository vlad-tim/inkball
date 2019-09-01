package ru.nsu.vtimofeev.Inkball.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;
import com.google.ads.AdRequest;
import com.google.ads.AdView;
import ru.nsu.vtimofeev.Inkball.chooseLevelModel.Level;
import ru.nsu.vtimofeev.Inkball.controller.AbstractController;
import ru.nsu.vtimofeev.Inkball.controller.ChooseLevelController;
import ru.nsu.vtimofeev.Inkball.controller.GameController;
import ru.nsu.vtimofeev.Inkball.graphics.AbstractScreen;
import ru.nsu.vtimofeev.Inkball.graphics.AbstractScreenInfo;
import ru.nsu.vtimofeev.Inkball.graphics.ChooseLevelScreen;
import ru.nsu.vtimofeev.Inkball.graphics.ChooseLevelScreenInfo;
import ru.nsu.vtimofeev.Inkball.levels.LevelInitializer;
import ru.nsu.vtimofeev.Inkball.resources.Assets;
import ru.nsu.vtimofeev.Inkball.resources.Settings;

public class ChooseLevelActivity extends AbstractGLActivity{


    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case HandlerCodes.START_LEVEL: {

                    Level chosenLevel = (Level) msg.obj;
                    LevelInitializer initializer = chosenLevel.extractInitializer();

                    //It's important to set GameActivity.levelInitializer

                    if (chosenLevel.getState() == Level.LOCKED) {

//                        Toast.makeText(getApplicationContext(), "Level is locked.",
//                                Toast.LENGTH_SHORT).show();
                            return;
                    }

                    GameActivity.levelInitializer = initializer;
                    Intent game = new Intent(ChooseLevelActivity.this, GameActivity.class);
                    startActivity(game);
                    break;
                }

                case HandlerCodes.EXIT: {

                    onBackPressed();
                    break;
                }
            }
        }
    };




    @Override
    public void onBackPressed() {

        super.onBackPressed();
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
    }

    @Override
    public void onResume() {

        super.onResume();
        updateLevelsState();
    }

    private void updateLevelsState() {

        SharedPreferences settings = getSharedPreferences(MainMenu.PREFS_COMPL_LEVELS,
                Context.MODE_PRIVATE);

        ((ChooseLevelController)controller).updateLevelsState(settings.getAll());
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected AbstractScreenInfo createScreenInfo() {
        return new ChooseLevelScreenInfo(glGraphics);
    }

    @Override
    protected AbstractController createController() {
        return new ChooseLevelController((ChooseLevelScreenInfo) screenInfo, handler);
    }

    @Override
    protected void loadTextures() {
        Assets.loadLevelTextures(glGraphics, fileIO);
    }

    @Override
    protected void reloadTextures() {
        Assets.reloadLevelTextures();
    }

    @Override
    protected AbstractScreen createScreen() {
        return new ChooseLevelScreen((ChooseLevelScreenInfo) screenInfo,
                ((ChooseLevelController) controller).getModel());
    }
}

