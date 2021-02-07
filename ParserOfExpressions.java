package expression.exceptions;

import expression.*;
import expression.parser.BaseParser;
import expression.parser.Parser;
import expression.parser.StringSource;

import java.util.Set;

public class ParserOfExpressions extends BaseParser implements Parser {
    private String unaryOperation;
    private char bufferChar;
    private final Set<String> VARIABLES = Set.of(
            "x",
            "y",
            "z"
    );

    protected ParserOfExpressions(StringSource source) {
        super(source);
    }

    public AllExpression parse(final String expression) {
        return new ParserOfExpressions(new StringSource(expression)).parseExpression(END);
    }

    private AllExpression parseExpression(char end) {
        AllExpression result = parseMinMax();
        skipWhitespace();
        if (test(end)) {
            return result;
        } else {
            if (end != END) {
                throw new ParseException(end + " expected");
            } else {
                throw new ParseException("End of file expected");
            }
        }
    }

    private AllExpression parseMinMax() {
        AllExpression firstArgument = parseBitOrs();
        while (true) {
            if (test('m')) {
                if (test('a')) {
                    if (test('x')) {
                        if (Character.isDigit(ch)) throw new UnsupportedOperationException("max" + ch);
                        firstArgument = new Max(firstArgument, parseBitOrs());
                    } else {
                        throw new UnsupportedOperationException("ma" + ch + "...");
                    }
                } else if (test('i') && test('n')) {
                    if (Character.isDigit(ch)) throw new UnsupportedOperationException("min" + ch);
                    firstArgument = new Min(firstArgument, parseBitOrs());
                } else {
                    throw new UnsupportedOperationException("m...");
                }
            } else {
                return firstArgument;
            }
        }
    }

    private AllExpression parseBitOrs() {
        AllExpression firstArgument = parseBitXORs();
        while (test('|')) {
            firstArgument = new BitOr(firstArgument, parseBitXORs());
        }
        return firstArgument;
    }

    private AllExpression parseBitXORs() {
        AllExpression firstArgument = parseBitAnds();
        while (test('^')) {
            firstArgument = new BitXOR(firstArgument, parseBitAnds());
        }
        return firstArgument;
    }

    private AllExpression parseBitAnds() {
        AllExpression firstArgument = parseNotBitExpression();
        while (test('&')) {
            firstArgument = new BitAnd(firstArgument, parseNotBitExpression());
        }
        return firstArgument;
    }

    private AllExpression parseNotBitExpression() {
        AllExpression firstArgument = parseOperation();
        while (true) {
            if (test('+')) {
                firstArgument = new CheckedAdd(firstArgument, parseOperation());
            } else if (test('-')) {
                firstArgument = new CheckedSubtract(firstArgument, parseOperation());
            } else{
                return firstArgument;
            }
        }
    }

    private AllExpression parseOperation() {
        AllExpression firstArgument = parseOperand();
        while (true) {
            if (test('*')) {
                firstArgument = new CheckedMultiply(firstArgument, parseOperand());
            } else if (test('/')) {
                firstArgument = new CheckedDivide(firstArgument, parseOperand());
            } else {
                return  firstArgument;
            }
        }
    }

    private AllExpression parseOperand() {
        skipWhitespace();
        AllExpression result;
        if (test('(')) {
            result = parseExpression(')');
        } else if (hasUnary()) {
            result = parseUnaryOperation();
        } else if (Character.isLetter(ch)) {
            if (VARIABLES.contains(String.valueOf(ch))) {
                result = new Variable(String.valueOf(ch));
                nextChar();
            } else {
                throw new UnsupportableVariableExpression("Bad variable " + ch);
            }
        } else {
            StringBuilder sb = new StringBuilder();
            if (bufferChar == '-') {
                bufferChar = 0;
                sb.append('-');
            }
            if (Character.getType(ch) == Character.DASH_PUNCTUATION ) {
                sb.append('-');
                nextChar();
            }
            skipWhitespace();
            while (Character.isDigit(ch)) {
                sb.append(ch);
                nextChar();
            }
            result = new Const(Integer.parseInt(sb.toString()));
        }
        skipWhitespace();
        return result;
    }

    private boolean hasUnary() {
        if (test('-')) {
            if (Character.isDigit(ch)) {
                bufferChar = '-';
                return false;
            } else {
                unaryOperation = "-";
                return true;
            }
        } else if (test('~')) {
            unaryOperation = "~";
            return true;
        } else if (test('c')) {
            isIt("count");
            return true;
        } else if (test('a')) {
            isIt("abs");
            return true;
        } else if (test('s')) {
            isIt("sqrt");
            return true;
        }
        return false;
    }

    private boolean isIt(String operation) {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < operation.length(); i++) {
            sb.append(operation.charAt(i));
            if (!test(operation.charAt(i))) {
                throw new UnsupportedOperationException(operation.charAt(0) + sb.toString());
            }
        }
        if (Character.isLetter(ch)) throw new UnsupportedOperationException(operation + ch);
        unaryOperation = operation;
        return true;
    }

    private AllExpression parseUnaryOperation() {
        skipWhitespace();
        if (unaryOperation.equals("-")) {
            return new UnaryMinus(parseOperand());
        } else if (unaryOperation.equals("~")) {
            return new BitNot(parseOperand());
        } else if (unaryOperation.equals("count")) {
            return new CountBits(parseOperand());
        } else if (unaryOperation.equals("abs")) {
            return new Abs(parseOperand());
        } else if (unaryOperation.equals("sqrt")) {
            return new Sqrt(parseOperand());
        } else {
            return null;
        }
    }


    private void skipWhitespace() {
        while (Character.isWhitespace(ch)) nextChar();
    }
}