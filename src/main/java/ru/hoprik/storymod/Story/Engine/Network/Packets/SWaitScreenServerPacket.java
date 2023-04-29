package ru.hoprik.storymod.Story.Engine.Network.Packets;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;
import ru.hoprik.storymod.Story.Engine.Network.Network;

import java.util.function.Supplier;

public class SWaitScreenServerPacket {
    private final int time;
    public SWaitScreenServerPacket(int time){
        this.time = time;
    }
    public SWaitScreenServerPacket(PacketBuffer buf){
        this.time = buf.readInt();
    }
    public void toBytes(PacketBuffer buf) {
        buf.writeInt(time);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            ServerPlayerEntity player = context.getSender();
            for (ServerPlayerEntity human : player.getServer().getPlayerList().getPlayers()) {
                Network.sendToPlayer(new SWaitScreenPacket(time), human);
            }
        });
        return true;
    }
}
