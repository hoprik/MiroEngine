package ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.animation.action;

import net.minecraft.world.level.block.entity.BlockEntity;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.animation.BaseAnimationManager;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.animation.builders.PredefinedAnimations;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.api.animation.AnimatedObject;

public class TileEntityActionManager<T extends BlockEntity & AnimatedObject<T>> extends ActionManagerImpl<T> {
    private final PredefinedAnimations predefinedAnimations;

    public TileEntityActionManager(BaseAnimationManager animationManager, T boundObject, PredefinedAnimations predefinedAnimations) {
        super(animationManager, boundObject);
        this.predefinedAnimations = predefinedAnimations;
    }
}
