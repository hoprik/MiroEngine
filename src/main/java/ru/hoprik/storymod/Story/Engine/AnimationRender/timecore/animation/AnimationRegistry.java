package ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.animation;

import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.api.animation.Animation;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

//TODO move to Forge Registries
public class AnimationRegistry {
    private static final Map<ResourceLocation, Animation> animationMap = new ConcurrentHashMap<>();

    public static Animation registerAnimation(Animation animation) {
        if (animationMap.put(animation.getId(), animation) != null) {
            throw new IllegalArgumentException("Animation with id " + animation.getId() + " is already registered.");
        }

        return animation;
    }

    @Nullable
    public static Animation getAnimation(ResourceLocation id) {
        return animationMap.get(id);
    }
}
