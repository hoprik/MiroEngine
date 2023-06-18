package ru.hoprik.storymod.Story.Engine.AnimationRender.Gecolib3.mclib.math;

public class Constant implements IValue {
    private double value;

    public Constant(double value) {
        this.value = value;
    }

    public double get() {
        return this.value;
    }

    public void set(double value) {
        this.value = value;
    }

    public String toString() {
        return String.valueOf(this.value);
    }
}

