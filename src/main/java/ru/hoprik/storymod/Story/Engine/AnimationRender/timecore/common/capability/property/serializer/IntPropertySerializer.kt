package ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.common.capability.property.serializer

import net.minecraft.nbt.CompoundTag


object IntPropertySerializer : IPropertySerializer<Int> {

    override fun serialize(name: String, value: Int, nbt: CompoundTag) = nbt.putInt(name, value)
    override fun deserialize(name: String, nbt: CompoundTag) = nbt.getInt(name)

    object Nullable : NullPropertySerializer<Int>(this)
}