package expression;


public abstract class AbstractOperation implements AllExpression {
    protected final AllExpression firstArgument;
    protected final AllExpression secondArgument;

    public AbstractOperation(AllExpression firstArgument, AllExpression secondArgument) {
        this.firstArgument = firstArgument;
        this.secondArgument = secondArgument;
    }

    @Override
    public String toString() {
        return "(" + firstArgument.toString() + " " + this.getOperation() + " " + secondArgument.toString() + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj instanceof Expression) {
            Expression that = (Expression) obj;
            return this.toString().equals(that.toString());
        }
        return false;
    }

    private String setBrackets(boolean needBrackets, AllExpression operand) {
        if (needBrackets) {
            return '(' + operand.toMiniString() + ')';
        } else {
            return operand.toMiniString();
        }
    }

    @Override
    public String toMiniString() {
        return setBrackets(firstArgument.getPriority() < this.getPriority(), firstArgument) + ' ' +
                this.getOperation() + ' ' + setBrackets(
                        this.getPriority() == secondArgument.getPriority() && (this.isNeedBrackets() || secondArgument.isNeedBrackets()) ||
                                secondArgument.getPriority() < this.getPriority(), secondArgument);
    }

    @Override
    public int hashCode() {
        return (firstArgument.hashCode() * 21 + secondArgument.hashCode()) * 28 - this.getOperation().hashCode() * 59;
    }
}