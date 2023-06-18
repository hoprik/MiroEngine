package ru.hoprik.storymod.Story.Engine.AnimationRender.Gecolib3.core;


import ru.hoprik.storymod.Story.Engine.AnimationRender.Gecolib3.mclib.math.IValue;

public class ConstantValue implements IValue {
	private final double value;

	public ConstantValue(double value) {
		this.value = value;
	}

	@Override
	public double get() {
		return value;
	}

	public static ConstantValue fromDouble(double d) {
		return new ConstantValue(d);
	}

	public static ConstantValue fromFloat(float d) {
		return new ConstantValue(d);
	}

	public static ConstantValue parseDouble(String s) {
		return new ConstantValue(Double.parseDouble(s));
	}

	public static ConstantValue parseFloat(String s) {
		return new ConstantValue(Float.parseFloat(s));
	}

	public static ConstantValue subtract(IValue first, IValue second) {
		return ConstantValue.fromDouble(first.get() - second.get());
	}

}
