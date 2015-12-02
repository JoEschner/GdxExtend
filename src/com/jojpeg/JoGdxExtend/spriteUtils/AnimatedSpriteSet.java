package com.jojpeg.JoGdxExtend.spriteUtils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.GdxRuntimeException;

import java.util.HashMap;



public class AnimatedSpriteSet extends SpriteSet {
    long lastFrameStep = -1;
    float stateTime = 0;
    String currentAnimName = "";
    HashMap<String, Animation> animations = new HashMap<String, Animation>();
    Animation currentAnim = null;

    public AnimatedSpriteSet(String name, Texture img, int xcount, int ycount) {
        super(name, img, xcount, ycount);

        int[] main = new int[xcount * ycount];
        for (int i = 0; i < xcount * ycount; i++) {
            main[i] = i;
        }
        addAnimation("main", main, 0.1f);
    }

    protected AnimatedSpriteSet(){

    }

    public void addAnimation(String name, int[] frames, float speed) {

        TextureRegion[] f = getFrames(frames);
        Animation anim = new Animation(speed, f);

        anim.setPlayMode(Animation.PlayMode.LOOP);
        animations.put(name, anim);

        currentAnimName = name;
        if (currentAnim == null) currentAnim = animations.get(name);

    }

    public void addAnimation(String name, int from, int to, float speed){
        int[] frames = new int[to - from + 1];

        for (int i = from; i < to + 1; i++) {
            frames[i-from] = i;
        }
        addAnimation(name, frames, speed);
    }

    public void setAnimation(String name) {
        if (animations.containsKey(name)) currentAnim = animations.get(name);
        currentAnimName = name;
    }

    public void resetAnimation(String name) {
        stateTime = 0.0f;
        if (animations.containsKey(name)) currentAnim = animations.get(name);
        currentAnimName = name;
    }

    public String getCurrentAnimName() {
        return currentAnimName;
    }

    public Animation getCurrentAnim() {
        if (currentAnim == null) throw new GdxRuntimeException("No Animation! Make sure to set up Animations first.");
        return currentAnim;
    }

    @Override
    public TextureRegion getCurrentFrame() {
        if (getCurrentAnim().getKeyFrame(getStateTime(), true) != null)
            return getCurrentAnim().getKeyFrame(getStateTime(), true);
        else throw new GdxRuntimeException("CurrentAnimation is Null");

    }

    private float getStateTime() {
        if (Gdx.graphics.getFrameId() != lastFrameStep) {
            this.stateTime += Gdx.graphics.getDeltaTime();
            lastFrameStep = Gdx.graphics.getFrameId();
        }
        return stateTime;

    }

    public boolean animationFinished() {
        return currentAnim.isAnimationFinished(getStateTime());
    }

    public void setSpeed(String name, float speed) {
        animations.get(name).setFrameDuration(speed);
    }
}
