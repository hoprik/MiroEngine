/*
 * Copyright (c) 2020.
 * Author: Bernie G. (Gecko)
 */

package ru.hoprik.storymod.Story.Engine.AnimationRender.Gecolib3.core.keyframe;

import ru.hoprik.storymod.Story.Engine.AnimationRender.Gecolib3.mclib.math.IValue;

public class BoneAnimation {
	public final String boneName;
	
	public VectorKeyFrameList<KeyFrame<IValue>> rotationKeyFrames;
	public VectorKeyFrameList<KeyFrame<IValue>> positionKeyFrames;
	public VectorKeyFrameList<KeyFrame<IValue>> scaleKeyFrames;
	
	public BoneAnimation(String boneName) {
		this.boneName = boneName;
	}
}
