package com.jojpeg.JoGdxExtend.spriteUtils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Json;

import java.util.ArrayList;
import java.util.Arrays;

public class AseSprite extends AnimatedSpriteSet {

    Ase ase;

    public AseSprite(String imgDir, String jsonDir) {
        Json json = new Json();
        json.setIgnoreUnknownFields(true);

        this.ase = json.fromJson(Ase.class, Gdx.files.internal(jsonDir));

        setup(
                new Texture(imgDir),
                ase.getMeta().getSize().w / ase.getFrames().get(0).getFrame().w,
                ase.getMeta().getSize().h / ase.getFrames().get(0).getFrame().w
        );

        for(Ase.Meta.Tag t : ase.getFrameTags()){
            addAnimation(t.name, t.from, t.to, (float)ase.getFrames().get(t.from).duration / 1000f);
            System.out.println();
        }
    }

    public Ase getAse() {
        return ase;
    }

    public static class Ase {
        ArrayList<FrameData> frames;
        Meta meta;
        int scale;

        public ArrayList<FrameData> getFrames() {
            return frames;
        }

        public Meta getMeta() {
            return meta;
        }

        public ArrayList<Meta.Tag> getFrameTags(){
            return getMeta().getFrameTags();
        }

        public int getScale() {
            return scale;
        }

        public String toString(){
            return Arrays.toString(meta.getFrameTags().toArray());
        }

        private static class Frames{
            //
            ArrayList<FrameData> frameData;
            //FrameData frameData;
        }

        private static class FrameData{
            String filename;
            PosSize frame;
            boolean rotated, trimmed;
            PosSize spriteSourceSize;
            Size sourceSize;
            int duration;

            public PosSize getFrame() {
                return frame;
            }

            public boolean isRotated() {
                return rotated;
            }

            public boolean isTrimmed() {
                return trimmed;
            }

            public PosSize getSpriteSourceSize() {
                return spriteSourceSize;
            }

            public Size getSourceSize() {
                return sourceSize;
            }

            public int getDuration() {
                return duration;
            }
        }

        private static class PosSize extends Size{
            public int x, y;
        }

        private static class Size{
            public int w, h;
        }

        private static class Meta{
            String app;
            String version;
            String format;
            float scale;
            Size size;
            ArrayList<Tag> frameTags;

            public ArrayList<Tag> getFrameTags() {
                return frameTags;
            }

            public float getScale() {
                return scale;
            }

            public Size getSize() {
                return size;
            }

            public String getApp() {
                return app;
            }

            public String getVersion() {
                return version;
            }

            public String getFormat() {
                return format;
            }

            private static class Tag{
                String name;
                int from, to;

                public String toString() {
                    return (name + ": " + from + " > " + to);
                }
            }
        }

    }
}

