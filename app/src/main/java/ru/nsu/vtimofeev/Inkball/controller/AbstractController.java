package ru.nsu.vtimofeev.Inkball.controller;

import ru.nsu.vtimofeev.GameFramework.HelperClasses.MathClasses.Vector2;
import ru.nsu.vtimofeev.GameFramework.InputHandlers.TouchEvent;
import ru.nsu.vtimofeev.Inkball.pool.Vector2Pool;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: vtimofeev
 * Date: 6/19/12
 * Time: 11:44 AM
 * To change this template use File | Settings | File Templates.
 */
public abstract class AbstractController {

    public abstract void update(float deltaTime, List<TouchEvent> events);
}
