package ru.nsu.vtimofeev.Inkball.model;

import ru.nsu.vtimofeev.GameFramework.HelperClasses.ModelHelpers.GameObject;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by IntelliJ IDEA.
 * User: vtimofeev
 * Date: 5/16/12
 * Time: 9:51 PM
 * To change this template use File | Settings | File Templates.
 */
public class BallGenerator extends GameObject{

    public static final float defaultSize = Wall.defaultSize;
    
    private float spawnInterval = 3f;
    private float time = 0f;
    
    private List <Ball> realBalls;
    private Queue<Ball> scheduledBalls = new LinkedList<Ball>();


    public BallGenerator(List<Ball> balls) {
        //Not visible
        super(-Wall.defaultSize, -Wall.defaultSize, defaultSize, defaultSize);
        this.realBalls = balls;
    }

    public BallGenerator(float x, float y, List<Ball> balls) {
        super(x, y, defaultSize, defaultSize);
        this.realBalls = balls;
    }

    public void scheduleBall(Ball ball) {
        scheduledBalls.add(ball);
    }

    public void update(float deltaTime){

        time += deltaTime;

        if (time > spawnInterval){
            time -= spawnInterval;

            if (scheduledBalls.size() != 0) {
                realBalls.add(scheduledBalls.remove());
            }
        }
    }

    public boolean isEmpty() {
        return scheduledBalls.isEmpty();
    }

    public void setSpawnInterval(float spawnInterval) {
        this.spawnInterval = spawnInterval;

        correctTime();
    }

    private void correctTime() {
        if (spawnInterval != 0f) {
            time = spawnInterval - 1f;
        }
    }

    @Override
    public float getWidth() {
        return  defaultSize;
    }

    @Override
    public float getHeight() {
        return defaultSize;
    }

    public void clear() {
        scheduledBalls.clear();
    }
}
