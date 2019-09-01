package ru.nsu.vtimofeev.Inkball.controller;

import android.os.Handler;
import android.util.Log;
import ru.nsu.vtimofeev.GameFramework.HelperClasses.MathClasses.Vector2;
import ru.nsu.vtimofeev.GameFramework.InputHandlers.TouchEvent;
import ru.nsu.vtimofeev.Inkball.activities.HandlerCodes;
import ru.nsu.vtimofeev.Inkball.graphics.GameMenuItem;
import ru.nsu.vtimofeev.Inkball.graphics.GameScreenInfo;
import ru.nsu.vtimofeev.Inkball.graphics.HintStatus;
import ru.nsu.vtimofeev.Inkball.levels.LevelInitializer;
import ru.nsu.vtimofeev.Inkball.levels.LevelList;
import ru.nsu.vtimofeev.Inkball.model.World;
import ru.nsu.vtimofeev.Inkball.pool.SegmentPool;
import ru.nsu.vtimofeev.Inkball.pool.Vector2Pool;
import ru.nsu.vtimofeev.Inkball.resources.Assets;
import ru.nsu.vtimofeev.Inkball.resources.Settings;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: vtimofeev
 * Date: 3/27/12
 * Time: 1:12 PM
 * To change this template use File | Settings | File Templates.
 */
public class GameController extends AbstractController{

    //Approximate evaluation - 60 Frames Per Second
    private static final float UPDATE_INTERVAL = 0.017f;
    
    //We do not show ad at very first levels
    private static final int FREE_AD_LEVEL_COUNT = 9;

    private World world;
    private GameScreenInfo gameScreenInfo;
    private Handler handler;
    private LevelInitializer initializer;
    private boolean enteringMenu = false;

    private final float menuItemDeltaTimeLimit = 0.5f;
    private float currentDeltaTime = 0f;
    private boolean additionalMenuItemVisible = false;

    private float elapsedTime = 0f;
    private final float hintChangeInterval = 3f;
    private final float hintDelay = 0.8f;

    private final boolean hintsEnabled;


    public GameController(GameScreenInfo gameScreenInfo, LevelInitializer levelInitializer,
                          Handler handler) {
        this.gameScreenInfo = gameScreenInfo;
        this.handler = handler;
        this.initializer = levelInitializer;
        world = new World(levelInitializer, handler);

        hintsEnabled = Settings.isHintsEnabled();
        updateHints(levelInitializer);

        SegmentPool.init();
        Vector2Pool.init();

//        System.gc();
    }

    public void update(float deltaTime, List<TouchEvent> events) {



        if (false == gamePaused()) {
            
            if (world.isLost()) {
                updateLost(events);                
            }
            
            else if (world.isWin()) {
                updateWin(events);
            }
            
            else {
                addElapsedTimeForHint(deltaTime);
                updateInGame(deltaTime, events);
            }
        }

        else {
            updateInMenu(events);
        }
    }

    private void addElapsedTimeForHint(float deltaTime) {

        if (!hintsEnabled) {
            return;
        }

        elapsedTime += deltaTime;

        if (elapsedTime < hintDelay ||
            elapsedTime > hintDelay + hintChangeInterval &&
                    elapsedTime < 2*hintDelay + hintChangeInterval ||
            elapsedTime > 2*(hintDelay + hintChangeInterval)) {

            gameScreenInfo.setHintStatus(HintStatus.NONE_SHOWN);
        }

        else if (elapsedTime > 2*hintDelay + hintChangeInterval) {
            gameScreenInfo.setHintStatus(HintStatus.SECOND_HINT_SHOWN);
        }
        else {
            gameScreenInfo.setHintStatus(HintStatus.FIRST_HINT_SHOWN);
        }
    }

    private void updateLost(List<TouchEvent> events) {


        ensureAdVisibility();

        for (int i = 0; i < events.size(); ++i) {

            TouchEvent event = events.get(i);

            if (event.type == TouchEvent.TOUCH_UP) {
                
                if (gameScreenInfo.pointsInRestart(event)) {
                    hideAd();
                    recreateWorld();
//                    System.gc();
                    return;
                }
            }

        }
    }

    private void updateWin(List<TouchEvent> events) {

        ensureAdVisibility();

        if (LevelList.isLevelLast(initializer)) {
            gameScreenInfo.setShouldDrawNextLevelButton(false);
            return;
        }
        else {
            gameScreenInfo.setShouldDrawNextLevelButton(true);
        }


        for (int i = 0; i < events.size(); ++i) {

            TouchEvent event = events.get(i);

            if (event.type == TouchEvent.TOUCH_UP) {

                if (gameScreenInfo.pointsInNextLevel(event)) {
                    initializer = LevelList.getNext(initializer);
                    handler.obtainMessage(HandlerCodes.UPDATE_INITIALIZER, initializer).
                            sendToTarget();
                    hideAd();
                    recreateWorld();
                    elapsedTime = 0f;
//                    System.gc();
                }
            }
        }

    }

