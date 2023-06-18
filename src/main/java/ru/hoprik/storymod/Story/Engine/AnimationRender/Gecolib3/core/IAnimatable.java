/*
 * Copyright (c) 2020.
 * Author: Bernie G. (Gecko)
 */
package ru.hoprik.storymod.Story.Engine.AnimationRender.Gecolib3.core;

import ru.hoprik.storymod.Story.Engine.AnimationRender.Gecolib3.core.manager.AnimationData;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Gecolib3.core.manager.AnimationFactory;

/**
 * This interface must be applied to any object that wants to be animated
 */
public interface IAnimatable {
	void registerControllers(AnimationData data);

	AnimationFactory getFactory();
}
