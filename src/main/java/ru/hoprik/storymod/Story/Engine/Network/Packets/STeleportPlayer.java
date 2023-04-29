package ru.hoprik.storymod.Story.Engine.Network.Packets;



import net.minecraft.block.Blocks;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.Objects;
import java.util.function.Supplier;

public class STeleportPlayer {
    private final double x;
    private final double y;
    private final double z;

    public STeleportPlayer(Vector3d vector3d) {
        this.x = vector3d.x;
        this.y = vector3d.y;
        this.z = vector3d.z;
    }

    public STeleportPlayer(PacketBuffer buf) {
        this.x = buf.readDouble();
        this.y = buf.readDouble();
        this.z = buf.readDouble();
    }

    public void toBytes(PacketBuffer buf) {
        buf.writeDouble(x);
        buf.writeDouble(y);
        buf.writeDouble(z);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            Vector3d vector3d = new Vector3d(x, y, z);
            ServerPlayerEntity player = context.getSender();

            World world = player.level;
            for (double y = vector3d.y; y<=255; y++){
                if (world.getBlockState(new BlockPos(vector3d.x, y, vector3d.z)) == Blocks.AIR.defaultBlockState()){
                    for(ServerPlayerEntity serverPlayerEntity: player.getServer().getPlayerList().getPlayers()){
                        serverPlayerEntity.teleportTo((ServerWorld) world, vector3d.x, y, vector3d.z, player.xRot, player.yRot);
                    }
                    break;
                }
            }
        });
        return true;
    }
}
