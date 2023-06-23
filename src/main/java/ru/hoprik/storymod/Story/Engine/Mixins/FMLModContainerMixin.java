package ru.hoprik.storymod.Story.Engine.Mixins;

import net.minecraftforge.fml.javafmlmod.FMLModContainer;
import net.minecraftforge.forgespi.language.ModFileScanData;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.TimeCore;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.internal.loading.ModInitializer;

@Mixin(value = FMLModContainer.class, remap = false)
public abstract class FMLModContainerMixin {
    @Shadow
    @Final
    private ModFileScanData scanResults;
    @Shadow
    @Final
    private Class<?> modClass;

    @Inject(method = "constructMod",
            at = @At(value = "TAIL")
    )
    public void fmlModConstructingHook(CallbackInfo ci) {
        FMLModContainer modContainer = (FMLModContainer) (Object) this;
        Object mod = getMod();

        if (mod instanceof TimeCore) {
            ModInitializer.run(modContainer, scanResults, mod);
        }
    }

    @Shadow
    public abstract Object getMod();
}