package ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.animation.component;

import com.mojang.math.Vector3f;

public interface IKeyFrame {
    int getTime();

    Vector3f getVec(KeyFrameState state);

    IKeyFrame withNewTime(int time);
}
