package expression;

public class UnaryMinus extends AbstractUnaryOperation {

    public UnaryMinus(AllExpression content) {
        super(content);
    }

    @Override
    public String getOperation() {
        return "-";
    }

    @Override
    public int evaluate(int x) {
        return - content.evaluate(x);
    }

    @Override
    public double evaluate(double x) {
        return - x;
    }


    @Override
    public int evaluate(int x, int y, int z) {
        return - content.evaluate(x, y, z);
    }
}
