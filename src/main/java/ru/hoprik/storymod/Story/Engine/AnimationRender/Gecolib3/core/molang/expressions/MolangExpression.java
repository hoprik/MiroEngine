package ru.hoprik.storymod.Story.Engine.AnimationRender.Gecolib3.core.molang.expressions;

import ru.hoprik.storymod.Story.Engine.AnimationRender.Gecolib3.mclib.math.Constant;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Gecolib3.mclib.math.Operation;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;

import ru.hoprik.storymod.Story.Engine.AnimationRender.Gecolib3.core.molang.MolangParser;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Gecolib3.mclib.math.IValue;

public abstract class MolangExpression implements IValue {
	public MolangParser context;

	public static boolean isZero(MolangExpression expression) {
		return isConstant(expression, 0);
	}

	public static boolean isOne(MolangExpression expression) {
		return isConstant(expression, 1);
	}

	public static boolean isConstant(MolangExpression expression, double x) {
		if (expression instanceof MolangValue) {
			MolangValue value = (MolangValue) expression;
			return value.value instanceof Constant && Operation.equals(value.value.get(), x);
		}

		return false;
	}

	public static boolean isExpressionConstant(MolangExpression expression) {
		if (expression instanceof MolangValue) {
			MolangValue value = (MolangValue) expression;
			return value.value instanceof Constant;
		}

		return false;
	}

	public MolangExpression(MolangParser context) {
		this.context = context;
	}

	public JsonElement toJson() {
		return new JsonPrimitive(this.toString());
	}
}
