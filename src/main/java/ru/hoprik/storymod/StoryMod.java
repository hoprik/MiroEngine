package ru.hoprik.storymod;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLConstructModEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.MixinEnvironment;
import ru.hoprik.storymod.Story.Engine.Entity.InitEntity;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Gecolib3.GeckoLib;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.TimeCore;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.animation.AnimationRegistry;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.animation.watcher.TransitionWatcher;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.api.Markers;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.api.TimeCoreAPI;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.api.animation.Animation;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.api.animation.AnimationAPI;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.api.util.EnvironmentUtils;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.common.capability.CapabilityManager;
import ru.hoprik.storymod.Story.Engine.Config.StorySaveDataConfigBuilder;
import ru.hoprik.storymod.Story.Engine.Network.Network;

import java.util.Map;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(StoryMod.MODID)
public class StoryMod
{
    public static final String MODID = "storymod";


    public static StoryMod INSTANCE = null;
    private final CapabilityManager capabilityManager;
    public static Logger logger = LogManager.getLogger(StoryMod.class);
    public static Map<String, Animation> core;
    private static final String MARKER_PROPERTY = "timecore.logging.markers";

    public StoryMod(){
        GeckoLib.initialize();
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        InitEntity.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);
        modEventBus.addListener(this::CommonSetup);
        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, StorySaveDataConfigBuilder.SPEC, "story-server.toml");

        INSTANCE = this;

        checkForMixinBootstrap();

        capabilityManager = new CapabilityManager();

        TimeCoreAPI.setup(this);

        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onConstruct);
    }

    public static ResourceLocation rl(String path) {
        return new ResourceLocation(MODID, path);
    }

    private void CommonSetup(FMLCommonSetupEvent event){
        Network.register();
        core = AnimationAPI.loadAndRegisterAnimations(TimeCore.rl("animations/npc_timecore.animation.json"));
    }

    private void onConstruct(FMLConstructModEvent event) {
        EnvironmentUtils.handleMarkerVisibility(TimeCore.MODID, MARKER_PROPERTY, Markers.getAll());
    }

    private void setup(final FMLCommonSetupEvent event) {
//        ReflectionHelper.loadClass(StructureRevealer.class); //FIXME port?
        event.enqueueWork(capabilityManager::addDefaultAttachers);

        AnimationRegistry.registerAnimation(Animation.NULL);
        AnimationRegistry.registerAnimation(TransitionWatcher.TRANSITION);
    }

    private static void checkForMixinBootstrap() {
        try {
            if (MixinEnvironment.getCurrentEnvironment().getPhase() != MixinEnvironment.Phase.DEFAULT) {
                throw new IllegalArgumentException("Mixins are not initialized");
            }
        } catch (NoClassDefFoundError e) {
            throw new IllegalStateException("TimeCore requires MixinBootstrap Mod to be loaded.");
        }
    }

    public CapabilityManager getCapabilityManager() {
        return capabilityManager;
    }
}
