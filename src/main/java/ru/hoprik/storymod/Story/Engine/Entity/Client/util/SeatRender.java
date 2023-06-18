package ru.hoprik.storymod.Story.Engine.Entity.Client.util;

import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import ru.hoprik.storymod.Story.Engine.Entity.Entity.SeatEntity;

public class SeatRender extends EntityRenderer<SeatEntity> {

    public SeatRender(EntityRendererProvider.Context p_174008_) {
        super(p_174008_);
    }

    @Override
    public ResourceLocation getTextureLocation(SeatEntity p_114482_) {
        return null;
    }
}
