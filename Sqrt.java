package expression;

import expression.exceptions.OverflowException;

public class Sqrt extends AbstractUnaryOperation {

    public Sqrt(AllExpression content) {
        super(content);
    }

    @Override
    public String getOperation() {
        return "sqrt";
    }

    private void checkBadArgument(int x) {
        if (x < 0) {
            throw new OverflowException("Bad argument for sqrt: " + x);
        }
    }

    @Override
    public int evaluate(int x) {
        int value = content.evaluate(x);
        checkBadArgument(value);
        return (int) Math.sqrt(value);
    }


    @Override
    public int evaluate(int x, int y, int z) {
        int value = content.evaluate(x, y, z);
        checkBadArgument(value);
        return (int) Math.sqrt(value);
    }

    @Override
    public double evaluate(double x) {
        throw new UnsupportedOperationException();
    }
}
