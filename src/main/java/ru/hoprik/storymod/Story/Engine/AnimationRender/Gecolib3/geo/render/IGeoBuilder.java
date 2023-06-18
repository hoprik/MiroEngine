package ru.hoprik.storymod.Story.Engine.AnimationRender.Gecolib3.geo.render;

import ru.hoprik.storymod.Story.Engine.AnimationRender.Gecolib3.geo.raw.pojo.ModelProperties;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Gecolib3.geo.raw.tree.RawBoneGroup;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Gecolib3.geo.raw.tree.RawGeometryTree;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Gecolib3.geo.render.built.GeoBone;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Gecolib3.geo.render.built.GeoModel;

public interface IGeoBuilder {
    GeoModel constructGeoModel(RawGeometryTree geometryTree);

    GeoBone constructBone(RawBoneGroup bone, ModelProperties properties, GeoBone parent);
}
