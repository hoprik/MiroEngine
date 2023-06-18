package ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.api.animation.builders;

import ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.api.animation.BlendType;

public interface IAnimationManagerBuilder {
    void addLayer(String name, BlendType blendType, float weight);

    void addLayer(LayerDefinition layer);

    void addMainLayer();
}
