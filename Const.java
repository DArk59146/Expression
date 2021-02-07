package expression;


public class Const implements AllExpression {
    private final double value;
    private final boolean isDouble;

    public Const(double value) {
        if (Math.abs(value) > Integer.MAX_VALUE || Math.floor(value) != Math.ceil(value)) {
            isDouble = true;
        } else {
            isDouble = false;
        }
        this.value = value;
    }

    @Override
    public int evaluate(int x) {
        return (int) value;
    }

    @Override
    public String toString() {
        if (isDouble) {
            return Double.toString(value);
        } else {
            return String.valueOf((int) value);
        }
    }

    @Override
    public String toMiniString() {
        return toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Const) {
            return ((Const) obj).value == this.value;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Double.hashCode(value);
    }

    @Override
    public double evaluate(double x) {
        return value;
    }

    @Override
    public int getPriority() {
        return Integer.MAX_VALUE;
    }

    @Override
    public String getOperation() {
        return " ";
    }

    @Override
    public boolean isNeedBrackets() {
        return false;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return (int) value;
    }
}