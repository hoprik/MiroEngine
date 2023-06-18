package ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.api.registry.util;

import net.minecraft.resources.ResourceLocation;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.api.util.holder.Late;

import java.util.Objects;

public abstract class Promised<T> extends Late<T> {
    private final ResourceLocation id;

    public Promised(ResourceLocation id) {
        this.id = id;
    }

    public ResourceLocation getId() {
        return id;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj instanceof Promised<?>) {
            return Objects.equals(((Promised<?>) obj).id, id);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
