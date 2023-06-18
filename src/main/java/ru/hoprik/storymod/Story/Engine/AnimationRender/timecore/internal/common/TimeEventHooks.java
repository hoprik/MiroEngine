package ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.internal.common;

import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.common.MinecraftForge;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.animation.internal.AnimationEventHandler;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.api.common.event.LivingTickEndEvent;

public class TimeEventHooks {
    public static void onLivingUpdateEnd(LivingEntity entity) {
        LivingTickEndEvent event = new LivingTickEndEvent(entity);
        AnimationEventHandler.onEntityTickEnd(event);

        MinecraftForge.EVENT_BUS.post(event);
    }
}
