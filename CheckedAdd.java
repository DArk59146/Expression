package expression.exceptions;


import expression.Add;
import expression.AllExpression;

public class CheckedAdd extends Add {

    public CheckedAdd(AllExpression firstArgument, AllExpression secondArgument) {
        super(firstArgument, secondArgument);
    }

    private void checkOverflow(int x, int y) {
        if ((x > 0 && y >= 0 && x > Integer.MAX_VALUE - y) || (x < 0 && y < Integer.MIN_VALUE - x)) {
            throw new OverflowException("Overflow for expression: " + x + " + " + y);
        }
    }

    @Override
    public int evaluate(int x) {
        int first = firstArgument.evaluate(x);
        int second = secondArgument.evaluate(x);
        checkOverflow(first, second);
        return first + second;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        int first = firstArgument.evaluate(x, y, z);
        int second = secondArgument.evaluate(x, y, z);
        checkOverflow(first, second);
        return first + second;
    }

    @Override
    public double evaluate(double x) {
        throw new UnsupportedOperationException();
    }
}
