/*
 * Copyright (c) 2020.
 * Author: Bernie G. (Gecko)
 */

package ru.hoprik.storymod.Story.Engine.AnimationRender.Gecolib3.core.keyframe;

public class EventKeyFrame<T> {
	private final T eventData;
	private final double startTick;

	public EventKeyFrame(double startTick, T eventData) {
		this.startTick = startTick;
		this.eventData = eventData;
	}

	public T getEventData() {
		return eventData;
	}

	public double getStartTick() {
		return startTick;
	}
}
