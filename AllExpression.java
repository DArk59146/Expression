package expression;

public interface AllExpression extends Expression, DoubleExpression, TripleExpression {
    int getPriority();
    String getOperation();
    boolean isNeedBrackets();
}
