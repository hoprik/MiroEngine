package ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.api.util.holder;

public interface ThrowingSupplier<T, E extends Throwable> {
    T get() throws E;
}
