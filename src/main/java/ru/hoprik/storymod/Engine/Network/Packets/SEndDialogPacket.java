package ru.hoprik.storymod.Engine.Network.Packets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;
import org.apache.commons.lang3.SerializationUtils;
import ru.hoprik.storymod.Engine.Dialoge.Bench;
import ru.hoprik.storymod.Engine.Dialoge.Dialog;
import ru.hoprik.storymod.Engine.Dialoge.DialogGui;
import ru.hoprik.storymod.Engine.Network.Network;
import ru.hoprik.storymod.Engine.Utils.SerializableRunnable;
import ru.hoprik.storymod.StoryMod;

import java.io.*;
import java.util.function.Supplier;

public class SEndDialogPacket {
    transient Runnable runnable;
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
            Dialog dialog = SerializationUtils.deserialize(this.instance);
            dialog.setEnd(end);
        });
        return true;
    }


}