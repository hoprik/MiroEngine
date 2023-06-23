package ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.animation.util;

import net.minecraft.network.FriendlyByteBuf;
import org.jetbrains.annotations.NotNull;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.animation.Layer;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.animation.ServerAnimationManager;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.animation.watcher.AnimationWatcher;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.animation.watcher.TransitionWatcher;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class AnimationSerializer {
	private static final WatcherSerializer<AnimationWatcher> COMMON_SERIALIZER = new AnimationWatcher.Serializer();
	private static final WatcherSerializer<TransitionWatcher> TRANSITION_SERIALIZER = new TransitionWatcher.Serializer();

	public static void serializeWatchers(ServerAnimationManager<?> animationManager, FriendlyByteBuf buffer) {
		Set<String> layerNames = animationManager.getLayerNames();

		buffer.writeInt(layerNames.size());

		for (String name : layerNames) {
			Layer layer = animationManager.getLayer(name);
			AnimationWatcher animationWatcher = layer.getAnimationWatcher();

			boolean hasWatcher = animationWatcher != null;
			buffer.writeBoolean(hasWatcher);

			if (hasWatcher) {
				buffer.writeUtf(name);
				serializeWatcher(animationWatcher, buffer);
			}
		}
	}

	public static Map<String, AnimationWatcher> deserializeWatchers(FriendlyByteBuf buffer) {
		int layerCount = buffer.readInt();

		Map<String, AnimationWatcher> layerMap = new HashMap<>();

		for (int i = 0; i < layerCount; i++) {
			boolean hasWatcher = buffer.readBoolean();
			if (hasWatcher) {
				String layerName = buffer.readUtf();
				AnimationWatcher watcher = deserializeWatcher(buffer);
				layerMap.put(layerName, watcher);
			}
		}

		return layerMap;
	}

	private static void serializeWatcher(@NotNull AnimationWatcher watcher, FriendlyByteBuf buffer) {
		buffer.writeBoolean(watcher instanceof TransitionWatcher);

		if (watcher instanceof TransitionWatcher) {
			TRANSITION_SERIALIZER.serialize(((TransitionWatcher) watcher), buffer);
		} else {
			COMMON_SERIALIZER.serialize(watcher, buffer);
		}
	}

	private static AnimationWatcher deserializeWatcher(FriendlyByteBuf buffer) {
		boolean isTransitionWatcher = buffer.readBoolean();
		if (isTransitionWatcher) {
			return TRANSITION_SERIALIZER.deserialize(buffer);
		} else {
			return COMMON_SERIALIZER.deserialize(buffer);
		}
	}
}