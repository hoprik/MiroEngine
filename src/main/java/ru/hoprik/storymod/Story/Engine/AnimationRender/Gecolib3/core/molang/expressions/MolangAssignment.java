package ru.hoprik.storymod.Story.Engine.AnimationRender.Gecolib3.core.molang.expressions;

import ru.hoprik.storymod.Story.Engine.AnimationRender.Gecolib3.mclib.math.IValue;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Gecolib3.mclib.math.Variable;

import ru.hoprik.storymod.Story.Engine.AnimationRender.Gecolib3.core.molang.MolangParser;

public class MolangAssignment extends MolangExpression {
	public Variable variable;
	public IValue expression;

	public MolangAssignment(MolangParser context, Variable variable, IValue expression) {
		super(context);

		this.variable = variable;
		this.expression = expression;
	}

	@Override
	public double get() {
		double value = this.expression.get();

		this.variable.set(value);

		return value;
	}

	@Override
	public String toString() {
		return this.variable.getName() + " = " + this.expression.toString();
	}
}