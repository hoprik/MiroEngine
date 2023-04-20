package ru.hoprik.storymod.Engine.Network.Packets;

import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.network.NetworkEvent;
import ru.hoprik.storymod.Engine.Dialoge.Bench;
import ru.hoprik.storymod.Engine.Dialoge.Dialog;
import ru.hoprik.storymod.Engine.Dialoge.DialogGui;
import ru.hoprik.storymod.Engine.Network.Network;
import ru.hoprik.storymod.StoryMod;

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