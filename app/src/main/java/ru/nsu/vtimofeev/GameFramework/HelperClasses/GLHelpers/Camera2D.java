package ru.nsu.vtimofeev.GameFramework.HelperClasses.GLHelpers;

import ru.nsu.vtimofeev.GameFramework.HelperClasses.MathClasses.Vector2;
import ru.nsu.vtimofeev.GameFramework.MainClasses.GLGraphics;

import javax.microedition.khronos.opengles.GL10;


public class Camera2D {

    public final Vector2 position;
    public final float frustumWidth;
    public final float frustumHeight;
    final GLGraphics glGraphics;

    public Camera2D(GLGraphics glGraphics, float frustumWidth, float frustumHeight) {
        this.glGraphics = glGraphics;
        this.frustumWidth = frustumWidth;
        this.frustumHeight = frustumHeight;
        this.position = new Vector2(frustumWidth / 2, frustumHeight / 2);
    }

    public void setViewportAndMatrices() {
        GL10 gl = glGraphics.getGL();
        gl.glViewport(0, 0, glGraphics.getWidth(), glGraphics.getHeight());
        gl.glMatrixMode(GL10.GL_PROJECTION);
        gl.glLoadIdentity();
        gl.glOrthof(position.x - frustumWidth / 2,
                position.x + frustumWidth / 2,
                position.y - frustumHeight / 2,
                position.y + frustumHeight / 2,
                1, -1);
        gl.glMatrixMode(GL10.GL_MODELVIEW);
        gl.glLoadIdentity();
    }

    public void touchToWorld(Vector2 touch) {
        touch.x = (touch.x / (float) glGraphics.getWidth()) * frustumWidth;
        touch.y = (1 - touch.y / (float) glGraphics.getHeight()) * frustumHeight;
        touch.add(position).sub(frustumWidth  / 2, frustumHeight / 2);
    }
}
