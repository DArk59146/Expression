package expression.exceptions;


import expression.AllExpression;
import expression.Multiply;

public class CheckedMultiply extends Multiply {
    public CheckedMultiply(AllExpression firstArgument, AllExpression secondArgument) {
        super(firstArgument, secondArgument);
    }


    private void checkOverflow(int x, int y) {
        int max = Integer.MIN_VALUE;
        if (Integer.signum(x) == Integer.signum(y)) max = Integer.MAX_VALUE;
        int buf = x;
        if (x < y) {
            x = y;
            y = buf;
        }
        if (x == -1 && y == Integer.MIN_VALUE || x != 0 && x != -1 && ((y > 0 && y > max / x) || (y < 0 && y < max / x))) {
            throw new OverflowException("Overflow for expression: " + x + " * " + y);
        }
    }

    @Override
    public int evaluate(int x) {
        int first = firstArgument.evaluate(x);
        int second = secondArgument.evaluate(x);
        checkOverflow(first, second);
        return first * second;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        int first = firstArgument.evaluate(x, y, z);
        int second = secondArgument.evaluate(x, y, z);
        checkOverflow(first, second);
        return first * second;
    }

    @Override
    public double evaluate(double x) {
        throw new UnsupportedOperationException();
    }
}
