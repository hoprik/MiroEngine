package ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLConstructModEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.MixinEnvironment;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.animation.AnimationRegistry;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.animation.watcher.TransitionWatcher;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.api.Markers;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.api.TimeCoreAPI;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.api.animation.Animation;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.api.util.EnvironmentUtils;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.common.capability.CapabilityManager;
import ru.hoprik.storymod.StoryMod;


public final class TimeCore {
    public static final String MODID = StoryMod.MODID;
    public static final Logger LOGGER = LogManager.getLogger(MODID);
    public static TimeCore INSTANCE = null;

    private static final String MARKER_PROPERTY = "timecore.logging.markers";

    private final CapabilityManager capabilityManager;

    public TimeCore() {
        INSTANCE = this;

        checkForMixinBootstrap();

        capabilityManager = new CapabilityManager();

        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::setup);
        modEventBus.addListener(this::onConstruct);

        TimeCoreAPI.setup(this);
    }

    /**
     * Creates ResourceLocation with bound mod id.
     */
    public static ResourceLocation rl(String path) {
        return new ResourceLocation(MODID, path);
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
