package ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.animation.component;

import com.mojang.math.Vector3f;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.TimeCore;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.animation.AnimationStarter;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.animation.calculation.KeyFrameInterpolator;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.animation.util.AnimationUtils;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.animation.watcher.TimelineSnapshot;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.api.animation.*;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.api.client.render.model.ITimeModel;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.api.util.holder.Pair;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.client.render.model.TimeModelPart;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class Transition extends Animation {
    private final int transitionLength;
    private final String name;
    private final Animation destAnimation;
    private final ResourceLocation id;
    private List<BoneOption> options = new ArrayList<>();

    private Transition(int transitionLength, String name, Animation destAnimation) {
        this.transitionLength = transitionLength;
        this.name = name;
        this.id = new ResourceLocation(TimeCore.MODID, "internal/" + getName());
        this.destAnimation = destAnimation;
    }

    private static Transition createFromIdleState(AnimationStarter.AnimationData destinationData, ITimeModel model, int transitionTime) {
        Animation dest = destinationData.getAnimation();
        Transition transition = new Transition(transitionTime, "idle_to_" + dest.getName(), dest);

        TransitionFactoryWithDestination destFactory = dest.getTransitionFactory().withRequiredDestination();
        TimelineSnapshot destinationStartTime = TimelineSnapshot.createForStartTime(destinationData);

        dest.forEachBone(name -> {
            TimeModelPart part = model.tryGetPart(name);
            if (part != null) {
                Pair<IKeyFrame, IKeyFrame> rotations = TransitionFactory.makeTransitionPairFromIdle(part, destinationStartTime, name, Channel.ROTATION, destFactory, transitionTime);
                Pair<IKeyFrame, IKeyFrame> translations = TransitionFactory.makeTransitionPairFromIdle(part, destinationStartTime, name, Channel.TRANSLATION, destFactory, transitionTime);
                Pair<IKeyFrame, IKeyFrame> scales = TransitionFactory.makeTransitionPairFromIdle(part, destinationStartTime, name, Channel.SCALE, destFactory, transitionTime);
                transition.options.add(new BoneOption(name, rotations, translations, scales));
            }
        });

        return transition;
    }

    public static Animation createForServer(Animation source, Animation dest, int transitionTime) {
        return new Transition(transitionTime, source.getName() + "_to_" + dest.getName(), dest);
    }

    public static Animation create(Animation source, AnimationStarter.AnimationData dest, ITimeModel model, int existingTime, int transitionTime) {
        TransitionFactory sourceTFactory = source.getTransitionFactory();

        List<BoneOption> options = sourceTFactory.createTransitionBones(dest, model, existingTime, transitionTime);
        if (options == null) {
            return createFromIdleState(dest, model, transitionTime);
        }

        Animation destAnimation = dest.getAnimation();
        Transition transition = new Transition(transitionTime, source.getName() + "_to_" + destAnimation.getName(), destAnimation);
        transition.options = options;

        return transition;
    }

    @Override
    public void apply(ITimeModel model, ILayer layer, int existingTime) {
        if (options != null) {
            if (existingTime <= transitionLength) {
                options.forEach(boneOption -> {
                    TimeModelPart piece = model.tryGetPart(boneOption.name);

                    if (piece != null) {
                        boneOption.apply(piece, layer, existingTime);
                    }
                });
            }
        }
    }

    @Override
    public void forEachBone(Consumer<String> action) {
        options.forEach(transitionBoneOption -> action.accept(transitionBoneOption.name));
    }

    @Override
    public LoopMode getLoopMode() {
        return LoopMode.DO_NOT_LOOP;
    }

    @Override
    public @NotNull TransitionFactory getTransitionFactory() {
        return new InternalTransitionFactory(this);
    }

    @Override
    public int getLength() {
        return transitionLength;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public ResourceLocation getId() {
        return id;
    }

    public Animation getDestination() {
        return destAnimation;
    }

    @Override
    public String toString() {
        return "Transition{" +
                "location='" + name + '\'' +
                ", id=" + id +
                ", transitionLength=" + transitionLength +
                ", destAnimation=" + destAnimation +
                '}';
    }

    private static class InternalTransitionFactory extends TransitionFactory {

        public InternalTransitionFactory(Transition source) {
            super(source);
        }

        private static IKeyFrame calcStartKeyFrame(@Nullable Pair<IKeyFrame, IKeyFrame> sourceFrames, Vector3f modelIdleVec, int existingTime) {
            if (sourceFrames != null) {
                Vector3f vec = KeyFrameInterpolator.interpolateLinear(sourceFrames.left(), sourceFrames.right(), existingTime);
                return new KeyFrame(0, vec);
            }

            return KeyFrame.createIdleKeyFrame(0, modelIdleVec);
        }

        private static Pair<IKeyFrame, IKeyFrame> makeTransitionPair(TimeModelPart part, TimelineSnapshot destinationStartTime, BoneOption option, Channel channel, TransitionFactoryWithDestination destFactory, int existingTime, int transitionTime) {
            IKeyFrame startKeyFrame = calcStartKeyFrame(option.getKeyFrames(channel), channel.getDefaultVector(part), existingTime);
            IKeyFrame endKeyFrame = destFactory.getDestKeyFrame(part, destinationStartTime, option.getName(), channel, transitionTime);
            return Pair.of(startKeyFrame, endKeyFrame);
        }

        @Override
        public @Nullable List<BoneOption> createTransitionBones(AnimationStarter.AnimationData dest, ITimeModel model, int existingTime, int transitionTime) {
            Transition source = getSourceTyped();
            if (source.options == null || source.options.isEmpty()) {
                return null;
            }

            TransitionFactoryWithDestination destFactory = dest.getAnimation().getTransitionFactory().withRequiredDestination();
            TimelineSnapshot destinationStartTime = TimelineSnapshot.createForStartTime(dest);

            List<BoneOption> transitionBones = new ArrayList<>();
            source.options.forEach(bone -> {
                TimeModelPart part = model.tryGetPart(bone.name);
                if (part != null) {
                    Pair<IKeyFrame, IKeyFrame> rotations = makeTransitionPair(part, destinationStartTime, bone, Channel.ROTATION, destFactory, existingTime, transitionTime);
                    Pair<IKeyFrame, IKeyFrame> translations = makeTransitionPair(part, destinationStartTime, bone, Channel.TRANSLATION, destFactory, existingTime, transitionTime);
                    Pair<IKeyFrame, IKeyFrame> scales = makeTransitionPair(part, destinationStartTime, bone, Channel.SCALE, destFactory, existingTime, transitionTime);
                    transitionBones.add(new BoneOption(bone.name, rotations, translations, scales));
                }
            });

            return transitionBones;
        }
    }

    public static class BoneOption {
        private final String name;

        private final Pair<IKeyFrame, IKeyFrame> rotations;
        private final Pair<IKeyFrame, IKeyFrame> positions;
        private final Pair<IKeyFrame, IKeyFrame> scales;

        public BoneOption(String boneName, Pair<IKeyFrame, IKeyFrame> rotations, Pair<IKeyFrame, IKeyFrame> positions, Pair<IKeyFrame, IKeyFrame> scales) {
            this.name = boneName;
            this.rotations = rotations;
            this.positions = positions;
            this.scales = scales;
        }

        public void apply(TimeModelPart piece, ILayer layer, int existingTime) {
            Vector3f interpolated = KeyFrameInterpolator.interpolateLinear(rotations.left(), rotations.right(), existingTime);
            AnimationUtils.applyRotation(piece, layer, interpolated);

            interpolated = KeyFrameInterpolator.interpolateLinear(positions.left(), positions.right(), existingTime);
            AnimationUtils.applyOffset(piece, layer, interpolated);

            interpolated = KeyFrameInterpolator.interpolateLinear(scales.left(), scales.right(), existingTime);
            AnimationUtils.applyScale(piece, layer, interpolated);
        }

        public String getName() {
            return name;
        }

        public Pair<IKeyFrame, IKeyFrame> getKeyFrames(Channel channel) {
            if (channel == Channel.ROTATION) {
                return rotations;
            } else if (channel == Channel.TRANSLATION) {
                return positions;
            } else if (channel == Channel.SCALE) {
                return scales;
            }

            throw new IllegalArgumentException("Unknown channel: " + channel);
        }
    }
}