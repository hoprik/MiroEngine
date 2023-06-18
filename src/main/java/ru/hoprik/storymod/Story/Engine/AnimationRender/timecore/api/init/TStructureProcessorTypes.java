package ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.api.init;

import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.TimeCore;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.api.common.world.structure.processor.RandomizeBlockProcessor;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.api.registry.StructureProcessorTypeRegister;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.api.registry.util.AutoRegistrable;

public class TStructureProcessorTypes {
    @AutoRegistrable
    private static final StructureProcessorTypeRegister REGISTER = new StructureProcessorTypeRegister(TimeCore.MODID);

    public static final StructureProcessorType<RandomizeBlockProcessor> RANDOMIZE_BLOCK_PROCESSOR = REGISTER.register("randomize_block", RandomizeBlockProcessor.CODEC);
}
