package ru.hoprik.storymod.Story.Engine.Network.Packets;

import net.minecraft.client.Minecraft;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;
import ru.hoprik.storymod.Story.Engine.Gui.WakeUpScreen;

import java.util.function.Supplier;

public class SWakeUpPacket {
    public SWakeUpPacket() {
    }
    public SWakeUpPacket(PacketBuffer buf) {
    }

    public void toBytes(PacketBuffer buf) {
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            Minecraft minecraft = Minecraft.getInstance();
            minecraft.setScreen(new WakeUpScreen());
        });
        return true;
    }
}