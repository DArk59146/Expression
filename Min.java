package expression;

public class Min extends AbstractOperation {

    public Min(AllExpression firstArgument, AllExpression secondArgument) {
        super(firstArgument, secondArgument);
    }

    @Override
    public int evaluate(int x) {
        int first = firstArgument.evaluate(x);
        int second = secondArgument.evaluate(x);
        if (first < second) {
            return first;
        } else {
            return second;
        }
    }

    @Override
    public double evaluate(double x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getPriority() {
        return -3;
    }

    @Override
    public String getOperation() {
        return "min";
    }

    @Override
    public boolean isNeedBrackets() {
        return false;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        int first = firstArgument.evaluate(x, y, z);
        int second = secondArgument.evaluate(x, y, z);
        if (first < second) {
            return first;
        } else {
            return second;
        }
    }
}
