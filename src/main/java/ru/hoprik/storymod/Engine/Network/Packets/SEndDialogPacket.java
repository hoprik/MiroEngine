package ru.hoprik.storymod.Engine.Network.Packets;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;
import ru.hoprik.storymod.Engine.Dialoge.Dialog;
import ru.hoprik.storymod.Engine.Network.Network;
import ru.hoprik.storymod.Engine.Utils.SerializableRunnable;
import ru.hoprik.storymod.StoryMod;

import java.io.*;
import java.util.function.Supplier;

public class SEndDialogPacket {
    private final String end;
    private final byte[] instance;
    public SEndDialogPacket(String end, byte[] instance) {
        this.end = end;
        this.instance = instance;
    }

    public SEndDialogPacket(FriendlyByteBuf buf) {
        this.end = buf.readUtf();
        this.instance = buf.readByteArray();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeUtf(end);
        buf.writeByteArray(instance);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            Dialog dialog = (Dialog) Network.toObj(this.instance);
            StoryMod.logger.info(dialog);
            SerializableRunnable runnable = (SerializableRunnable) Network.toObj(dialog.runnable);
            runnable.run();
        });
        return true;
    }
}