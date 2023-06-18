package ru.hoprik.storymod.Story.Engine.Mixins;

import net.minecraft.SharedConstants;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(SharedConstants.class)
public class FastLoadMixin {
    /**
     * @author Andrew Steinborn
     * @reason Disables any possibility of enabling DFU "optimizations"
     */
    @Overwrite
    public static void enableDataFixerOptimizations() {
        // Turn this method into a no-op.
    }
}
