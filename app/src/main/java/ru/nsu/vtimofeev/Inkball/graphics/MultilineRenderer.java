package ru.nsu.vtimofeev.Inkball.graphics;

import ru.nsu.vtimofeev.GameFramework.HelperClasses.GLHelpers.Texture;
import ru.nsu.vtimofeev.GameFramework.HelperClasses.GLHelpers.Vertices;
import ru.nsu.vtimofeev.GameFramework.HelperClasses.MathClasses.Segment;
import ru.nsu.vtimofeev.GameFramework.HelperClasses.MathClasses.Vector2;
import ru.nsu.vtimofeev.GameFramework.MainClasses.GLGraphics;
import ru.nsu.vtimofeev.Inkball.model.MultiLine;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by IntelliJ IDEA.
 * User: vtimofeev
 * Date: 3/29/12
 * Time: 12:44 PM
 * To change this template use File | Settings | File Templates.
 */
public class MultilineRenderer {

    final float[] verticesBuffer;
    final Vertices vertices;
    int numVerticles;
    int bufferIndex;
    private GameScreenInfo gameScreenInfo;
    private GL10 gl10;
    private float multiLineRadius = MultiLine.defaultRadius;

    Vector2 v = new Vector2();
    Vector2 temp = new Vector2();
    Vector2 mv = new Vector2();

    public MultilineRenderer(GLGraphics glGraphics, GameScreenInfo gameScreenInfo, int maxVerticles) {

        this.verticesBuffer = new float[maxVerticles * 2];
        this.vertices = new Vertices(glGraphics, maxVerticles, 0, false, false);
        this.gameScreenInfo = gameScreenInfo;
        this.numVerticles = 0;
        this.gl10 = glGraphics.getGL();
    }

    public void startRendering() {
        numVerticles = 0;
        bufferIndex = 0;
    }

    public void renderSegment(Segment segment) {

        //TODO that's awful
        v.set(segment.v1, segment.v0);
        temp.set(v);
        temp.rotate(90);
        temp.normalize();
        temp.mul(multiLineRadius);
        mv.set(segment.v0);
        mv.add(temp);

        verticesBuffer[bufferIndex++] = gameScreenInfo.toFrustrumPositionX(mv.x);
        verticesBuffer[bufferIndex++] = gameScreenInfo.toFrustrumPositionY(mv.y);

        mv.sub(temp);
        mv.sub(temp);
        verticesBuffer[bufferIndex++] = gameScreenInfo.toFrustrumPositionX(mv.x);
        verticesBuffer[bufferIndex++] = gameScreenInfo.toFrustrumPositionY(mv.y);

        mv.add(v);
        verticesBuffer[bufferIndex++] = gameScreenInfo.toFrustrumPositionX(mv.x);
        verticesBuffer[bufferIndex++] = gameScreenInfo.toFrustrumPositionY(mv.y);

        verticesBuffer[bufferIndex++] = gameScreenInfo.toFrustrumPositionX(mv.x);
        verticesBuffer[bufferIndex++] = gameScreenInfo.toFrustrumPositionY(mv.y);

        mv.add(temp);
        mv.add(temp);
        verticesBuffer[bufferIndex++] = gameScreenInfo.toFrustrumPositionX(mv.x);
        verticesBuffer[bufferIndex++] = gameScreenInfo.toFrustrumPositionY(mv.y);

        mv.sub(v);
        verticesBuffer[bufferIndex++] = gameScreenInfo.toFrustrumPositionX(mv.x);
        verticesBuffer[bufferIndex++] = gameScreenInfo.toFrustrumPositionY(mv.y);

        numVerticles += 6;
    }

    public void endRendering() {

        if (0 == numVerticles) return;

        gl10.glColor4f(0, 0, 0, 1);
        gl10.glDisable(GL10.GL_TEXTURE_2D);

        vertices.setVertices(verticesBuffer, 0, bufferIndex);
        vertices.bind();
        vertices.draw(GL10.GL_TRIANGLES, 0, numVerticles);
        vertices.unbind();
        gl10.glColor4f(1, 1, 1, 1);
        gl10.glEnable(GL10.GL_TEXTURE_2D);
    }
}
