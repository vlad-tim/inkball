package ru.nsu.vtimofeev.GameFramework.MainClasses;

import android.content.Context;
import android.view.View;
import ru.nsu.vtimofeev.GameFramework.InputHandlers.SingleTouchHandler;
import ru.nsu.vtimofeev.GameFramework.InputHandlers.TouchEvent;

import java.util.List;


public class Input {

    SingleTouchHandler touchHandler;

    public Input(View view, float scaleX, float scaleY) {
        touchHandler = new SingleTouchHandler(view, scaleX, scaleY);
    }

    public List<TouchEvent> getTouchEvents() {
        return touchHandler.getTouchEvents();
    }

}
