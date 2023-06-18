package ru.hoprik.storymod.Story.Engine.AnimationRender.Gecolib3.core.manager;

import ru.hoprik.storymod.Story.Engine.AnimationRender.Gecolib3.core.IAnimatable;

/**
 * AnimationFactory implementation for instantiated objects such as Entities or BlockEntities. Returns a single {@link AnimationData} instance per factory.
 */
public class InstancedAnimationFactory extends AnimationFactory {
	private AnimationData animationData;

	/**
	 * Deprecated, use {@code GeckolibUtil#createFactory(IAnimatable)}
	 *
	 * @param animatable The animatable object the factory is for
	 */
	public InstancedAnimationFactory(IAnimatable animatable) {
		super(animatable);
	}


	@Override
	public AnimationData getOrCreateAnimationData(int uniqueID) {
		if (this.animationData == null) {
			this.animationData = new AnimationData();

			this.animatable.registerControllers(this.animationData);
		}

		return this.animationData;
	}
}