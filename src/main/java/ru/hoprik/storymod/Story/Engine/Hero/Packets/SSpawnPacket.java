package ru.hoprik.storymod.Story.Engine.Hero.Packets;

import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.network.NetworkEvent;
import ru.hoprik.storymod.Story.Engine.Network.Network;

import java.util.function.Supplier;

public class SSpawnPacket {
    public final byte[] hero;
    public final BlockPos pos;

    public SSpawnPacket(byte[] hero, BlockPos pos) {
        this.hero = hero;
        this.pos = pos;
    }

    public SSpawnPacket(FriendlyByteBuf buf) {
        this.hero = buf.readByteArray();
        this.pos = buf.readBlockPos();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeByteArray(hero);
        buf.writeBlockPos(pos);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            Entity entity = (Entity) Network.toObj(hero);
            entity.setPos(pos.getX(), pos.getY(), pos.getZ());
            entity.level.addFreshEntity(entity);
        });
        return true;
    }
}