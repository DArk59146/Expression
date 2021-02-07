package expression.parser;

import expression.exceptions.ParseException;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class StringSource implements CharSource {
    private final String data;
    private int mark;
    private int pos;

    public StringSource(final String data) {
        this.data = data;
        mark = 0;
    }

    @Override
    public boolean hasNext() {
        return pos < data.length();
    }

    @Override
    public char next() {
        return data.charAt(pos++);
    }

    @Override
    public ParseException error(final String message) {
        return new ParseException(pos + ": " + message);
    }
}
