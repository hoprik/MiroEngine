package ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.internal.registry;

import net.minecraft.resources.ResourceLocation;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.api.registry.util.Promised;

public class InsertablePromised<T> extends Promised<T> {
    public InsertablePromised(ResourceLocation id) {
        super(id);
    }

    public void insert(T value) {
        this.value = value;
    }
}
