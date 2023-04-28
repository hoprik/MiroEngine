package ru.hoprik.storymod.Story.Engine.Network.Packets;

import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;
import ru.hoprik.storymod.Story.Engine.Dialoge.Bench;
import ru.hoprik.storymod.Story.Engine.Dialoge.DialogGui;
import ru.hoprik.storymod.Story.Engine.Network.Network;

import java.util.function.Supplier;

public class SDialogPacket  {
    private final String heroSay;
    private final byte[] benches;
    public SDialogPacket(String heroSay, byte[] bench) {
        this.heroSay = heroSay;
        this.benches = bench;
    }
    public SDialogPacket(FriendlyByteBuf buf) {
        this.heroSay = buf.readUtf();
        this.benches = buf.readByteArray();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeUtf(heroSay);
        buf.writeByteArray(benches);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            Minecraft minecraft = Minecraft.getInstance();
            minecraft.setScreen(new DialogGui(this.heroSay,(Bench[]) Network.toObj(benches)));
        });
        return true;
    }
}