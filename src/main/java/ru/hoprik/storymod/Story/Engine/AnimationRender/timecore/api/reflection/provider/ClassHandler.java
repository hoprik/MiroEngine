package ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.api.reflection.provider;

import org.jetbrains.annotations.Nullable;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.api.reflection.UnlockedMethod;

public interface ClassHandler {
    boolean canHandle(Class<?> clazz);

    <O, R> boolean isStatic(UnlockedMethod<O, R> method);

    <O, R> void requireStatic(UnlockedMethod<O, R> method);

    @Nullable <O, R> UnlockedMethod<O, R> findMethod(Class<O> clazz, String signature);

    @Nullable <O, R> Object invokeStaticMethod(UnlockedMethod<O, R> method, Object... args);
}
