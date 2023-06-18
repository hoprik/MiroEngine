package ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.animation.util;

import net.minecraft.network.FriendlyByteBuf;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.animation.watcher.AnimationWatcher;

public interface WatcherSerializer<T extends AnimationWatcher> {
    void serialize(T watcher, FriendlyByteBuf buffer);

    T deserialize(FriendlyByteBuf buffer);
}