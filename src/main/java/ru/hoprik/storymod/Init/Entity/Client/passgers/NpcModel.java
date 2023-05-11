package ru.hoprik.storymod.Init.Entity.Client.passgers;

import net.minecraft.resources.ResourceLocation;
import ru.hoprik.storymod.Init.Entity.Entity.NpcEntity;
import ru.hoprik.storymod.StoryMod;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class NpcModel extends AnimatedGeoModel<NpcEntity> {
    @Override
    public ResourceLocation getModelResource(NpcEntity object) {
        return new ResourceLocation(StoryMod.MODID, "geo/steve.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(NpcEntity object) {
        return new ResourceLocation(StoryMod.MODID, "textures/entity/vuzz_npc.png");
    }

    @Override
    public ResourceLocation getAnimationResource(NpcEntity animatable) {
        return new ResourceLocation(StoryMod.MODID, "animations/npc.animation_1.json");
    }
}
