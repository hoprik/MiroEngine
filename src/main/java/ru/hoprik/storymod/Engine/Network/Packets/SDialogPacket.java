package ru.hoprik.storymod.Engine.Network.Packets;

import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;
import ru.hoprik.storymod.Engine.Dialoge.Bench;
import ru.hoprik.storymod.Engine.Dialoge.DialogGui;
import ru.hoprik.storymod.StoryMod;

import java.util.function.Supplier;

public class SDialogPacket {

    private String heroSay;
    private Bench[] benches;

    public SDialogPacket(String heroSay, Bench[] benches) {
        this.heroSay = heroSay;
        this.benches = benches;
    }

    public SDialogPacket(FriendlyByteBuf buf) {
    }

    public void toBytes(FriendlyByteBuf buf) {
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            // HERE WE ARE ON THE CLIENT!
//            Minecraft minecraft = Minecraft.getInstance();
//            minecraft.setScreen(new DialogGui(heroSay, benches, null));
            StoryMod.logger.info("ffff");
        });
        return true;
    }
}
