package ru.hoprik.storymod.Engine.Dialoge;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.fml.common.Mod;
import ru.hoprik.storymod.Engine.Network.Network;
import ru.hoprik.storymod.Engine.Network.Packets.SDialogPacket;
import ru.hoprik.storymod.Engine.Utils.SerializableRunnable;
import ru.hoprik.storymod.StoryMod;

import java.beans.Transient;
import java.io.*;

@Mod.EventBusSubscriber(modid = StoryMod.MODID)
public class Dialog implements Serializable{
    String heroSay;
    Bench[] benches;
    public byte[] end;

    public Dialog(String heroSay, Bench[] benches){
        this.heroSay = heroSay;
        this.benches = benches;
    }
    public Dialog(int id, Runnable runnable){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream)) {
            objectOutputStream.writeObject(new SerializableRunnable(runnable));
        } catch (IOException e) {
            // Обработка исключения
        }
        this.heroSay = "end."+id;
        this.end = byteArrayOutputStream.toByteArray();

    }

    public void show(Player entity) {
        for (ServerPlayer player : entity.getServer().getPlayerList().getPlayers()) {
            if (player == entity) {
                Network.sendToPlayer(new SDialogPacket(heroSay, Network.toByte(benches)), player);
            }
        }
    }

    public void end() {


    }
}