/*
 * Copyright (c) 2020.
 * Author: Bernie G. (Gecko)
 */

package ru.hoprik.storymod.Story.Engine.AnimationRender.Gecolib3.core.keyframe;

import java.util.LinkedList;

import ru.hoprik.storymod.Story.Engine.AnimationRender.Gecolib3.core.processor.IBone;

/**
 * An animation point queue holds a queue of Animation Points which are used in
 * the AnimatedEntityModel to lerp between values
 */
public class AnimationPointQueue extends LinkedList<AnimationPoint> {

	private static final long serialVersionUID = 5472797438476621193L;
	public IBone model;
}