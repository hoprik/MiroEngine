package ru.hoprik.storymod.Story.Engine.AnimationRender.Gecolib3.mclib.math.functions.rounding;

import ru.hoprik.storymod.Story.Engine.AnimationRender.Gecolib3.mclib.math.IValue;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Gecolib3.mclib.math.functions.Function;

public class Trunc extends Function {
    public Trunc(IValue[] values, String name) throws Exception {
        super(values, name);
    }

    public int getRequiredArguments() {
        return 1;
    }

    public double get() {
        double value = this.getArg(0);
        return value < 0.0 ? Math.ceil(value) : Math.floor(value);
    }
}

