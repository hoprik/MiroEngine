package ru.hoprik.storymod.Story.Engine.Network.Packets;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;
import ru.hoprik.storymod.Story.Engine.Network.Network;

import java.util.function.Supplier;

public class SWaitScreenServerPacket {
    private final int time;
    public SWaitScreenServerPacket(int time){
        this.time = time;
    }
    public SWaitScreenServerPacket(FriendlyByteBuf buf){
        this.time = buf.readInt();
    }
    public void toBytes(FriendlyByteBuf buf) {
        buf.writeInt(time);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            ServerPlayer player = context.getSender();
            for (ServerPlayer human : player.getServer().getPlayerList().getPlayers()) {
                Network.sendToPlayer(new SWaitScreenPacket(time), human);
            }
        });
        return true;
    }
}
