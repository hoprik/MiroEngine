package ru.hoprik.storymod.Story.Engine.AnimationRender.Gecolib3.mclib.math;

public class Negative implements IValue{
    public IValue value;

    public Negative(IValue value) {
        this.value = value;
    }

    public double get() {
        return -this.value.get();
    }

    public String toString() {
        return "-" + this.value.toString();
    }
}
