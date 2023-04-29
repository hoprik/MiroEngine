package ru.hoprik.storymod.Init.Entity.Client.npc;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import ru.hoprik.storymod.Init.Entity.Entity.NpcEntity;
import ru.hoprik.storymod.StoryMod;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;



public class NpcRender extends GeoEntityRenderer<NpcEntity> {
    public NpcRender(EntityRendererManager renderManager) {
        super(renderManager, new NpcModel());
    }

    @Override
    public ResourceLocation getTextureLocation(NpcEntity animatable) {
        return new ResourceLocation(StoryMod.MODID, "textures/entity/player.png");
    }

    @Override
    public <E extends Entity> void renderLeash(NpcEntity entity, float partialTicks, MatrixStack poseStack, IRenderTypeBuffer buffer, E leashHolder) {
        poseStack.scale(1f, 1f, 1f);
        super.renderLeash(entity, partialTicks, poseStack, buffer, leashHolder);
    }
}
