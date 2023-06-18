package ru.hoprik.storymod.Story.Engine.AnimationRender.Gecolib3.mclib.math.functions.utility;

import ru.hoprik.storymod.Story.Engine.AnimationRender.Gecolib3.mclib.math.IValue;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Gecolib3.mclib.math.functions.Function;

public class Random extends Function {
    public java.util.Random random = new java.util.Random();

    public Random(IValue[] values, String name) throws Exception {
        super(values, name);
    }

    public double get() {
        double random = 0.0;
        if (this.args.length >= 3) {
            this.random.setSeed((long)this.getArg(2));
            random = this.random.nextDouble();
        } else {
            random = Math.random();
        }

        if (this.args.length >= 2) {
            double a = this.getArg(0);
            double b = this.getArg(1);
            double min = Math.min(a, b);
            double max = Math.max(a, b);
            random = random * (max - min) + min;
        } else if (this.args.length >= 1) {
            random *= this.getArg(0);
        }

        return random;
    }
}

