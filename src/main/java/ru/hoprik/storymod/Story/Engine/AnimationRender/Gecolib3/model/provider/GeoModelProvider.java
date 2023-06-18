package ru.hoprik.storymod.Story.Engine.AnimationRender.Gecolib3.model.provider;

import net.minecraft.resources.ResourceLocation;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Gecolib3.geo.render.built.GeoModel;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Gecolib3.resource.GeckoLibCache;
import ru.hoprik.storymod.StoryMod;

public abstract class GeoModelProvider<T> {
	public double seekTime;
	public double lastGameTickTime;
	public boolean shouldCrashOnMissing = false;

	public GeoModel getModel(ResourceLocation location) {
		return GeckoLibCache.getInstance().getGeoModels().get(location);
	}

	public abstract ResourceLocation getModelResource(T object);

	public abstract ResourceLocation getTextureResource(T object);
}
