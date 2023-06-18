package ru.hoprik.storymod.Story.Engine.Network.Packets;

import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.network.NetworkEvent;
import ru.hoprik.storymod.Story.Engine.Dialoge.Bench;
import ru.hoprik.storymod.Story.Engine.Dialoge.DialogGui;
import ru.hoprik.storymod.Story.Engine.Network.Network;

import java.util.function.Supplier;

public class SCutscene {
    public final int entity;
    public SCutscene(int entity) {
        this.entity = entity;
    }
    public SCutscene(FriendlyByteBuf buf) {
        this.entity = buf.readInt();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeInt(entity);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            Minecraft minecraft = Minecraft.getInstance();
            Entity entityCamera = minecraft.level.getEntity(entity);
            minecraft.setCameraEntity(entityCamera);
        });
        return true;
    }
}
