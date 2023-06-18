package ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.api.animation;

import org.jetbrains.annotations.Nullable;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.animation.AnimationStarter;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.animation.component.IKeyFrame;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.animation.component.KeyFrame;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.animation.component.Transition;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.animation.watcher.TimelineSnapshot;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.api.client.render.model.ITimeModel;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.api.util.holder.Pair;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.client.render.model.TimeModelPart;

import java.util.List;

public abstract class TransitionFactory {
    /**
     * Animation, from which transition will start.
     */
    protected Animation source;

    public TransitionFactory(Animation source) {
        this.source = source;
    }

    @SuppressWarnings("unchecked")
    public <T> T getSourceTyped() {
        return ((T) source);
    }

    /**
     * Returns list of bones with calculated change vectors.
     * Returns null if list can't be created due to, for example, lack of bone option list.
     * In this case transition will be created with stronger dependence to destination animation.
     * <p>
     * Will be called only when {@link #source} is a start animation.
     *
     * @param dest           animation with data, to which transition will lead.
     * @param model          model, for which we apply animation.
     * @param existingTime   source animation existing time
     * @param transitionTime time of transition between source and destination animations.
     */
    @Nullable
    public abstract List<Transition.BoneOption> createTransitionBones(AnimationStarter.AnimationData dest, ITimeModel model, int existingTime, int transitionTime);

    public TransitionFactoryWithDestination withRequiredDestination() {
        throw new UnsupportedOperationException(String.format("This should never be reached. TransitionFactory with source '%s' unable to be transition destination", source));
    }

    public static Pair<IKeyFrame, IKeyFrame> makeTransitionPairFromIdle(TimeModelPart part, TimelineSnapshot destinationStartTime, String partName, Channel channel, TransitionFactoryWithDestination destFactory, int transitionTime) {
        IKeyFrame startKeyFrame = KeyFrame.createIdleKeyFrame(0, channel.getDefaultVector(part));
        IKeyFrame endKeyFrame = destFactory.getDestKeyFrame(part, destinationStartTime, partName, channel, transitionTime);
        return Pair.of(startKeyFrame, endKeyFrame);
    }
}
