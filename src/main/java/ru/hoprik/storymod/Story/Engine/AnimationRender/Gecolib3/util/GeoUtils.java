package ru.hoprik.storymod.Story.Engine.AnimationRender.Gecolib3.util;

import net.minecraft.client.model.geom.ModelPart;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Gecolib3.core.processor.IBone;

public class GeoUtils {
	public static void copyRotations(ModelPart from, IBone to) {
		to.setRotationX(-from.xRot);
		to.setRotationY(-from.yRot);
		to.setRotationZ(from.zRot);
	}
}
