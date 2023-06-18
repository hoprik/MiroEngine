package ru.hoprik.storymod.Story.Engine.AnimationRender.Gecolib3.mclib.math.functions.limit;

import ru.hoprik.storymod.Story.Engine.AnimationRender.Gecolib3.mclib.math.IValue;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Gecolib3.mclib.math.functions.Function;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Gecolib3.mclib.utils.MathUtils;

public class Clamp extends Function {
    public Clamp(IValue[] values, String name) throws Exception {
        super(values, name);
    }

    public int getRequiredArguments() {
        return 3;
    }

    public double get() {
        return MathUtils.clamp(this.getArg(0), this.getArg(1), this.getArg(2));
    }
}

