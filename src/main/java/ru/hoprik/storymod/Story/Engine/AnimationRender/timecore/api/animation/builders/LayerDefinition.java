package ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.api.animation.builders;

import ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.api.animation.BlendType;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.api.util.MathUtils;

public record LayerDefinition(String name, BlendType blendType, float weight) {
    public LayerDefinition(String name, BlendType blendType, float weight) {
        this.name = name;
        this.blendType = blendType;
        this.weight = MathUtils.coerceInRange(weight, 0, 1);
    }
}