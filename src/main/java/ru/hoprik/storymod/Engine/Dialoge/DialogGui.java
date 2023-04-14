package ru.hoprik.storymod.Engine.Dialoge;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class DialogGui extends Screen {
    String hero;
    Bench[] benches;
    Player player;

    public DialogGui(String hero, Bench[] benches, Player player) {
        super(Component.literal("dialog"));
        this.hero = hero;
        this.benches = benches;
        this.player = player;
    }

    @Override
    protected void init() {
        this.addRenderableWidget(new Button(this.width / 2 - 100, this.height / 4 + 72, 200, 20, Component.literal(hero), (p_95930_) -> {
            this.minecraft.player.respawn();
            this.minecraft.setScreen((Screen)null);
        }));
        super.init();
    }
}
