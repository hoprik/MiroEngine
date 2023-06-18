package ru.hoprik.storymod.Story.Engine.AnimationRender.Gecolib3.mclib.math.functions.utility;

import ru.hoprik.storymod.Story.Engine.AnimationRender.Gecolib3.mclib.math.IValue;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Gecolib3.mclib.math.functions.Function;

import java.util.Random;

public class DieRoll extends Function {
    public java.util.Random random = new Random();

    public DieRoll(IValue[] values, String name) throws Exception {
        super(values, name);
    }

    public int getRequiredArguments() {
        return 3;
    }

    public double get() {
        double i = 0.0;

        double total;
        for(total = 0.0; i < this.getArg(0); total += Math.random() * (this.getArg(2) - this.getArg(2))) {
        }

        return total;
    }
}

