package ru.nsu.vtimofeev.Inkball.levels.generation;

import ru.nsu.vtimofeev.Inkball.model.Color;

/**
 * Created by IntelliJ IDEA.
 * User: vtimofeev
 * Date: 7/10/12
 * Time: 1:54 PM
 * To change this template use File | Settings | File Templates.
 */
public class BallDescription {
    
    public final VelocityType velocity;
    public final Color color;
    public Coordinates pos;

    public Coordinates vel;

    public BallDescription(VelocityType velocity, Color color) {
        this.velocity = velocity;
        this.color = color;
    }

    public BallDescription(VelocityType velocity, Color color, Coordinates coord) {
        this.velocity = velocity;
        this.color = color;
        this.pos = coord;
    }

    public BallDescription(VelocityType velocity, Color color, Coordinates pos, Coordinates vel) {
        this.velocity = velocity;
        this.color = color;
        this.pos = pos;
        this.vel = vel;
    }
}
