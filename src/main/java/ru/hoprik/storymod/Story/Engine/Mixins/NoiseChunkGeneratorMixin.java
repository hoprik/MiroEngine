package ru.hoprik.storymod.Story.Engine.Mixins;


import net.minecraft.world.level.levelgen.NoiseBasedChunkGenerator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.api.common.world.structure.INoNoiseStructurePiece;

@Mixin(NoiseBasedChunkGenerator.class)
public class NoiseChunkGeneratorMixin {
//    @Redirect(method = "fillFromNoise",
//            at = @At(value = "INVOKE", target = "Ljava/util/Set;add(Ljava/lang/Object;)Z")
//    )
//    private static boolean disableNoiseForSomePieces(StructurePiece piece, ChunkPos pos, int int_) {
//        return !(piece instanceof INoNoiseStructurePiece) && piece.isCloseToChunk(pos, int_);
//    }
}
