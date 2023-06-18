package ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.animation;

import net.minecraft.client.Minecraft;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.animation.watcher.AnimationWatcher;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.api.animation.Animation;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.api.client.render.model.ITimeModel;

public class ClientAnimationManager extends BaseAnimationManager {
    @Override
    protected void applyAnimation(ITimeModel model, Layer layer, AnimationWatcher watcher, long currentTime) {
        Animation animation = watcher.getAnimation();
        animation.apply(model, layer, watcher.getAnimationTime(currentTime));
    }

    @Override
    public boolean isGamePaused() {
        return Minecraft.getInstance().isPaused();
    }
}
