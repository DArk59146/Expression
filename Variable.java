package expression;

public class Variable implements AllExpression {
    private final String name;

    public Variable(String name) {
        this.name = name;
    }

    @Override
    public int evaluate(int x) {
        return x;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public String toMiniString() {
        return toString();
    }

    @Override
    public int hashCode() {
        return Character.hashCode(name.charAt(0));
    }

    @Override
    public double evaluate(double x) {
        return x;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Variable) {
            return ((Variable) obj).name == this.name;
        } else {
            return false;
        }
    }

    @Override
    public int getPriority() {
        return Integer.MAX_VALUE;
    }

    @Override
    public String  getOperation() {
        return " ";
    }

    @Override
    public boolean isNeedBrackets() {
        return false;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        if (name.equals("x")) return x;
        else if (name.equals("y")) return y;
        else return z;
    }
}
