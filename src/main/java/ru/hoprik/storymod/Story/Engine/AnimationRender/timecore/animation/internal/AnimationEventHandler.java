package ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.animation.internal;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.animation.ServerAnimationManager;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.animation.action.EntityActionManager;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.api.animation.AnimatedObject;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.api.animation.AnimationManager;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.api.common.event.LivingTickEndEvent;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.internal.common.packet.InternalPacketManager;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.internal.common.packet.animation.S2CSyncAnimationsMsg;

//TODO add tickers for tile entities
@Mod.EventBusSubscriber
public class AnimationEventHandler {

    @SubscribeEvent
    public static void onEntityTick(LivingEvent.LivingTickEvent event) {
        LivingEntity entityLiving = event.getEntity();

        if (entityLiving instanceof AnimatedObject<?>) {
            if (!entityLiving.level.isClientSide) {
                //needed for animation ticking on server side.
                ((AnimatedObject<?>) entityLiving).getSystem().getAnimationManager().applyAnimations(null);
            }
        }
    }

    public static void onEntityTickEnd(LivingTickEndEvent event) {
        LivingEntity entityLiving = event.getEntity();

        if (entityLiving instanceof AnimatedObject<?>) {
            EntityActionManager<?> actionManager = (EntityActionManager<?>) (((AnimatedObject<?>) entityLiving).getSystem().getActionManager());
            actionManager.onTick();
        }
    }

    @SubscribeEvent
    public static void onPlayerStartTracking(PlayerEvent.StartTracking event) {
        Entity target = event.getTarget();
        if (target instanceof AnimatedObject<?>) {
            AnimationManager animationManager = ((AnimatedObject<?>) target).getSystem().getActionManager().getAnimationManager();
            ServerAnimationManager<?> serverAnimationManager = (ServerAnimationManager<?>) animationManager;
            InternalPacketManager.sendToPlayer(((ServerPlayer) event.getEntity()), new S2CSyncAnimationsMsg(serverAnimationManager, target));
        }
    }
}
