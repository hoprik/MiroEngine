package ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.animation.builders;

import ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.animation.BaseAnimationManager;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.animation.ClientAnimationManager;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.animation.EnumAnimatedObjectType;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.animation.ServerAnimationManager;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.animation.action.ActionManagerImpl;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.api.animation.AnimatedObject;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.api.animation.AnimationConstants;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.api.animation.BlendType;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.api.animation.builders.IAnimationManagerBuilder;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.api.animation.builders.LayerDefinition;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.api.util.SingleUseBuilder;

import java.util.LinkedHashMap;

public class BaseAnimationManagerBuilder extends SingleUseBuilder implements IAnimationManagerBuilder {
    private final LinkedHashMap<String, LayerDefinition> layerDefinitions = new LinkedHashMap<>();

    @Override
    public void addLayer(String name, BlendType blendType, float weight) {
        addLayer(new LayerDefinition(name, blendType, weight));
    }

    @Override
    public void addMainLayer() {
        verifyNotUsed();
        addLayer(AnimationConstants.MAIN_LAYER_NAME, BlendType.OVERWRITE, 1);
    }

    @Override
    public void addLayer(LayerDefinition layerDefinition) {
        verifyNotUsed();
        if (layerDefinitions.put(layerDefinition.name(), layerDefinition) != null) {
            throw new IllegalArgumentException("Layer with location " + layerDefinition.name() + " already exist in provided animation manager.");
        }
    }

    BaseAnimationManager build(boolean serverSide, EnumAnimatedObjectType type) {
        BaseAnimationManager manager;
        if (serverSide) {
            manager = new ServerAnimationManager<>(type.getNetworkDispatcher());
        } else {
            manager = new ClientAnimationManager();
        }

        if (layerDefinitions.isEmpty()) {
            addMainLayer();
        }

        manager.buildLayers(layerDefinitions);

        setUsed();

        return manager;
    }

    @SuppressWarnings("unchecked")
    <T extends AnimatedObject<T>> void init(BaseAnimationManager manager, ActionManagerImpl<T> actionManager) {
        if (manager instanceof ServerAnimationManager) {
            ((ServerAnimationManager<T>) manager).setActionManager(actionManager);
        }
    }
}
