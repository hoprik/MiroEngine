package ru.hoprik.storymod.Story.Engine.AnimationRender.Gecolib3.file;

import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.resources.ResourceLocation;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Gecolib3.GeckoLib;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Gecolib3.geo.exception.GeckoLibException;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Gecolib3.geo.raw.pojo.Converter;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Gecolib3.geo.raw.pojo.FormatVersion;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Gecolib3.geo.raw.pojo.RawGeoModel;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Gecolib3.geo.raw.tree.RawGeometryTree;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Gecolib3.geo.render.GeoBuilder;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Gecolib3.geo.render.built.GeoModel;

public class GeoModelLoader {
	public GeoModel loadModel(ResourceManager resourceManager, ResourceLocation location) {
		try {
			// Deserialize from json into basic json objects, bones are still stored as a
			// flat list
			RawGeoModel rawModel = Converter
 					.fromJsonString(AnimationFileLoader.getResourceAsString(location, resourceManager));
			if (rawModel.getFormatVersion() != FormatVersion.VERSION_1_12_0) {
				throw new GeckoLibException(location, "Wrong geometry json version, expected 1.12.0");
			}

			// Parse the flat list of bones into a raw hierarchical tree of "BoneGroup"s
			RawGeometryTree rawGeometryTree = RawGeometryTree.parseHierarchy(rawModel);

			// Build the quads and cubes from the raw tree into a built and ready to be
			// rendered GeoModel
			return GeoBuilder.getGeoBuilder(location.getNamespace()).constructGeoModel(rawGeometryTree);
		} catch (Exception e) {
			GeckoLib.LOGGER.error(String.format("Error parsing %S", location), e);
			throw (new RuntimeException(e));
		}
	}
}
