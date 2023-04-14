package ru.hoprik.storymod.Engine.Dialoge;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.DeathScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.apache.logging.log4j.core.jmx.Server;
import ru.hoprik.storymod.Engine.Network.Network;
import ru.hoprik.storymod.Engine.Network.Packets.SDialogPacket;
import ru.hoprik.storymod.StoryMod;

import java.util.ArrayList;
import java.util.List;


public class Dialog {
    String heroSay;
    Bench[] benches;

    public Dialog(String heroSay, Bench[] benches){
        this.heroSay = heroSay;
        this.benches = benches;
    }
    public void show(Player entity){
        for (ServerPlayer player: entity.getServer().getPlayerList().getPlayers()) {
            if (player == entity) {
                Network.sendToPlayer(new SDialogPacket(heroSay, benches), player);
            }
        }
    }
}  // Работает?