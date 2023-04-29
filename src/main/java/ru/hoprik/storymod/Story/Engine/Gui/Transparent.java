package ru.hoprik.storymod.Story.Engine.Gui;


import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.StringTextComponent;
import ru.hoprik.storymod.Story.Engine.Utils.ColorManager;
import ru.hoprik.storymod.StoryMod;

public class Transparent extends Screen {
    public static final ResourceLocation BLACK = new ResourceLocation(StoryMod.MODID,"textures/gui/black.png");

    int alpha = 0;
    int tick;
    int second;
    int state = 1;

    public Transparent(int second) {
        super(new StringTextComponent("trans"));
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
    public void render(MatrixStack p_96562_, int p_96563_, int p_96564_, float p_96565_) {
        renderBlackground();
        super.render(p_96562_, p_96563_, p_96564_, p_96565_);
    }

    public  void renderBlackground() {
        Tessellator tessellator = net.minecraft.client.renderer.Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuilder();
        this.minecraft.getTextureManager().bind(BLACK);
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, alpha);
        float f = 32.0F;
        bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
        bufferbuilder.vertex(0.0D, this.height, 0.0D).uv(0.0F, (float)this.height / 32.0F + (float)0).color(64, 64, 64, 255).endVertex();
        bufferbuilder.vertex(this.width, this.height, 0.0D).uv((float)this.width / 32.0F, (float)this.height / 32.0F + (float)0).color(64, 64, 64, 255).endVertex();
        bufferbuilder.vertex(this.width, 0.0D, 0.0D).uv((float)this.width / 32.0F, (float)0).color(64, 64, 64, 255).endVertex();
        bufferbuilder.vertex(0.0D, 0.0D, 0.0D).uv(0.0F, (float)0).color(64, 64, 64, 255).endVertex();
        tessellator.end();
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    @Override
    public void tick() {
        StoryMod.logger.info(alpha);
        if (state == 1){
            if (alpha<=255){
                alpha+=10;
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
                alpha-=10;
            }
            else {
                minecraft.setScreen(null);
            }
        }
        super.tick();
    }
}
