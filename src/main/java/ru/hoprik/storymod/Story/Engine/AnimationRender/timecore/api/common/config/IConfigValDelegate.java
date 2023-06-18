package ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.api.common.config;

public interface IConfigValDelegate<T> {
    T get();

    void set(T val);
}