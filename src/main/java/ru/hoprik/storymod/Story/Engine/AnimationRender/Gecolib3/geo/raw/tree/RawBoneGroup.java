package ru.hoprik.storymod.Story.Engine.AnimationRender.Gecolib3.geo.raw.tree;

import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Gecolib3.geo.raw.pojo.Bone;

import java.util.Map;

public class RawBoneGroup {
	public Map<String, RawBoneGroup> children = new Object2ObjectOpenHashMap<>();
	public Bone selfBone;

	public RawBoneGroup(Bone bone) {
		this.selfBone = bone;
	}
}
