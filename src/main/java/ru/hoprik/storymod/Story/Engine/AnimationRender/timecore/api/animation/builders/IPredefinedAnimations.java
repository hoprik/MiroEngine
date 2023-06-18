package ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.api.animation.builders;

import ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.animation.AnimationStarter;

public interface IPredefinedAnimations {
    interface IEntityPredefinedAnimations extends IPredefinedAnimations {
        void setWalkingAnimation(AnimationStarter walkingAnimationStarter, String layerName);

        void setIdleAnimation(AnimationStarter idleAnimationStarter, String layerName);
    }
}