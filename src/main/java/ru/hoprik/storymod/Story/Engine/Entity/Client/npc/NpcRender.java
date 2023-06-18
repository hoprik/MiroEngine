package ru.hoprik.storymod.Story.Engine.Entity.Client.npc;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import ru.hoprik.storymod.Story.Engine.Entity.Entity.NpcEntity;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.animation.renderer.AnimatedLivingEntityRenderer;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.animation.renderer.ModelConfiguration;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.client.render.model.TimeEntityModel;
import ru.hoprik.storymod.StoryMod;

public class NpcRender extends AnimatedLivingEntityRenderer<NpcEntity, TimeEntityModel<NpcEntity>> {
    ResourceLocation TEXTURE = new ResourceLocation(StoryMod.MODID, "textures/entity/player.png");
    public NpcRender(EntityRendererProvider.Context renderManager) {
        super(renderManager, new TimeEntityModel<>(ModelConfiguration.builder(NpcModel.NPC).build()), 0.4f);
    }



    @Override
    public ResourceLocation getTextureLocation(NpcEntity animatable) {
        return TEXTURE;
    }

//    @Override
//    public void render(NpcEntity entity, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
//        poseStack.scale(1,1,1);
//
//        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
//    }
}
