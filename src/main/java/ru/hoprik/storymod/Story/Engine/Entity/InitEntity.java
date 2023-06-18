package ru.hoprik.storymod.Story.Engine.Entity;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import ru.hoprik.storymod.Story.Engine.Entity.Entity.CameraEntity;
import ru.hoprik.storymod.Story.Engine.Entity.Entity.NpcEntity;
import ru.hoprik.storymod.Story.Engine.Entity.Entity.SeatEntity;
import ru.hoprik.storymod.StoryMod;

public class InitEntity {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, StoryMod.MODID);

    //pls don`t remove because without them. Script don`t working
    public static final RegistryObject<EntityType<SeatEntity>> SEAT = register("seat", EntityType.Builder.<SeatEntity>of((type, world) -> new SeatEntity(world), MobCategory.MISC).sized(0.0F, 0.0F));

    public static final RegistryObject<EntityType<CameraEntity>> CAMERA =
            ENTITY_TYPES.register("camera",
                    () -> EntityType.Builder.of(CameraEntity::new, MobCategory.MISC)
                            .sized(0f, 0f)
                            .build(new ResourceLocation(StoryMod.MODID, "camera").toString()));

    public static final RegistryObject<EntityType<NpcEntity>> HOPRIK =
            ENTITY_TYPES.register("hoprik",
                    () -> EntityType.Builder.of(NpcEntity::new, MobCategory.CREATURE)
                            .sized(0.4f, 2f)
                            .build(new ResourceLocation(StoryMod.MODID, "hoprik").toString()));

    private static <T extends Entity> RegistryObject<EntityType<T>> register(String name, EntityType.Builder<T> builder)
    {
        return ENTITY_TYPES.register(name, () -> builder.build(name));
    }

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