    private void hideAd() {
        handler.obtainMessage(HandlerCodes.HIDE_AD, initializer).
                sendToTarget();
    }

    private void ensureAdVisibility() {

        if (LevelList.getIDInt(initializer) >=  FREE_AD_LEVEL_COUNT) {
            handler.obtainMessage(HandlerCodes.SHOW_AD, initializer).
                    sendToTarget();
        }
    }

    private void updateInGame(float deltaTime, List<TouchEvent> events) {

        processMenuItemChange(deltaTime);


        for (int i = 0; i < events.size(); ++i) {
            TouchEvent event = events.get(i);

            if (event.type == TouchEvent.TOUCH_DOWN) {

                enteringMenu = pointsInMenuIcon(event);

                Vector2 pos = gameScreenInfo.toModelPosition(event);
                Vector2 v = Vector2Pool.acquire().set(pos);
                world.addPointToNewMultiline(v);
                Vector2Pool.release(v);
            } else if (event.type == TouchEvent.TOUCH_DRAGGED) {

                Vector2 pos = gameScreenInfo.toModelPosition(event);
                Vector2 v = Vector2Pool.acquire().set(pos);
                world.addPointToLastMultiline(v);
                Vector2Pool.release(v);
            } else if (event.type == TouchEvent.TOUCH_UP) {

                if (!pointsInMenuIcon(event)) {
                    enteringMenu = false;
                }

                else if (enteringMenu) {
                    createMenu();
                    world.removeLastMultiline();
                }
            }
        }



        while (deltaTime > UPDATE_INTERVAL) {
            world.update(UPDATE_INTERVAL);
            deltaTime -= UPDATE_INTERVAL;
        }
        world.update(deltaTime);
    }

    private void processMenuItemChange(float deltaTime) {

        if (additionalMenuItemVisible) {

            currentDeltaTime += deltaTime;

            if (currentDeltaTime % 0.2f > 0.1f) {
                gameScreenInfo.disableVisibleMenuItem();
            }
            else {
                gameScreenInfo.enableVisibleMenuItem();
            }

            if (currentDeltaTime > menuItemDeltaTimeLimit) {
                currentDeltaTime = 0f;
                additionalMenuItemVisible = false;
                gameScreenInfo.disableVisibleMenuItem();
            }
        }
    }

    private boolean pointsInMenuIcon(TouchEvent event) {
        return gameScreenInfo.pointsInMenuIcon(event);
    }

    private void updateInMenu(List<TouchEvent> events) {

        for (int i = 0; i < events.size(); ++i) {

            TouchEvent event = events.get(i);

            if (event.type == TouchEvent.TOUCH_UP) {

                GameMenuItem item = gameScreenInfo.getGameMenuItem(event);

                if (item == GameMenuItem.RESTART) {
                    recreateWorld();
                    closeMenu(item);
                }
                else if (item == GameMenuItem.ACCELERATE) {
                    world.accelerateBalls();
                    closeMenu(item);
                }
                else if (item == GameMenuItem.CLEAR) {
                    world.removeMultilines();
//                    world.immediateWin();
                    closeMenu(item);
                }
                else if (item == GameMenuItem.RESUME) {
                    closeMenu(item);
                }
                else if (item == GameMenuItem.EXIT) {
                    closeMenu();
                    handler.obtainMessage(HandlerCodes.EXIT).sendToTarget();
                }
            }
        }
    }

    private void recreateWorld() {
        world.reset(initializer);
        gameScreenInfo.reset();
        updateHints(initializer);
    }

    private void updateHints(LevelInitializer initializer) {

        if (!hintsEnabled) {
            gameScreenInfo.setHintStatus(HintStatus.NONE_SHOWN);
        }

        else {
            Assets.firstHint = initializer.getFirstHint();
            Assets.secondHint = initializer.getSecondHint();
        }
    }

    public World getWorld() {
        return world;
    }

    public void createMenu() {
        gameScreenInfo.setMenuVisible(true);
    }

    public void closeMenu(GameMenuItem item) {

        closeMenu();

        gameScreenInfo.enableVisibleMenuItem(item);
        additionalMenuItemVisible = true;
    }

    private void closeMenu() {
        gameScreenInfo.setMenuVisible(false);
    }

    private boolean gamePaused() {
        return gameScreenInfo.isMenuVisible();
    }

    public boolean isWinOrLost() {
        return (world.isLost() || world.isWin());
    }

    public void switchMenu() {
        boolean isPaused = gamePaused();

        gameScreenInfo.setMenuVisible(!isPaused);
    }
}
