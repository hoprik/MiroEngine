package ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.common.capability.property.serializer

import net.minecraft.nbt.CompoundTag


interface IPropertySerializer<T> {

    fun serialize(name: String, value: T, nbt: CompoundTag)

    fun deserialize(name: String, nbt: CompoundTag): T

    fun nullable() = NullPropertySerializer(this)
}