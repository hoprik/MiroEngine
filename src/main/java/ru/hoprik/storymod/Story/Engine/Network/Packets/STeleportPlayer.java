package ru.hoprik.storymod.Story.Engine.Network.Packets;

import com.mojang.math.Vector3d;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.network.NetworkEvent;
import ru.hoprik.storymod.Story.Engine.StoryFunction;
import ru.hoprik.storymod.StoryMod;

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

    public STeleportPlayer(FriendlyByteBuf buf) {
        this.x = buf.readDouble();
        this.y = buf.readDouble();
        this.z = buf.readDouble();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeDouble(x);
        buf.writeDouble(y);
        buf.writeDouble(z);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            Vector3d vector3d = new Vector3d(x, y, z);
            ServerPlayer player = context.getSender();

            Level world = player.level;
            for (double y = vector3d.y; y<=255; y++){
                if (world.getBlockState(new BlockPos(vector3d.x, y, vector3d.z)) == Blocks.AIR.defaultBlockState()){
                    for(ServerPlayer serverPlayerEntity: player.getServer().getPlayerList().getPlayers()){
                        StoryMod.logger.info("vvv");
                        serverPlayerEntity.teleportTo((ServerLevel) world,vector3d.x, y, vector3d.z, player.getYRot(), player.getXRot() );
                    }
                    break;
                }
            }
        });
        return true;
    }
}
