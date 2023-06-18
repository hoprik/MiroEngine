package ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.internal.common.config;

import ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.TimeCore;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.api.registry.ConfigRegister;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.api.registry.util.AutoRegistrable;

public class TimeCoreConfigManager {
    @AutoRegistrable
    private static final ConfigRegister REGISTER = new ConfigRegister(TimeCore.MODID);

    @AutoRegistrable.Init
    private static void register() {
        REGISTER.register(MainConfig.INSTANCE);
    }
}
