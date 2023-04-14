package ru.hoprik.storymod;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.hoprik.storymod.Engine.Network.Network;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(StoryMod.MODID)
public class StoryMod
{
    public static final String MODID = "storymod";


    public static Logger logger = LogManager.getLogger(StoryMod.class);
    public StoryMod()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        MinecraftForge.EVENT_BUS.register(this);
        modEventBus.addListener(this::CommonSetup);
    }

    private void CommonSetup(FMLCommonSetupEvent event){
        Network.register();
    }
}
