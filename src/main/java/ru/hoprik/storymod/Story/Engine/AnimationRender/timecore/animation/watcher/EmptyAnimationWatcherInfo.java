package ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.animation.watcher;

import org.jetbrains.annotations.Nullable;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.animation.AnimationStarter;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.animation.component.LoopMode;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.api.animation.Animation;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.api.animation.IAnimationWatcherInfo;

public class EmptyAnimationWatcherInfo implements IAnimationWatcherInfo {
    @Override
    public int getElapsedTime(long currentMillis) {
        return 0;
    }

    @Override
    public int getAnimationTime(long currentMillis) {
        return 0;
    }

    @Override
    public int getElapsedLength() {
        return 0;
    }

    @Override
    public LoopMode getLoopMode() {
        return LoopMode.DO_NOT_LOOP;
    }

    @Override
    public Animation getAnimation() {
        return Animation.NULL;
    }

    @Override
    public boolean isAutoTransition() {
        return false;
    }

    @Override
    public boolean isNull() {
        return true;
    }

    @Override
    public boolean autoTransitsTo(Animation animation) {
        return false;
    }

    @Override
    public boolean autoTransitsFrom(Animation animation) {
        return false;
    }

    @Override
    public float getSpeed() {
        return 0;
    }

    @Override
    public boolean isReversed() {
        return false;
    }

    @Override
    public @Nullable AnimationStarter.AnimationData getNextAnimation() {
        return null;
    }

    @Override
    public String toString() {
        return "No animation";
    }
}
