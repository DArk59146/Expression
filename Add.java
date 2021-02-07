package expression;


public class Add extends AbstractOperation {

    public Add(AllExpression firstArgument, AllExpression secondArgument) {
        super(firstArgument, secondArgument);
    }

    @Override
    public int evaluate(int x) {
        return firstArgument.evaluate(x) + secondArgument.evaluate(x);
    }

    @Override
    public double evaluate(double x) {
        return firstArgument.evaluate(x) + secondArgument.evaluate(x);
    }

    @Override
    public int getPriority() {
        return 1;
    }

    @Override
    public String getOperation() {
        return "+";
    }

    @Override
    public boolean isNeedBrackets() {
        return false;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return firstArgument.evaluate(x, y, z) + secondArgument.evaluate(x, y, z);
    }
}