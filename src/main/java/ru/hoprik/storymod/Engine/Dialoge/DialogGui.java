package ru.hoprik.storymod.Engine.Dialoge;



import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import ru.hoprik.storymod.Engine.Network.Network;
import ru.hoprik.storymod.Engine.Network.Packets.SEndDialogPacket;
import ru.hoprik.storymod.StoryMod;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DialogGui extends Screen {
    String heroSay;
    Bench[] benches;
    List<Button> buttons = new ArrayList<>();
    int y = 72;

    public DialogGui(String heroSay, Bench[] benches) {
        super(Component.literal("dialog"));
        this.heroSay = heroSay;
        this.benches = benches;
    }

    @Override
    protected void init() {
        for (Bench bench: benches) {
             Button button = this.addRenderableWidget(new Button(this.width / 2 - 75, this.height / 4 + y, 150, 20, Component.literal(bench.playerSay), (p_213021_1_) -> {
                 String[] strings = bench.function.heroSay.split("\\.");
                 if (Objects.equals(strings[0], "end")){
                     this.minecraft.setScreen(null);
                     StoryMod.logger.info(bench.function.heroSay);
                     Network.sendToServer(new SEndDialogPacket(strings[1], Network.toByte(bench.function)));
                 }
                 else {
                    this.minecraft.setScreen(new DialogGui(bench.function.heroSay, bench.function.benches));
                 }
             }));
             buttons.add(button);
             y+=30;
        }
        super.init();
    }

    @Override
    public void render(PoseStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        renderBackground(matrixStack);
        drawCenteredString(matrixStack, this.font, heroSay, this.width / 2, 85, 16777215);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
    }

    @Override
    public boolean shouldCloseOnEsc() {
        return false;
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}
