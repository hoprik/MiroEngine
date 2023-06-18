package ru.hoprik.storymod.Story.Engine.Mixins;

import net.minecraft.client.model.geom.builders.MaterialDefinition;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(MaterialDefinition.class)
public interface MaterialDefinitionAccessor {
    @Accessor("xTexSize")
    int getTextureWidth();

    @Accessor("yTexSize")
    int getTextureHeight();
}
