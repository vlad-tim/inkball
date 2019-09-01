package ru.nsu.vtimofeev.Inkball.utils;

/**
 * Created by IntelliJ IDEA.
 * User: vtimofeev
 * Date: 4/2/12
 * Time: 2:05 PM
 * To change this template use File | Settings | File Templates.
 */
public class FPSCounter {

    private long startTime = System.nanoTime();
    private int FPS = 0;
    private int frames = 0;

    public void logFrame() {
        frames++;
        if (System.nanoTime() - startTime >= 1000000000) {
            FPS = frames;
            frames = 0;
            startTime = System.nanoTime();
        }
    }

    public int getFPS() {
        return FPS;
    }
}

