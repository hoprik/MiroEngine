package ru.hoprik.storymod.Init.Entity;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import ru.hoprik.storymod.Init.Entity.Entity.NpcEntity;
import ru.hoprik.storymod.Init.Entity.Entity.SeatEntity;
import ru.hoprik.storymod.StoryMod;

public class InitEntity {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, StoryMod.MODID);


//    public static final RegistryObject<EntityType<SeatEntity>> SEAT =
//            ENTITY_TYPES.register("seatEntity",
//                    () -> EntityType.Builder.of(SeatEntity::new, MobCategory.MISC)
//                            .sized(0.4f, 2f)
//                            .build(new ResourceLocation(StoryMod.MODID, "hoprik").toString()));

    public static final RegistryObject<EntityType<SeatEntity>> SEAT = register("seat", EntityType.Builder.<SeatEntity>of((type, world) -> new SeatEntity(world), MobCategory.MISC).sized(0.0F, 0.0F));


    public static final RegistryObject<EntityType<NpcEntity>> HOPRIK =
            ENTITY_TYPES.register("hoprik",
                    () -> EntityType.Builder.of(NpcEntity::new, MobCategory.CREATURE)
                            .sized(0.4f, 2f)
                            .build(new ResourceLocation(StoryMod.MODID, "hoprik").toString()));

    public static final RegistryObject<EntityType<NpcEntity>> PASSAGER1 =
            ENTITY_TYPES.register("passager",
                    () -> EntityType.Builder.of(NpcEntity::new, MobCategory.CREATURE)
                            .sized(0.4f, 2f)
                            .build(new ResourceLocation(StoryMod.MODID, "passager").toString()));

    public static final RegistryObject<EntityType<NpcEntity>> YBLEDOK =
            ENTITY_TYPES.register("ybl",
                    () -> EntityType.Builder.of(NpcEntity::new, MobCategory.CREATURE)
                            .sized(0.4f, 2f)
                            .build(new ResourceLocation(StoryMod.MODID, "ybl").toString()));

    private static <T extends Entity> RegistryObject<EntityType<T>> register(String name, EntityType.Builder<T> builder)
    {
        return ENTITY_TYPES.register(name, () -> builder.build(name));
    }

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
