package ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.mixins.accessor;

import net.minecraft.server.level.ChunkHolder;
import net.minecraft.server.level.ChunkMap;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(ChunkMap.class)
public interface ChunkMapAccessor {
    @Invoker("getChunks")
    Iterable<ChunkHolder> callGetChunks();
}
