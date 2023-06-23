package ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.animation;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.TimeCore;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.animation.watcher.AnimationWatcher;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.animation.watcher.TransitionWatcher;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.api.animation.Animation;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.api.animation.AnimationConstants;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.api.animation.AnimationManager;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.api.animation.builders.LayerDefinition;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.api.client.render.model.ITimeModel;

import javax.annotation.OverridingMethodsMustInvokeSuper;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class BaseAnimationManager implements AnimationManager {
    private Map<String, Layer> layerMap;

    @Override
    public boolean containsLayer(String name) {
        return layerMap.get(name) != null;
    }

    @NotNull
    @Override
    public Layer getLayer(String name) {
        Layer layer = layerMap.get(name);
        if (layer == null) throw new RuntimeException("There is no layer with location " + name);
        return layer;
    }

    @Override
    public Set<String> getLayerNames() {
        return layerMap.keySet();
    }

    @Override
    public void setAnimation(AnimationStarter animationStarter, String layerName) {
        AnimationStarter.AnimationData data = animationStarter.getData();
        if (containsLayer(layerName)) {
            Layer layer = getLayer(layerName);

            if (data.isIgnorable()) {
                AnimationWatcher watcher = layer.getAnimationWatcher();
                if (watcher != null) {
                    Animation animationIn = data.getAnimation();

                    if (animationIn.equals(watcher.getAnimation()) || (watcher instanceof TransitionWatcher && animationIn.equals(((TransitionWatcher) watcher).getDestination()))) {
                        return;//TODO add check for speed
                    }
                }
            }

            layer.setAnimation(data);
        } else {
            TimeCore.LOGGER.error("Can't start animation: layer with location " + layerName + " doesn't exist in provided animation manager.");
        }
    }

    protected void onAnimationStart(Layer layer, AnimationStarter.AnimationData data, AnimationWatcher watcher) {

    }

    @Override
    public void removeAnimation(String layerName) {
        removeAnimation(layerName, AnimationConstants.BASIC_TRANSITION_TIME);
    }

    @Override
    public void removeAnimation(String layerName, int transitionTime) {
        if (containsLayer(layerName)) {
            Layer layer = getLayer(layerName);
            layer.removeAnimation(transitionTime);
        } else {
            TimeCore.LOGGER.error("Can't find layer with location " + layerName);
        }
    }

    @Override
    public void applyAnimations(ITimeModel model) {
        long currentTime = System.currentTimeMillis();
        for (Layer layer : layerMap.values()) {
            layer.update(this, model, currentTime);

            AnimationWatcher watcher = layer.getAnimationWatcher();

            if (watcher != null) {
                applyAnimation(model, layer, watcher, currentTime);
            }
        }
    }

    protected abstract void applyAnimation(ITimeModel model, Layer layer, AnimationWatcher watcher, long currentTime);

    protected abstract boolean isGamePaused();

    /**
     * Called when the animation was removed or ended.
     *
     * @param watcher
     */
    @OverridingMethodsMustInvokeSuper
    protected void onAnimationEnd(@Nullable ITimeModel model, Layer layer, AnimationWatcher watcher) {
        onAnimationStop(watcher);
    }

    public void buildLayers(LinkedHashMap<String, LayerDefinition> layers) {
        layerMap = layers.values().stream()
                .map(layerDefinition -> new Layer(this, layerDefinition))
                .collect(Collectors.toMap(Layer::getName, layer -> layer, (o, o2) -> o, LinkedHashMap::new));
    }

    /**
     * Called on every animation stop like removing animation or replacing it.
     *
     * @param watcher
     */
    protected void onAnimationStop(AnimationWatcher watcher) {

    }

    public void onLoopedAnimationRestart(AnimationWatcher watcher) {

    }
}