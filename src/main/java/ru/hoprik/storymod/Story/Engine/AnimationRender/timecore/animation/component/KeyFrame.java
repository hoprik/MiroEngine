package ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.animation.component;

import com.mojang.math.Vector3f;

public class KeyFrame implements IKeyFrame {
    /**
     * in ms
     */
    private final int time;
    private final Vector3f vec;

    public KeyFrame(int time, Vector3f vec) {
        this.time = time;
        this.vec = vec;
    }

    public static KeyFrame createIdleKeyFrame(int startTime, Vector3f modelIdleVec) {
        return new KeyFrame(startTime, modelIdleVec);
    }

    @Override
    public int getTime() {
        return time;
    }

    @Override
    public Vector3f getVec(KeyFrameState state) {
        return vec;
    }

    @Override
    public KeyFrame withNewTime(int time) {
        return new KeyFrame(time, vec);
    }

    @Override
    public String toString() {
        return "KeyFrame{" +
                "time=" + time +
                ", vec=" + vec +
                '}';
    }
}
