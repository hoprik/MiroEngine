package ru.hoprik.storymod.Story.Engine.Gui;


import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import ru.hoprik.storymod.StoryMod;

public class Transparent extends Screen {

    float alpha = 0;
    int tick;
    int second;
    int state = 1;

    public Transparent(int second) {
        super(Component.literal("trans"));
        this.second = second;
    }

    @Override
    protected void init() {
        super.init();
    }

    @Override
    public boolean shouldCloseOnEsc() {
        return false;
    }

    @Override
    public void render(PoseStack p_96562_, int p_96563_, int p_96564_, float p_96565_) {
        renderBlackground(0);
        super.render(p_96562_, p_96563_, p_96564_, p_96565_);
    }

    public void renderBlackground(int p_96627_) {
        Tesselator tesselator = Tesselator.getInstance();
        BufferBuilder bufferbuilder = tesselator.getBuilder();
        RenderSystem.setShader(GameRenderer::getPositionTexColorShader);
        RenderSystem.setShaderTexture(0, BACKGROUND_LOCATION);
        RenderSystem.setShaderColor(0, 0, 0, alpha);
        float f = 32.0F;
        bufferbuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX_COLOR);
        bufferbuilder.vertex(0.0D, (double)this.height, 0.0D).uv(0.0F, (float)this.height / 32.0F + (float)p_96627_).color(64, 64, 64, 255).endVertex();
        bufferbuilder.vertex((double)this.width, (double)this.height, 0.0D).uv((float)this.width / 32.0F, (float)this.height / 32.0F + (float)p_96627_).color(64, 64, 64, 255).endVertex();
        bufferbuilder.vertex((double)this.width, 0.0D, 0.0D).uv((float)this.width / 32.0F, (float)p_96627_).color(64, 64, 64, 255).endVertex();
        bufferbuilder.vertex(0.0D, 0.0D, 0.0D).uv(0.0F, (float)p_96627_).color(64, 64, 64, 255).endVertex();
        tesselator.end();
        net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(new net.minecraftforge.client.event.ScreenEvent.BackgroundRendered(this, new PoseStack()));
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    @Override
    public void tick() {
        StoryMod.logger.info(tick);
        if (state == 1){
            if (alpha<1){
                alpha+=0.1;
            }
            else {
                state = 2;
            }
        } else if (state == 2) {
            tick++;
            if (tick == second*20){
                state = 3;
            }
        }
        else if (state == 3){
            if (alpha>0){
                alpha-=0.1;
            }
            else {
                minecraft.setScreen(null);
            }
        }
        super.tick();
    }
}
