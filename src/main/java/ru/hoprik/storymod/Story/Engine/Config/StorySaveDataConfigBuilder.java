package ru.hoprik.storymod.Story.Engine.Config;

import net.minecraftforge.common.ForgeConfigSpec;

public class StorySaveDataConfigBuilder {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.ConfigValue<Boolean> IS_WAKE_UP;

    static {
        BUILDER.push("Story");

        IS_WAKE_UP = BUILDER.define("isWakeUp", false);

        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}
