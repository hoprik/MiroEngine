package ru.hoprik.storymod.Story.Engine;

import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.*;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import ru.hoprik.storymod.Story.Engine.Entity.Client.npc.NpcRender;
import ru.hoprik.storymod.Story.Engine.Entity.Client.util.CameraRender;
import ru.hoprik.storymod.Story.Engine.Entity.Client.util.SeatRender;
import ru.hoprik.storymod.Story.Engine.Entity.Entity.CameraEntity;
import ru.hoprik.storymod.Story.Engine.Entity.Entity.NpcEntity;
import ru.hoprik.storymod.Story.Engine.Entity.InitEntity;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.client.render.model.TimeEntityModel;
import ru.hoprik.storymod.Story.Engine.Utils.KeyBinds;

import static ru.hoprik.storymod.StoryMod.MODID;

//@Mod.EventBusSubscriber(modid = MODID)
//public class  EventHandler {
//
//}

@Mod.EventBusSubscriber(modid = MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientModEvents {
    public static TimeEntityModel<NpcEntity> entityTimeEntityModel;

    @SubscribeEvent
    public static void RegisterAttributes(EntityAttributeCreationEvent event){
        event.put(InitEntity.HOPRIK.get(), NpcEntity.setAttributes());
        event.put(InitEntity.CAMERA.get(), CameraEntity.setAttributes());
    }
    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {

        EntityRenderers.register(InitEntity.HOPRIK.get(), NpcRender::new);
        EntityRenderers.register(InitEntity.SEAT.get(), SeatRender::new);
        EntityRenderers.register(InitEntity.CAMERA.get(), CameraRender::new);


    }

    @SubscribeEvent
    public static void onKeyRegister(RegisterKeyMappingsEvent event) {
        event.register(KeyBinds.NPC_DIALOG);
    }
}
