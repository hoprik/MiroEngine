package ru.hoprik.storymod.Story.Engine.AnimationRender.Gecolib3.mclib.math.functions.classic;

import ru.hoprik.storymod.Story.Engine.AnimationRender.Gecolib3.mclib.math.IValue;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Gecolib3.mclib.math.functions.Function;

public class Sin extends Function {
    public Sin(IValue[] values, String name) throws Exception {
        super(values, name);
    }

    public int getRequiredArguments() {
        return 1;
    }

    public double get() {
        return Math.sin(this.getArg(0));
    }
}

