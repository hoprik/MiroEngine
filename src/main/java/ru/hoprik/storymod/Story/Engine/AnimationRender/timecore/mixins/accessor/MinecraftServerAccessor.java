package ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.mixins.accessor;

import net.minecraft.server.MinecraftServer;
import net.minecraft.world.level.storage.LevelStorageSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(MinecraftServer.class)
public interface MinecraftServerAccessor {
    @Accessor("storageSource")
    LevelStorageSource.LevelStorageAccess getStorageSource();
}
