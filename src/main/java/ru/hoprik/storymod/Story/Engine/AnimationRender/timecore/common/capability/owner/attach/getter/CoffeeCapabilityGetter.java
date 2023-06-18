package ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.common.capability.owner.attach.getter;

import net.minecraft.core.Direction;

import java.util.function.Supplier;

public interface CoffeeCapabilityGetter<T, C> {

    C getCapability(T target, Direction direction);

    default Supplier<CoffeeCapabilityGetter<T, C>> supply() {
        return () -> this;
    }

}
