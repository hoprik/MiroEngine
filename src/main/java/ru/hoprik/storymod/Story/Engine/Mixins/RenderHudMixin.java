package ru.hoprik.storymod.Story.Engine.Mixins;


import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.scores.Scoreboard;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import ru.hoprik.storymod.Story.Engine.Utils.ServerSaveData;

@Mixin(Gui.class)
public abstract class RenderHudMixin {


    @Shadow @Final protected Minecraft minecraft;

    @Inject(method = "renderHotbar", at = @At(value = "HEAD"), cancellable = true)
    public void renderHotBarMix(CallbackInfo ci) {
        if (ServerSaveData.isCutScene) {
            ci.cancel();
            minecraft.gameRenderer.setRenderHand(false);
        }
        else{
            minecraft.gameRenderer.setRenderHand(true);
        }
    }

    @Inject(method = "renderCrosshair", at = @At(value = "HEAD"), cancellable = true)
    public void renderCrossHairsMix(CallbackInfo ci) {
        if (ServerSaveData.isCutScene) {
            ci.cancel();
        }
    }

}

