package ru.hoprik.storymod.Init.Entity;


import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import ru.hoprik.storymod.Init.Entity.Entity.NpcEntity;
import ru.hoprik.storymod.StoryMod;

public class InitEntity {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITIES, StoryMod.MODID);

    public static final RegistryObject<EntityType<NpcEntity>> HOPRIK =
            ENTITY_TYPES.register("hoprik",
                    () -> EntityType.Builder.of(NpcEntity::new, EntityClassification.CREATURE)
                            .sized(0.4f, 2f)
                            .build(new ResourceLocation(StoryMod.MODID, "hoprik").toString()));

    public static final RegistryObject<EntityType<NpcEntity>> YBLEDOK =
            ENTITY_TYPES.register("ybl",
                    () -> EntityType.Builder.of(NpcEntity::new, EntityClassification.CREATURE)
                            .sized(0.4f, 2f)
                            .build(new ResourceLocation(StoryMod.MODID, "ybl").toString()));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
