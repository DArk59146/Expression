package expression;

public class CountBits extends AbstractUnaryOperation {

    public CountBits(AllExpression content) {
        super(content);
    }

    @Override
    public String getOperation() {
        return "count";
    }

    @Override
    public int evaluate(int x) {
        String  n = Integer.toBinaryString(content.evaluate(x));
        int res = 0;
        for (int i = 0; i < n.length(); i++) {
            if (n.charAt(i) == '1') res++;
        }
        return res;
    }

    @Override
    public double evaluate(double x) {
        return x;
    }


    @Override
    public int evaluate(int x, int y, int z) {
        String n = Integer.toBinaryString(content.evaluate(x, y, z));
        int res = 0;
        for (int i = 0; i < n.length(); i++) {
            if (n.charAt(i) == '1') res++;
        }
        return res;
    }
}
