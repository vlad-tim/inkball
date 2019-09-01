package ru.nsu.vtimofeev.Inkball.activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import ru.nsu.vtimofeev.Inkball.chooseLevelModel.Level;
import ru.nsu.vtimofeev.Inkball.controller.AbstractController;
import ru.nsu.vtimofeev.Inkball.controller.GameController;
import ru.nsu.vtimofeev.Inkball.graphics.AbstractScreen;
import ru.nsu.vtimofeev.Inkball.graphics.AbstractScreenInfo;
import ru.nsu.vtimofeev.Inkball.graphics.GameScreen;
import ru.nsu.vtimofeev.Inkball.graphics.GameScreenInfo;
import ru.nsu.vtimofeev.Inkball.levels.LevelInitializer;
import ru.nsu.vtimofeev.Inkball.levels.LevelList;
import ru.nsu.vtimofeev.Inkball.resources.Assets;
import ru.nsu.vtimofeev.Inkball.resources.Rating;

/**
 * Created by IntelliJ IDEA.
 * User: vtimofeev
 * Date: 3/23/12
 * Time: 12:30 PM
 * To change this template use File | Settings | File Templates.
 */
public class GameActivity extends AbstractGLActivity implements GLSurfaceView.Renderer {

    //Only other activities able to change launchConfig
    static LevelInitializer levelInitializer = null;
    private int levelsInCategory = 9;


    private Handler handler = new Handler() {


        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {

                case HandlerCodes.EXIT: {
                    exit();
                    break;
                }

                case HandlerCodes.COMPLETE_LEVEL: {

                    completeCurrentLevel();
                    break;
                }

                case HandlerCodes.UPDATE_INITIALIZER: {
                    levelInitializer = (LevelInitializer) msg.obj;
                    break;
                }

                case HandlerCodes.SHOW_AD: {
                    showAd();
                    break;
                }

                case HandlerCodes.HIDE_AD: {
                    hideAd();
                    break;
                }
            }
        }
    };

    @Override
    public void onPause() {

        super.onPause();
        ((GameController)controller).createMenu();
    }

    private void completeCurrentLevel() {

        String levelID = LevelList.getID(levelInitializer);

        //Current level is completed
        SharedPreferences settings = getSharedPreferences(MainMenu.PREFS_COMPL_LEVELS,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt(levelID, Level.COMPLETED);


        //Next level is unlocked
        levelID = (LevelList.getID(LevelList.getNext(levelInitializer)));
        int state = settings.getInt(levelID, Level.LOCKED);

        if (state != Level.COMPLETED) {
            editor.putInt(levelID, Level.OPEN);
        }
        editor.commit();

        //Update rating if nessesary
        settings = getSharedPreferences(MainMenu.PREFS_RATING,
                Context.MODE_PRIVATE);
        editor = settings.edit();
        int levelIdInt =  LevelList.getIDInt(levelInitializer);
        if (((levelIdInt + 1) % levelsInCategory) == 0) {
            int currentRating = settings.getInt(Rating.rating, Rating.BEGINNER);
            int possibleNewRating = (levelIdInt + 1) / levelsInCategory;

            if (currentRating < possibleNewRating) {
                editor.putInt(Rating.rating, possibleNewRating);
                ((GameScreenInfo)screenInfo).setRating(possibleNewRating);
            }
        }
        editor.commit();
    }

    @Override
    protected void initAd() {
        super.initAd();
        //it's hidden initially
        hideAd();
    }

    @Override
    public void onBackPressed() {

        if (((GameController)controller).isWinOrLost()) {
            exit();
        }
        else {
            ((GameController)controller).switchMenu();
        }
    }

    private void exit() {
        super.onBackPressed();
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected AbstractScreenInfo createScreenInfo() {
        return new GameScreenInfo(glGraphics);
    }

    @Override
    protected AbstractController createController() {
        return new GameController((GameScreenInfo) screenInfo, levelInitializer, handler);
    }

    @Override
    protected void loadTextures() {
        Assets.loadGameTextures(glGraphics, fileIO);
    }

    @Override
    protected void reloadTextures() {
        Assets.reloadGameTextures();
    }

    @Override
    protected AbstractScreen createScreen() {
       return new GameScreen((GameScreenInfo) screenInfo, ((GameController)controller).getWorld());
    }
}
