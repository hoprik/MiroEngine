package ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.api.client.render.model;

public interface ITimeModelRenderer<T> {
    ITimeModel getTimeModel();

    IModelPuppeteer<T> getPuppeteer();
}
