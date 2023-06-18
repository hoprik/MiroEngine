package ru.hoprik.storymod.Story.Engine.MixinsInter;

import net.minecraftforge.data.event.GatherDataEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(value = GatherDataEvent.class, remap = false)
public interface GatherDataEventAccessor {
    @Accessor
    GatherDataEvent.DataGeneratorConfig getConfig();
}