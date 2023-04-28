package ru.hoprik.storymod.Init.Entity;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import ru.hoprik.storymod.Init.Entity.Entity.NpcEntity;
import ru.hoprik.storymod.StoryMod;

public class InitEntity {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, StoryMod.MODID);

    public static final RegistryObject<EntityType<NpcEntity>> HOPRIK =
            ENTITY_TYPES.register("hoprik",
                    () -> EntityType.Builder.of(NpcEntity::new, MobCategory.CREATURE)
                            .sized(0.4f, 2f)
                            .build(new ResourceLocation(StoryMod.MODID, "hoprik").toString()));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
