package ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.animation;

import net.minecraft.network.FriendlyByteBuf;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.internal.common.packet.animation.CodecSupplier;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.internal.common.packet.animation.CodecSupplier.EntityCodecSupplier;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.internal.common.packet.animation.CodecSupplier.TileEntityCodecSupplier;

import java.util.function.Function;

public enum EnumAnimatedObjectType {
    TILE_ENTITY(TileEntityCodecSupplier::new, NetworkDispatcher.forTileEntity()),
    ENTITY(EntityCodecSupplier::new, NetworkDispatcher.forEntity());

    private final Function<FriendlyByteBuf, CodecSupplier> codecFactory;
    private final NetworkDispatcher<?> networkDispatcher;

    EnumAnimatedObjectType(Function<FriendlyByteBuf, CodecSupplier> codecFactory, NetworkDispatcher<?> networkDispatcher) {
        this.codecFactory = codecFactory;
        this.networkDispatcher = networkDispatcher;
    }

    public CodecSupplier getCodec(FriendlyByteBuf buffer) {
        return codecFactory.apply(buffer);
    }

    public NetworkDispatcher<?> getNetworkDispatcher() {
        return networkDispatcher;
    }
}
