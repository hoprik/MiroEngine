package ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.api.util.holder;

public interface ThrowingRunnable<E extends Throwable> {
    void run() throws E;
}
