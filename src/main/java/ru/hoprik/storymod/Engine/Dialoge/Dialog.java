package ru.hoprik.storymod.Engine.Dialoge;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.fml.common.Mod;
import ru.hoprik.storymod.Engine.Network.Network;
import ru.hoprik.storymod.Engine.Network.Packets.SDialogPacket;
import ru.hoprik.storymod.Engine.Utils.SerializableRunnable;
import ru.hoprik.storymod.StoryMod;

import javax.security.auth.callback.Callback;
import java.beans.Transient;
import java.io.*;
import java.util.concurrent.Callable;

public class Dialog implements Serializable{
    String heroSay;
    Bench[] benches;
    String end = "her";
    byte[] instance;
    public byte[] runnable;

    public Dialog(String heroSay, Bench[] benches){
        this.heroSay = heroSay;
        this.benches = benches;

    }
    public Dialog(int id, Serializable runnable){
        this.heroSay = "end."+id;
        SerializableRunnable runnable1 = new SerializableRunnable(runnable);
        this.runnable = Network.toByte(runnable1);
        this.instance = Network.toByte(this);
    }


    public void show(Player entity) {
        for (ServerPlayer player : entity.getServer().getPlayerList().getPlayers()) {
            if (entity instanceof ServerPlayer) {
                Network.sendToPlayer(new SDialogPacket(heroSay, Network.toByte(benches)), (ServerPlayer) entity);
            }
        }
    }

//    public void setEnd(String end) {
//        this.end = end;
//    }
//
//
//    public void end() {
//        StoryMod.logger.info(this.heroSay);
//        StoryMod.logger.info(this.end);
//    }
}