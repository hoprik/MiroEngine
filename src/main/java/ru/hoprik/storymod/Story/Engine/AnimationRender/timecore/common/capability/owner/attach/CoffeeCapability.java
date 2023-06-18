package ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.common.capability.owner.attach;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.common.capability.CoffeeCapabilityInstance;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.common.capability.owner.CapabilityOwner;

public record CoffeeCapability<T extends ICapabilityProvider, C extends CoffeeCapabilityInstance<T>>(
        CapabilityOwner<T> owner,
        Capability<C> capability) {

}
