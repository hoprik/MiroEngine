package ru.hoprik.storymod.Story.Engine.AnimationRender.Gecolib3.mclib.math.functions.classic;

import ru.hoprik.storymod.Story.Engine.AnimationRender.Gecolib3.mclib.math.IValue;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Gecolib3.mclib.math.functions.Function;

public class Sqrt extends Function {
    public Sqrt(IValue[] values, String name) throws Exception {
        super(values, name);
    }

    public int getRequiredArguments() {
        return 1;
    }

    public double get() {
        return Math.sqrt(this.getArg(0));
    }
}
