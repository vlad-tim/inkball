package ru.nsu.vtimofeev.GameFramework.HelperClasses.GLHelpers;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLUtils;
import ru.nsu.vtimofeev.GameFramework.MainClasses.FileIO;
import ru.nsu.vtimofeev.GameFramework.MainClasses.GLGraphics;
import ru.nsu.vtimofeev.Inkball.resources.Settings;

import javax.microedition.khronos.opengles.GL;
import javax.microedition.khronos.opengles.GL10;
import java.io.IOException;
import java.io.InputStream;


public class Texture {

    public int width;
    public int height;

    private GLGraphics glGraphics;
    private FileIO fileIO;
    private String fileName;
    private int textureId;
    private int minFilter;
    private int magFilter;

    public Texture(GLGraphics glGraphics, FileIO fileIO, String fileName) {
        this.glGraphics = glGraphics;
        this.fileIO = fileIO;
        this.fileName = fileName;
        load();
    }

    private void load() {
        GL10 gl = glGraphics.getGL();
        int[] textureIds = new int[1];
        gl.glGenTextures(1, textureIds, 0);
        textureId = textureIds[0];

        InputStream in = null;
        try {
            in = fileIO.readAsset(fileName);
            Bitmap bitmap = BitmapFactory.decodeStream(in);
            gl.glBindTexture(GL10.GL_TEXTURE_2D, textureId);
            GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, bitmap, 0);
            //TODO need to manage filter somehow
            int filtering = Settings.isFilteringEnabled() ? GL10.GL_LINEAR : GL10.GL_NEAREST;
            setFilters(filtering, filtering);
            gl.glBindTexture(GL10.GL_TEXTURE_2D, 0);
            width = bitmap.getWidth();
            height = bitmap.getHeight();
            bitmap.recycle();
        } catch (IOException e) {
            throw new RuntimeException("Couldn't loadGameTextures texture '" + fileName + "'", e);
        } finally {
            if (in != null)
                try {
                    in.close();
                } catch (IOException e) {
                }
        }
    }

    public void reload() {
        load();
        bind();
        setFilters(minFilter, magFilter);
        glGraphics.getGL().glBindTexture(GL10.GL_TEXTURE_2D, 0);
    }

    public void setFilters(int minFilter, int magFilter) {
        this.minFilter = minFilter;
        this.magFilter = magFilter;
        GL10 gl = glGraphics.getGL();
        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, minFilter);
        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, magFilter);
    }

    public static void setFilters(int minFilter, int magFilter, GL10 gl) {
        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, minFilter);
        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, magFilter);
    }

    public void bind() {
        GL10 gl = glGraphics.getGL();
        gl.glBindTexture(GL10.GL_TEXTURE_2D, textureId);
    }

    public void dispose() {
        GL10 gl = glGraphics.getGL();
        gl.glBindTexture(GL10.GL_TEXTURE_2D, 0);
        int[] textureIds = {textureId};
        gl.glDeleteTextures(1, textureIds, 0);
    }
}