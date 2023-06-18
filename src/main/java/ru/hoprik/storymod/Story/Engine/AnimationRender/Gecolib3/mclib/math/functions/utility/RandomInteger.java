package ru.hoprik.storymod.Story.Engine.AnimationRender.Gecolib3.mclib.math.functions.utility;

import ru.hoprik.storymod.Story.Engine.AnimationRender.Gecolib3.mclib.math.IValue;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Gecolib3.mclib.math.functions.Function;

import java.util.Random;

public class RandomInteger extends Function {
    public Random random = new Random();

    public RandomInteger(IValue[] values, String name) throws Exception {
        super(values, name);
    }

    public int getRequiredArguments() {
        return 2;
    }

    public double get() {
        double min = Math.ceil(this.getArg(0));
        double max = Math.floor(this.getArg(1));
        return Math.floor(Math.random() * (max - min) + min);
    }
}
