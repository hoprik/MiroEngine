package ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.animation.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BlockEntity;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.api.animation.AnimatedObject;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.api.client.render.model.IModelPuppeteer;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.api.client.render.model.ITimeModelRenderer;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.client.render.model.TimeModel;

public abstract class AnimatedTileEntityRenderer<T extends BlockEntity & AnimatedObject<T>> implements BlockEntityRenderer<T>, ITimeModelRenderer<T> {
    private final ModelPuppeteer<T> puppeteer = new ModelPuppeteer<>();
    protected TimeModel model;

    public AnimatedTileEntityRenderer(TimeModel model) {
        this.model = model;
    }

    @Override
    public void render(T tileEntityIn, float partialTicks, PoseStack matrixStackIn, MultiBufferSource bufferIn, int combinedLightIn, int combinedOverlayIn) {
        getTimeModel().reset();

        tileEntityIn.getSystem().getAnimationManager().applyAnimations(getTimeModel());
        puppeteer.processModel(tileEntityIn, model, partialTicks);

        ResourceLocation texture = getTexture(tileEntityIn);

        RenderType renderType = model.renderType(texture);

        matrixStackIn.pushPose();

        matrixStackIn.translate(0.5F, 0, 0.5F);

        model.renderToBuffer(matrixStackIn, bufferIn.getBuffer(renderType), combinedLightIn, combinedOverlayIn, 1, 1, 1, 1);

        matrixStackIn.popPose();
    }

    protected abstract ResourceLocation getTexture(T tileEntityIn);

    @Override
    public TimeModel getTimeModel() {
        return model;
    }

    @Override
    public IModelPuppeteer<T> getPuppeteer() {
        return puppeteer;
    }
}