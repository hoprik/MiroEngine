package ru.hoprik.storymod.Story.Engine;

import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import ru.hoprik.storymod.Init.Entity.Client.npc.NpcRender;
import ru.hoprik.storymod.Init.Entity.Entity.NpcEntity;
import ru.hoprik.storymod.Init.Entity.InitEntity;
import ru.hoprik.storymod.Story.Engine.Utils.KeyBind;

import static ru.hoprik.storymod.StoryMod.MODID;

@Mod.EventBusSubscriber(modid = MODID)
public class EventHandler {

}

@Mod.EventBusSubscriber(modid = MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
class ClientModEvents {

    @SubscribeEvent
    public static void RegisterAttributes(EntityAttributeCreationEvent event){
        event.put(InitEntity.HOPRIK.get(), NpcEntity.setAttributes());
        event.put(InitEntity.YBLEDOK.get(), NpcEntity.setAttributes());
        event.put(InitEntity.PASSAGER1.get(), NpcEntity.setAttributes());
    }
    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {

        EntityRenderers.register(InitEntity.HOPRIK.get(), NpcRender::new);
        EntityRenderers.register(InitEntity.YBLEDOK.get(), ru.hoprik.storymod.Init.Entity.Client.ybleduk.NpcRender::new);
        EntityRenderers.register(InitEntity.PASSAGER1.get(), ru.hoprik.storymod.Init.Entity.Client.passgers.NpcRender::new);
    }

    @SubscribeEvent
    public static void onKeyRegister(RegisterKeyMappingsEvent event) {
        event.register(KeyBind.NPC_DIALOG);
    }
}
