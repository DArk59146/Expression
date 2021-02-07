package expression.exceptions;


import expression.AllExpression;
import expression.Subtract;

public class CheckedSubtract extends Subtract {

    public CheckedSubtract(AllExpression firstArgument, AllExpression secondArgument) {
        super(firstArgument, secondArgument);
    }

    private void checkOverflow(int x, int y) {
        if ((y > 0 && x < Integer.MIN_VALUE + y) || (y < 0 && x > Integer.MAX_VALUE + y)) {
            throw new OverflowException("Overflow for expression: " + x + " - " + y);
        }
    }

    @Override
    public int evaluate(int x) {
        int first = firstArgument.evaluate(x);
        int second = secondArgument.evaluate(x);
        checkOverflow(first, second);
        return first - second;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        int first = firstArgument.evaluate(x, y, z);
        int second = secondArgument.evaluate(x, y, z);
        checkOverflow(first, second);
        return first - second;
    }

    @Override
    public double evaluate(double x) {
        throw new UnsupportedOperationException();
    }
}
