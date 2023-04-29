package ru.hoprik.storymod.Story.Engine.Gui;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.text.StringTextComponent;

import static ru.hoprik.storymod.Story.Engine.Gui.Transparent.BLACK;

public class WakeUpScreen extends Screen {
    Button button;
    int tick;
    float alpha = 1.0F;
    boolean isAlpha = false;
    public WakeUpScreen() {
        super(new StringTextComponent("wakeup"));
    }

    @Override
    protected void init() {
        button = this.addButton(new Button(this.width / 2 - 75, this.height / 4+100 , 150, 20, new StringTextComponent("Встать c кровати"), (p_213021_1_) -> {
            isAlpha = true;
        }));
        button.visible = false;

    }

    @Override
    public void render(MatrixStack p_96562_, int p_96563_, int p_96564_, float p_96565_) {
        renderBlackground();
        super.render(p_96562_, p_96563_, p_96564_, p_96565_);
    }

    public void renderBlackground() {
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
    public boolean shouldCloseOnEsc() {
        return false;
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    @Override
    public void tick() {
        ++tick;
        if (tick == 60){
            button.visible = true;
        }
        if (isAlpha){
            if (alpha>=0){
                alpha-=0.1;
            }
            else {
                this.minecraft.setScreen(null);
            }
        }
        super.tick();
    }
}
