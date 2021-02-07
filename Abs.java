package expression;

import expression.exceptions.OverflowException;

public class Abs extends AbstractUnaryOperation {

    public Abs(AllExpression content) {
        super(content);
    }

    @Override
    public String getOperation() {
        return "abs";
    }

    private void checkOverflow(int x) {
        if (x == Integer.MIN_VALUE) {
            throw new OverflowException("Overflow for expression: abs" + x);
        }
    }

    @Override
    public int evaluate(int x) {
        int value = content.evaluate(x);
        checkOverflow(value);
        if (value < 0) {
            return -value;
        } else {
            return value;
        }
    }


    @Override
    public int evaluate(int x, int y, int z) {
        int value = content.evaluate(x, y, z);
        checkOverflow(value);
        if (value < 0) {
            return -value;
        } else {
            return value;
        }
    }

    @Override
    public double evaluate(double x) {
        throw new UnsupportedOperationException();
    }
}
