package expression.exceptions;

import expression.TripleExpression;
import expression.parser.BaseParser;
import expression.parser.StringSource;

public class ExpressionParser extends BaseParser implements Parser {

    public ExpressionParser() {
        super(new StringSource(""));
    }

    @Override
    public TripleExpression parse(String expression) {
        return new ParserOfExpressions(new StringSource(expression)).parse(expression);
    }
}