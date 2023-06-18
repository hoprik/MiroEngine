package ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.api.common.config;

import net.minecraftforge.fml.event.config.ModConfigEvent;

public interface ILoadListener {
    void onEveryLoad(ModConfigEvent event);
}
