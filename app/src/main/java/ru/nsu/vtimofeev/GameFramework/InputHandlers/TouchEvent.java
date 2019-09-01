package ru.nsu.vtimofeev.GameFramework.InputHandlers;

public class TouchEvent {
    public static final int TOUCH_DOWN = 0;
    public static final int TOUCH_UP = 1;
    public static final int TOUCH_DRAGGED = 2;

    public int x, y;
    public int pointer;
    public int type;
}