package ru.hoprik.storymod.Story.Engine.Network.Packets;



import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.network.NetworkEvent;
import org.w3c.dom.Text;

import java.util.function.Supplier;

public class SPlayerMessagePacket {
    private final String hero;
    private final String text;
    public SPlayerMessagePacket(String hero, String text){
        this.hero = hero;
        this.text = text;
    }
    public SPlayerMessagePacket(PacketBuffer buf){
        this.hero = buf.readUtf();
        this.text = buf.readUtf();
    }
    public void toBytes(PacketBuffer buf) {
        buf.writeUtf(hero);
        buf.writeUtf(text);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            ServerPlayerEntity playerAll = context.getSender();
            for (PlayerEntity player: playerAll.getServer().getPlayerList().getPlayers()) {
                player.sendMessage(new StringTextComponent(TextFormatting.DARK_PURPLE +"["+hero+"]"+TextFormatting.RESET+" "+text), player.getUUID());
            }
        });
        return true;
    }
}
