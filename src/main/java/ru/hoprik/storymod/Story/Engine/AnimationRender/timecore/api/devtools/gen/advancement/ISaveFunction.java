package ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.api.devtools.gen.advancement;

import net.minecraft.advancements.Advancement;
import net.minecraft.resources.ResourceLocation;

@FunctionalInterface
public interface ISaveFunction {
    Advancement process(ResourceLocation savePath, Advancement.Builder builder);
}