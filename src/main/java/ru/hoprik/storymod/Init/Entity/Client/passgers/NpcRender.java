package ru.hoprik.storymod.Init.Entity.Client.passgers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import ru.hoprik.storymod.Init.Entity.Entity.NpcEntity;
import ru.hoprik.storymod.StoryMod;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class NpcRender extends GeoEntityRenderer<NpcEntity> {
    public NpcRender(EntityRendererProvider.Context renderManager) {
        super(renderManager, new NpcModel());
    }

    @Override
    public ResourceLocation getTextureLocation(NpcEntity animatable) {
        return new ResourceLocation(StoryMod.MODID, "textures/entity/vuzz_npc.png");
    }

    @Override
    public RenderType getRenderType(NpcEntity animatable, float partialTick, PoseStack poseStack, @Nullable MultiBufferSource bufferSource, @Nullable VertexConsumer buffer, int packedLight, ResourceLocation texture) {
        poseStack.scale(1f, 1f, 1f);
        return super.getRenderType(animatable, partialTick, poseStack, bufferSource, buffer, packedLight, texture);
    }
}

class NpcRender2 extends GeoEntityRenderer<NpcEntity> {
    public NpcRender2(EntityRendererProvider.Context renderManager) {
        super(renderManager, new NpcModel());
    }

    @Override
    public ResourceLocation getTextureLocation(NpcEntity animatable) {
        return new ResourceLocation(StoryMod.MODID, "textures/entity/vuzz_npc.png");
    }

    @Override
    public RenderType getRenderType(NpcEntity animatable, float partialTick, PoseStack poseStack, @Nullable MultiBufferSource bufferSource, @Nullable VertexConsumer buffer, int packedLight, ResourceLocation texture) {
        poseStack.scale(1f, 1f, 1f);
        return super.getRenderType(animatable, partialTick, poseStack, bufferSource, buffer, packedLight, texture);
    }
}

class NpcRender3 extends GeoEntityRenderer<NpcEntity> {
    public NpcRender3(EntityRendererProvider.Context renderManager) {
        super(renderManager, new NpcModel());
    }

    @Override
    public ResourceLocation getTextureLocation(NpcEntity animatable) {
        return new ResourceLocation(StoryMod.MODID, "textures/entity/vuzz_npc.png");
    }

    @Override
    public RenderType getRenderType(NpcEntity animatable, float partialTick, PoseStack poseStack, @Nullable MultiBufferSource bufferSource, @Nullable VertexConsumer buffer, int packedLight, ResourceLocation texture) {
        poseStack.scale(1f, 1f, 1f);
        return super.getRenderType(animatable, partialTick, poseStack, bufferSource, buffer, packedLight, texture);
    }
}