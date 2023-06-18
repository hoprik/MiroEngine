package ru.hoprik.storymod.Story.Engine.AnimationRender.Gecolib3.mclib.math.functions.rounding;

import ru.hoprik.storymod.Story.Engine.AnimationRender.Gecolib3.mclib.math.IValue;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Gecolib3.mclib.math.functions.Function;

public class Round extends Function {
    public Round(IValue[] values, String name) throws Exception {
        super(values, name);
    }

    public int getRequiredArguments() {
        return 1;
    }

    public double get() {
        return (double)Math.round(this.getArg(0));
    }
}

