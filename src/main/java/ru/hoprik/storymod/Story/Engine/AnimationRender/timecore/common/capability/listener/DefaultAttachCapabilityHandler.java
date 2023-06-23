package ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.common.capability.listener;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.chunk.LevelChunk;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.TimeCore;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.common.capability.owner.CapabilityOwner;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.common.capability.owner.attach.CoffeeCapabilityAttacher;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.common.capability.owner.attach.CoffeeCapabilityProvider;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.common.capability.owner.attach.getter.CoffeeCapabilityGetter;

import java.util.ArrayList;

@Mod.EventBusSubscriber
public class DefaultAttachCapabilityHandler {

    @SubscribeEvent
    public static void onTileAttachCapability(AttachCapabilitiesEvent<BlockEntity> event) {
        attachCaps(event, CapabilityOwner.BLOCK_ENTITY);
    }

    @SubscribeEvent
    public static void onEntityAttachCapability(AttachCapabilitiesEvent<Entity> event) {
        attachCaps(event, CapabilityOwner.ENTITY);
    }

    // TODO: Village capability
//    @SubscribeEvent
//    public void onVillageAttachCapability(AttachCapabilitiesEvent<VillagePieces.Village> event) {
//        tryAttachCapability(event, CapabilityOwner.VILLAGE);
//    }

    @SubscribeEvent
    public static void onWorldAttachCapability(AttachCapabilitiesEvent<Level> event) {
        attachCaps(event, CapabilityOwner.LEVEL);
    }

    @SubscribeEvent
    public static void onChunkAttachCapability(AttachCapabilitiesEvent<LevelChunk> event) {
        attachCaps(event, CapabilityOwner.CHUNK);
    }

    @SubscribeEvent
    public static void onItemStackAttachCapability(AttachCapabilitiesEvent<ItemStack> event) {
        attachCaps(event, CapabilityOwner.ITEM_STACK);
    }

    private static <T extends ICapabilityProvider> void attachCaps(AttachCapabilitiesEvent<T> event, CapabilityOwner<T> owner) {
        CoffeeCapabilityProvider<?> provider = new CoffeeCapabilityProvider<>(event.getObject());
        ArrayList<CoffeeCapabilityAttacher<T, ?>> attachers = owner.getAttachers();

        boolean attached = false;
        if (attachers != null) {
            for (CoffeeCapabilityAttacher<T, ?> attacher : attachers) {
                if (attacher.predicate().test(event.getObject())) {
                    provider.addCapability(attacher.capability(), (CoffeeCapabilityGetter) attacher.getterFactory().get());
                    attached = true;
                }
            }
        }

        if (attached) {
            event.addCapability(TimeCore.rl("capability-provider"), provider);
        }
    }
}