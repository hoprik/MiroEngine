package ru.hoprik.storymod.Story.Engine.MixinsInter;

import net.minecraft.client.multiplayer.ClientPacketListener;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
//ClientPlayNetHandler
@Mixin(ClientPacketListener.class)
public interface ViewDistanceProvider {
    @Accessor
    int getServerChunkRadius();
}
