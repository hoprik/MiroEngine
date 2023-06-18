package ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.api.animation;

import org.jetbrains.annotations.NotNull;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.animation.component.BoneOption;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.animation.component.IKeyFrame;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.animation.watcher.TimelineSnapshot;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.client.render.model.TimeModelPart;

public abstract class TransitionFactoryWithDestination extends TransitionFactory {
    public TransitionFactoryWithDestination(Animation source) {
        super(source);
    }

    public abstract Iterable<BoneOption> getDestinationBones();

    /**
     * Returns destination keyframe of provided type for transition animation.
     * <p>
     * Will be called only when {@link #source} is a destination animation.
     *
     * @param part                 part for which destination keyframe should be calculated.
     * @param destinationStartTime snapshot which contains the start animation time to which keyframe should lead
     * @param boneName             location of bone/part for which destination keyframe should be calculated.
     * @param channel              type of keyframe which should be calculated.
     * @param transitionTime       time of transition between source and destination animations.
     */
    @NotNull
    public abstract IKeyFrame getDestKeyFrame(TimeModelPart part, TimelineSnapshot destinationStartTime, String boneName, Channel channel, int transitionTime);

    @Override
    public TransitionFactoryWithDestination withRequiredDestination() {
        return this;
    }
}
