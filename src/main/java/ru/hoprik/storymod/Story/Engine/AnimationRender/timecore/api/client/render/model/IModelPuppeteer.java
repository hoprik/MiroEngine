package ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.api.client.render.model;

public interface IModelPuppeteer<T> {
    void addModelProcessor(IModelProcessor<? super T> processor);
}
