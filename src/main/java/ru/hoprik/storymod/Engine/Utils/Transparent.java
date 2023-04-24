package ru.hoprik.storymod.Engine.Utils;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import com.mojang.math.Matrix4f;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;

public class Transparent {
    int width;
    int height;
    int blitOffset;

    public static final IGuiOverlay HUD_Transparent = ((gui, poseStack, partialTick, width, height) -> {
        RenderSystem.disableTexture();
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.setShader(GameRenderer::getPositionColorShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.disableBlend();
        RenderSystem.enableTexture();
    });
}
