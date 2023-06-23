package ru.hoprik.storymod.Story.Engine.Network.Packets;

import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;
import ru.hoprik.storymod.Story.Engine.Gui.Transparent;
import ru.hoprik.storymod.StoryMod;

import java.util.function.Supplier;

public class SWaitScreenPacket {
    private final int time;
    public SWaitScreenPacket(int time){
        this.time = time;
    }
    public SWaitScreenPacket(FriendlyByteBuf buf){
        this.time = buf.readInt();
    }
    public void toBytes(FriendlyByteBuf buf) {
        buf.writeInt(time);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            StoryMod.logger.info("hh");
            Minecraft minecraft = Minecraft.getInstance();
            minecraft.setScreen(new Transparent(time));
        });
        return true;
    }
}
