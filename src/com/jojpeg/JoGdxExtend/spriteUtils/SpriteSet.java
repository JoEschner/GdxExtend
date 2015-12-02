package com.jojpeg.JoGdxExtend.spriteUtils;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.GdxRuntimeException;

import java.util.Arrays;

public class SpriteSet {
    //
    String name;
    float rotation;
    TextureRegion[] frames;
    TextureRegion currentFrame;

    public SpriteSet(String name, Texture img, int frameCols, int frameRows) {
        this.name = name;
        setup(img, frameCols, frameRows);
    }

    public SpriteSet() {

    }

    public TextureRegion getCurrentFrame() {
        return currentFrame;
    }

    public TextureRegion[] getFrames() {
        return frames;
    }

    public int getFrameWidth() {
        return frames[0].getRegionWidth();
    }

    public TextureRegion[] getFrames(int[] f) {

        TextureRegion[] result = new TextureRegion[f.length];

        try {
            for (int i = 0; i < f.length; i++) {
                result[i] = getFrames()[f[i]];
            }
        } catch (IndexOutOfBoundsException e) {
            throw new GdxRuntimeException("Out of Bounds. Request on Set [\"" + name + "\"]: " + Arrays.toString(f));
        }

        return result;
    }

    public TextureRegion getFrame(int index) {
        return getFrames()[index];
    }

    public void setup(Texture img, int frameCols, int frameRows) {

        frames = new TextureRegion[frameCols * frameRows];

        if (img.getWidth() / frameCols == 0 || img.getHeight() / frameRows == 0) {
            throw new GdxRuntimeException(
                    "Wrong Index declaration: width -> " +
                            img.getWidth() + "/" + frameCols + ", height -> " +
                            img.getHeight() + "/" + frameRows
            );
        }

        TextureRegion[][] tmp = TextureRegion.split(
                img,
                img.getWidth() / frameCols,
                img.getHeight() / frameRows
        );

        System.out.println(frames.length + " and " + tmp[0].length + " * " + tmp.length);
        int index = 0;
        for (int i = 0; i < frameRows; i++) {
            for (int j = 0; j < frameCols; j++) {
                frames[index++] = tmp[i][j];
            }
        }
    }

    public float getRotation() {
        return rotation;
    }

    public void setRotation(float rotation) {
        this.rotation = rotation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void flip(boolean x, boolean y) {
        for (TextureRegion t : getFrames()) t.flip(x, y);
    }

    public void flipX(boolean x) {
        for (TextureRegion t : getFrames()) t.flip(x, t.isFlipY());
    }

    public void flipY(boolean y) {
        for (TextureRegion t : getFrames()) t.flip(t.isFlipX(), y);
    }
}
