package ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.common.capability

import net.minecraftforge.common.capabilities.ICapabilityProvider
import ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.common.capability.property.CoffeeProperty
import kotlin.reflect.KProperty0

abstract class KCoffeeCapabilityInstance<T : ICapabilityProvider> : CoffeeCapabilityInstance<T>() {
    fun <R> KProperty0<R>.markChanged(): R {
        if (this.getDelegate() is CoffeeProperty<*>) {
            (this.getDelegate() as CoffeeProperty<*>).changed = true
        }
        return this()
    }
}




