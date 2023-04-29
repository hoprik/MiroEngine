package ru.hoprik.storymod.Story.Engine;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import ru.hoprik.storymod.Init.Entity.Client.npc.NpcRender;
import ru.hoprik.storymod.Init.Entity.Entity.NpcEntity;
import ru.hoprik.storymod.Init.Entity.InitEntity;

import static ru.hoprik.storymod.StoryMod.MODID;

@Mod.EventBusSubscriber(modid = MODID)
public class EventHandler {

}

@Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
class ClientModEvents {

    @SubscribeEvent
    public static void RegisterAttributes(EntityAttributeCreationEvent event){
        event.put(InitEntity.HOPRIK.get(), NpcEntity.setAttributes());
        event.put(InitEntity.YBLEDOK.get(), NpcEntity.setAttributes());
    }
    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {

        RenderingRegistry.registerEntityRenderingHandler(InitEntity.HOPRIK.get(), NpcRender::new);
        RenderingRegistry.registerEntityRenderingHandler(InitEntity.YBLEDOK.get(), ru.hoprik.storymod.Init.Entity.Client.ybleduk.NpcRender::new);
    }
}
