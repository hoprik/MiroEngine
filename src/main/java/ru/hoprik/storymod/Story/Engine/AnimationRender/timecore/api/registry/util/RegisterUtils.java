package ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.api.registry.util;

import net.minecraft.world.level.block.Block;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.api.client.resource.location.BlockModelLocation;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.api.client.resource.location.TextureLocation;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.api.registry.BlockRegister;

public class RegisterUtils {
    public static BlockModelLocation defaultBml(BlockRegister.BlockRegisterChain<? extends Block> chain) {
        return new BlockModelLocation(chain.getModId(), chain.getName());
    }

    public static TextureLocation defaultBlockTl(BlockRegister.BlockRegisterChain<? extends Block> chain) {
        return new TextureLocation(chain.getModId(), "block/" + chain.getName());
    }

    public static TextureLocation defaultItemTl(BlockRegister.BlockRegisterChain<? extends Block> chain) {
        return new TextureLocation(chain.getModId(), "item/" + chain.getName());
    }
}
