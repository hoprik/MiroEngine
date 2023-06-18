package ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.animation;

import ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.api.animation.ActionManager;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.api.animation.AnimatedObject;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.api.animation.AnimationManager;

public class AnimationSystem<T extends AnimatedObject<T>> {
    private final ActionManager<T> actionManager;
    private final AnimationManager animationManager;

    public AnimationSystem(ActionManager<T> actionManager, AnimationManager animationManager) {
        this.actionManager = actionManager;
        this.animationManager = animationManager;
    }

    public ActionManager<T> getActionManager() {
        return actionManager;
    }

    public AnimationManager getAnimationManager() {
        return animationManager;
    }
}
