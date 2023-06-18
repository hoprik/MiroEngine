package ru.hoprik.storymod.Story.Engine.AnimationRender.Gecolib3.mclib.math.functions.rounding;

import ru.hoprik.storymod.Story.Engine.AnimationRender.Gecolib3.mclib.math.IValue;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Gecolib3.mclib.math.functions.Function;

public class Ceil extends Function {
    public Ceil(IValue[] values, String name) throws Exception {
        super(values, name);
    }

    public int getRequiredArguments() {
        return 1;
    }

    public double get() {
        return Math.ceil(this.getArg(0));
    }
}
