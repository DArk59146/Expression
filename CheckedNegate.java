package expression.exceptions;

import expression.AllExpression;
import expression.UnaryMinus;

public class CheckedNegate extends UnaryMinus {

    public CheckedNegate(AllExpression content) {
        super(content);
    }

    private void checkOverflow(int x) {
        if (x == Integer.MIN_VALUE) {
            throw new OverflowException("Overflow for expression: -" + x);
        }
    }

    @Override
    public int evaluate(int x) {
        int value = content.evaluate(x);
        checkOverflow(value);
        return - value;
    }

    @Override
    public double evaluate(double x) {
        throw new UnsupportedOperationException();
    }


    @Override
    public int evaluate(int x, int y, int z) {
        int value = content.evaluate(x, y, z);
        checkOverflow(value);
        return - value;
    }
}
