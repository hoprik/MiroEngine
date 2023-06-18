package ru.hoprik.storymod.Story.Engine.Entity.Client.npc;

import ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.TimeCore;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.api.registry.TimeModelRegister;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.api.registry.util.AutoRegistrable;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.client.render.model.InFileLocation;


public class NpcModel {
    @AutoRegistrable
    private static final TimeModelRegister REGISTER = new TimeModelRegister(TimeCore.MODID);

    public static final InFileLocation NPC = REGISTER.register("geo/time_core.geo.json", "model");


}
