package expression;

public abstract class AbstractUnaryOperation implements AllExpression {
    protected final AllExpression content;

    protected AbstractUnaryOperation(AllExpression content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return this.getOperation() + content.toString();
    }

    @Override
    public String toMiniString() {
        return this.getOperation() + content.toString();
    }

    @Override
    public boolean isNeedBrackets() {
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof AllExpression) {
            return content.equals(obj);
        } else {
            return false;
        }
    }

    @Override
    public int getPriority() {
        return Integer.MAX_VALUE;
    }

    @Override
    public int hashCode() {
        return this.getOperation().hashCode() * 11 + content.hashCode();
    }
}
