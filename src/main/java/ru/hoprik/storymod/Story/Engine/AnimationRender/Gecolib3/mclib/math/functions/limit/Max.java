package ru.hoprik.storymod.Story.Engine.AnimationRender.Gecolib3.mclib.math.functions.limit;

import ru.hoprik.storymod.Story.Engine.AnimationRender.Gecolib3.mclib.math.IValue;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Gecolib3.mclib.math.functions.Function;

public class Max extends Function {
    public Max(IValue[] values, String name) throws Exception {
        super(values, name);
    }

    public int getRequiredArguments() {
        return 2;
    }

    public double get() {
        return Math.max(this.getArg(0), this.getArg(1));
    }
}

