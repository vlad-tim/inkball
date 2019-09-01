package ru.nsu.vtimofeev.GameFramework.HelperClasses.GLHelpers;

public class TextureRegion {
    public final float u1, v1;
    public final float u2, v2;
    public final Texture texture;

    public TextureRegion(Texture texture, float x, float y, float width, float height) {
        checkXYConstraint(x, y, texture.width, texture.height);
        this.u1 = x / texture.width;
        this.v1 = y / texture.height;
        this.u2 = this.u1 + width / texture.width;
        this.v2 = this.v1 + height / texture.height;
        this.texture = texture;
    }

    private void checkXYConstraint(float x, float y, float width, float height) {
        if ((x > width) || (y > height)) {
            throw new IllegalArgumentException("Texture Region is illegal");
        }
    }
}
