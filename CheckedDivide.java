package expression.exceptions;


import expression.AllExpression;
import expression.Divide;

public class CheckedDivide extends Divide {

    public CheckedDivide(AllExpression firstArgument, AllExpression secondArgument) {
        super(firstArgument, secondArgument);
    }

    private void checkOverflow(int x, int y) {
        if (y == -1 && x == Integer.MIN_VALUE) {
            throw new OverflowException("Overflow for expression: " + x + " / " + y);
        }
    }

    private void checkDivisionByZero(int second) {
        if (second == 0) throw new DBZException("Division by zero");
    }

    @Override
    public int evaluate(int x) {
        int first = firstArgument.evaluate(x);
        int second = secondArgument.evaluate(x);
        checkDivisionByZero(second);
        checkOverflow(first, second);
        return first / second;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        int first = firstArgument.evaluate(x, y, z);
        int second = secondArgument.evaluate(x, y, z);
        checkDivisionByZero(second);
        checkOverflow(first, second);
        return first / second;
    }

    @Override
    public double evaluate(double x) {
        throw new UnsupportedOperationException();
    }
}