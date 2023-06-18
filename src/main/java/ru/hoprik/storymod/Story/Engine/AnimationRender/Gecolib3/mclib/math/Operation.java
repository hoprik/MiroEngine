package ru.hoprik.storymod.Story.Engine.AnimationRender.Gecolib3.mclib.math;

import java.util.HashSet;
import java.util.Set;

public enum Operation {
    ADD("+", 1) {
        public double calculate(double a, double b) {
            return a + b;
        }
    },
    SUB("-", 1) {
        public double calculate(double a, double b) {
            return a - b;
        }
    },
    MUL("*", 2) {
        public double calculate(double a, double b) {
            return a * b;
        }
    },
    DIV("/", 2) {
        public double calculate(double a, double b) {
            return a / (b == 0.0 ? 1.0 : b);
        }
    },
    MOD("%", 2) {
        public double calculate(double a, double b) {
            return a % b;
        }
    },
    POW("^", 3) {
        public double calculate(double a, double b) {
            return Math.pow(a, b);
        }
    },
    AND("&&", 5) {
        public double calculate(double a, double b) {
            return a != 0.0 && b != 0.0 ? 1.0 : 0.0;
        }
    },
    OR("||", 5) {
        public double calculate(double a, double b) {
            return a == 0.0 && b == 0.0 ? 0.0 : 1.0;
        }
    },
    LESS("<", 5) {
        public double calculate(double a, double b) {
            return a < b ? 1.0 : 0.0;
        }
    },
    LESS_THAN("<=", 5) {
        public double calculate(double a, double b) {
            return a <= b ? 1.0 : 0.0;
        }
    },
    GREATER_THAN(">=", 5) {
        public double calculate(double a, double b) {
            return a >= b ? 1.0 : 0.0;
        }
    },
    GREATER(">", 5) {
        public double calculate(double a, double b) {
            return a > b ? 1.0 : 0.0;
        }
    },
    EQUALS("==", 5) {
        public double calculate(double a, double b) {
            return equals(a, b) ? 1.0 : 0.0;
        }
    },
    NOT_EQUALS("!=", 5) {
        public double calculate(double a, double b) {
            return !equals(a, b) ? 1.0 : 0.0;
        }
    };

    public static final Set<String> OPERATORS = new HashSet();
    public final String sign;
    public final int value;

    public static boolean equals(double a, double b) {
        return Math.abs(a - b) < 1.0E-5;
    }

    private Operation(String sign, int value) {
        this.sign = sign;
        this.value = value;
    }

    public abstract double calculate(double var1, double var3);

    static {
        Operation[] var0 = values();
        int var1 = var0.length;

        for(int var2 = 0; var2 < var1; ++var2) {
            Operation op = var0[var2];
            OPERATORS.add(op.sign);
        }

    }
}
