package ru.nsu.vtimofeev.Inkball.model;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: vtimofeev
 * Date: 5/17/12
 * Time: 10:41 PM
 * To change this template use File | Settings | File Templates.
 */
public interface WorldInitialization {

    public void setBallGenerator(BallGenerator ballGenerator);
    public void addWall(Wall wall);
    public void addTimerWall(TimerWall timerWall);
    public void addAccel(Accelerator accelerator);
    public void addHole(Hole hole);
    public void addBar(Bar bar);
    public void addPuzzle(Puzzle puzzle);
    public void setTime(float time);
    
    public List<Ball> getBalls();


}
