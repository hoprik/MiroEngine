package ru.hoprik.storymod.Engine.Dialoge;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.fml.common.Mod;
import ru.hoprik.storymod.Engine.Network.Network;
import ru.hoprik.storymod.Engine.Network.Packets.SDialogPacket;
import ru.hoprik.storymod.StoryMod;

import java.beans.Transient;
import java.io.*;

public class Dialog implements Serializable{
    String heroSay;
    Bench[] benches;
    transient String end = "her";

    public Dialog(String heroSay, Bench[] benches){
        this.heroSay = heroSay;
        this.benches = benches;
    }
    public Dialog(int id){
        this.heroSay = "end."+id;

    }

    public void show(Player entity) {
        for (ServerPlayer player : entity.getServer().getPlayerList().getPlayers()) {
            if (player == entity) {
                Network.sendToPlayer(new SDialogPacket(heroSay, Network.toByte(benches)), player);
            }
        }
    }

    public void setEnd(String end) {
        this.end = end;
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        out.writeUTF(end);
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        this.end = in.readUTF();
    }

    public void end() {
        StoryMod.logger.info(this.end);
    }
}