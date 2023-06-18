package ru.hoprik.storymod.Story.Engine.Mixins;

import net.minecraft.data.Main;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.api.reflection.ReflectionHelper;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.api.reflection.UnlockedField;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.api.util.EnvironmentUtils;

@Mixin(value = Main.class, remap = false)
public abstract class MixinDataMain {
    @Inject(method = "main",
            at = @At(value = "INVOKE",
                    target = "Ljava/util/HashSet;<init>(Ljava/util/Collection;)V"))
    private static void onStart(String[] args, CallbackInfo ci) {
        UnlockedField<EnvironmentUtils, Boolean> fIsInDataMode = ReflectionHelper.findField(EnvironmentUtils.class, "isInDataMode");
        fIsInDataMode.set(null, true);
    }
}
