package ru.hoprik.storymod.Story.Engine.AnimationRender.Gecolib3.mclib.math.functions.utility;

import ru.hoprik.storymod.Story.Engine.AnimationRender.Gecolib3.mclib.math.IValue;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Gecolib3.mclib.math.functions.Function;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Gecolib3.mclib.utils.Interpolations;

public class LerpRotate extends Function {
    public LerpRotate(IValue[] values, String name) throws Exception {
        super(values, name);
    }

    public int getRequiredArguments() {
        return 3;
    }

    public double get() {
        return Interpolations.lerpYaw(this.getArg(0), this.getArg(1), this.getArg(2));
    }
}

