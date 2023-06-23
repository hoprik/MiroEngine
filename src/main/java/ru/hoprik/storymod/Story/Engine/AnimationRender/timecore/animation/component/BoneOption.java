package ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.animation.component;

import com.mojang.math.Vector3f;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.animation.calculation.KeyFrameInterpolator;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.animation.util.AnimationUtils;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.api.animation.Animation;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.api.animation.Channel;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.api.animation.ILayer;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.client.render.model.TimeModelPart;

import java.util.List;

public class BoneOption {
    private final String boneName;
    /**
     * Immutable rotation keyframe list
     */
    private final List<IKeyFrame> rotations;
    /**
     * Immutable translation keyframe list
     */
    private final List<IKeyFrame> translations;
    /**
     * Immutable scale keyframe list
     */
    private final List<IKeyFrame> scales;

    public BoneOption(String boneName, List<IKeyFrame> rotations, List<IKeyFrame> translations, List<IKeyFrame> scales) {
        this.boneName = boneName;
        this.rotations = rotations;
        this.translations = translations;
        this.scales = scales;
    }

    public void apply(Animation animation, ILayer layer, TimeModelPart piece, int existingTime) {
        Vector3f rotateVec = KeyFrameInterpolator.findInterpolationVec(animation, rotations, existingTime);
        if (rotateVec != null) {
            AnimationUtils.applyRotation(piece, layer, rotateVec);
        }

        Vector3f posVec = KeyFrameInterpolator.findInterpolationVec(animation, translations, existingTime);
        if (posVec != null) {
            AnimationUtils.applyOffset(piece, layer, posVec);
        }

        Vector3f scaleVec = KeyFrameInterpolator.findInterpolationVec(animation, scales, existingTime);
        if (scaleVec != null) {
            AnimationUtils.applyScale(piece, layer, scaleVec);
        }
    }

    public String getName() {
        return boneName;
    }

    public List<IKeyFrame> getKeyFrames(Channel channel) {
        if (channel == Channel.ROTATION) {
            return rotations;
        } else if (channel == Channel.TRANSLATION) {
            return translations;
        } else if (channel == Channel.SCALE) {
            return scales;
        }

        throw new IllegalArgumentException("Unknown channel: " + channel);
    }
}