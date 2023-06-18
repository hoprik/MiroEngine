package ru.hoprik.storymod.Story.Engine.AnimationRender.Gecolib3.mclib.math.functions.utility;

import ru.hoprik.storymod.Story.Engine.AnimationRender.Gecolib3.mclib.math.IValue;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Gecolib3.mclib.math.functions.Function;

public class HermiteBlend extends Function {
    public java.util.Random random = new java.util.Random();

    public HermiteBlend(IValue[] values, String name) throws Exception {
        super(values, name);
    }

    public int getRequiredArguments() {
        return 1;
    }

    public double get() {
        double min = Math.ceil(this.getArg(0));
        return Math.floor(3.0 * Math.pow(min, 2.0) - 2.0 * Math.pow(min, 3.0));
    }
}

