package ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.api.client.resource;

import ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.api.client.resource.blockstates.StairsBlockStateFactory;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.api.client.resource.location.BlockModelLocation;

public class BlockStateResources {
    public static BlockStateResource stairs(BlockModelLocation stairs, BlockModelLocation innerStairs, BlockModelLocation outerStairs) {
        return StairsBlockStateFactory.create(stairs, innerStairs, outerStairs);
    }
}
