package ru.nsu.vtimofeev.Inkball.controller;

import android.os.Handler;
import ru.nsu.vtimofeev.GameFramework.HelperClasses.MathClasses.Vector2;
import ru.nsu.vtimofeev.GameFramework.InputHandlers.TouchEvent;
import ru.nsu.vtimofeev.Inkball.activities.HandlerCodes;
import ru.nsu.vtimofeev.Inkball.chooseLevelModel.ChooseLevelModel;
import ru.nsu.vtimofeev.Inkball.chooseLevelModel.Level;
import ru.nsu.vtimofeev.Inkball.graphics.ChooseLevelScreenInfo;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: vtimofeev
 * Date: 3/27/12
 * Time: 1:12 PM
 * To change this template use File | Settings | File Templates.
 */
public class ChooseLevelController extends AbstractController{


    private ChooseLevelScreenInfo chooseLevelScreenInfo;
    private ChooseLevelModel chooseLevelModel;
    private Handler handler;

    //In model position
    private Vector2 lastTouch = new Vector2();
    private final float dragCoef = 3.0f;
    private final float deltaXLimit = Level.defWidth / 5;

    private float deltaXDragged = 0f;

    private Level lastPointedLevel = null;

    public ChooseLevelController(ChooseLevelScreenInfo chooseLevelScreenInfo, Handler handler) {
        chooseLevelModel = new ChooseLevelModel();
        this.handler = handler;
        this.chooseLevelScreenInfo = chooseLevelScreenInfo;
    }

    public void update(float deltaTime, List<TouchEvent> events) {

        for (int i = 0; i < events.size(); ++i) {

            TouchEvent event = events.get(i);
            Vector2 pos = chooseLevelScreenInfo.toModelPosition(event);

            if (event.type == TouchEvent.TOUCH_DOWN) {

                if (chooseLevelScreenInfo.pointsInLeftPointer(event)) {
                    chooseLevelModel.moveRight();
                }
                else if (chooseLevelScreenInfo.pointsInRightPointer(event)) {
                    chooseLevelModel.moveLeft();
                }

                else if (chooseLevelScreenInfo.pointsInBackButton(event)) {
                    handler.obtainMessage(HandlerCodes.EXIT).sendToTarget();
                }

                else {
                    Level pointedLevel = chooseLevelModel.getPointedLevel(pos);

                    if (pointedLevel != null && pointedLevel.getState() != Level.LOCKED) {

                        pointedLevel.startGrowing();
                        lastPointedLevel = pointedLevel;
                    }
                }
            }

            else if (event.type == TouchEvent.TOUCH_DRAGGED) {

                Level pointedLevel = chooseLevelModel.getPointedLevel(pos);

                if (lastPointedLevel != null && lastPointedLevel != pointedLevel) {
                    lastPointedLevel.startReducing();
                }

                if (pointedLevel != null && pointedLevel.getState() != Level.LOCKED) {

                    pointedLevel.startGrowing();
                    lastPointedLevel = pointedLevel;
                }

            }


            else if (event.type == TouchEvent.TOUCH_UP) {

                Level chosenLevel = chooseLevelModel.getPointedLevel(pos);

                if (null != chosenLevel) {
                    handler.obtainMessage(HandlerCodes.START_LEVEL, chosenLevel).sendToTarget();
                    lastPointedLevel = chosenLevel;
                }
            }
        }

        chooseLevelModel.update(deltaTime);
    }

    public ChooseLevelModel getModel() {
        return chooseLevelModel;
    }

    public void updateLevelsState(Map<String, ?> map) {
        
        List<Level> levels = chooseLevelModel.getLevels();
        Set<String> levelIds = map.keySet();
        
        for (String id : levelIds) {

            Integer levelState = (Integer)map.get(id);
            
            int state = (levelState != null) ? levelState.intValue() : Level.LOCKED;
            levels.get(Integer.parseInt(id)).setState(state);
        }

        //First level cant be locked
        int firstLevelState = levels.get(0).getState();
        if (firstLevelState != Level.COMPLETED) {
            levels.get(0).setState(Level.OPEN);
        }

        //also reduce lastLevel
        if (lastPointedLevel != null) {
            lastPointedLevel.startReducing();
        }
    }
}
