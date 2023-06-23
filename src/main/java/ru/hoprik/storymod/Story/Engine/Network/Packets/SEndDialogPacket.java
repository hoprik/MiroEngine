package ru.hoprik.storymod.Story.Engine.Network.Packets;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;
import ru.hoprik.storymod.Story.Engine.Dialoge.Dialog;
import ru.hoprik.storymod.Story.Engine.Network.Network;
import ru.hoprik.storymod.Story.Engine.Utils.SerializableRunnable;

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
            SerializableRunnable runnable = (SerializableRunnable) Network.toObj(dialog.runnable);
            runnable.run();
        });
        return true;
    }
}