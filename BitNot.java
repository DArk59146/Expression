package expression;

public class BitNot extends AbstractUnaryOperation {

    public BitNot(AllExpression content) {
        super(content);
    }

    @Override
    public String getOperation() {
        return "~";
    }

    @Override
    public int evaluate(int x) {
        return ~ content.evaluate(x);
    }

    @Override
    public double evaluate(double x) {
        return ~ (int) x;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return ~ content.evaluate(x, y, z);
    }
}
