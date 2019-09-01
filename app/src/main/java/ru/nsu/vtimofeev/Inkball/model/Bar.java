package ru.nsu.vtimofeev.Inkball.model;

import ru.nsu.vtimofeev.GameFramework.HelperClasses.MathClasses.Rectangle;
import ru.nsu.vtimofeev.GameFramework.HelperClasses.MathClasses.Vector2;
import ru.nsu.vtimofeev.GameFramework.HelperClasses.ModelHelpers.GameObject;

/**
 * Created by IntelliJ IDEA.
 * User: vtimofeev
 * Date: 3/23/12
 * Time: 11:28 AM
 * To change this template use File | Settings | File Templates.
 */
public class Bar extends GameObject {

    public static final float defaultSize = Wall.defaultSize;
    public static final float originalWidth = defaultSize * 3f/8f;

    private Color color = Color.NONE;

    private boolean hasDirection = false;

    //Bar has either direction OR alignment. See constructor
    private Direction direction;
    private BarAlignment alignment;

    public Bar(float x, float y, Color color, BarAlignment alignment) {

        super(x, y, defaultSize, defaultSize);

        if (alignment == BarAlignment.HORIZONTAL) {
            ((Rectangle)this.bounds).lowerLeft.set(x - defaultSize / 2,
                    y - originalWidth / 2);
            (((Rectangle)this.bounds).height) = originalWidth;
        }
        //alignment == BarAlignment.VERTICAL
        else {
            ((Rectangle)this.bounds).lowerLeft.set(x - originalWidth / 2,
                    y -  defaultSize/ 2);
            (((Rectangle)this.bounds).width) = originalWidth;
        }

        hasDirection = false;
        this.alignment = alignment;
        this.color = color;
    }

    public Bar(float x, float y, Color color, Direction direction) {
        super(x, y, defaultSize, defaultSize);

        if (direction == Direction.UP) {
            ((Rectangle)this.bounds).lowerLeft.set(x - defaultSize / 2,
                    y +  (defaultSize/ 2 - originalWidth));
            (((Rectangle)this.bounds).height) = originalWidth;
        }
        else if (direction == Direction.DOWN) {
            (((Rectangle)this.bounds).height) = originalWidth;
        }
        else if (direction == Direction.LEFT) {
            (((Rectangle)this.bounds).width) = originalWidth;
        }
        else if (direction == Direction.RIGHT) {
            ((Rectangle)this.bounds).lowerLeft.set(x +  (defaultSize/ 2 - originalWidth),
                    y - defaultSize / 2);
            (((Rectangle)this.bounds).width) = originalWidth;
        }

        hasDirection = true;
        this.direction = direction;
        this.color = color;
    }


    public Color getColor() {
        return color;
    }

    public boolean hasDirection() {
        return hasDirection;
    }

    public Direction getDirection() {
        return direction;
    }

    public BarAlignment getAlignment() {
        return alignment;
    }

    @Override
    public float getWidth() {
        return defaultSize;
    }

    @Override
    public float getHeight() {
        return defaultSize;
    }

    public boolean shouldInteractWithBall(Ball ball) {

        Color ballColor = ball.getColor();

        if (hasDirection) {

            if (direction == Direction.UP && ball.velocity.y < 0f ||
                direction == Direction.DOWN && ball.velocity.y >= 0f||
                direction == Direction.RIGHT && ball.velocity.x < 0f ||
                direction == Direction.LEFT && ball.velocity.x >= 0f) {

                return true;
            }
        }

        if (this.color == Color.NONE || ballColor == this.color) {
            return false;
        }
        return true;
      }
}
