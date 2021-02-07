package expression;

import java.io.*;
import java.nio.charset.Charset;

public class FastScanner implements Closeable {
    private final RequiredSymbols requiredSymbols;
    private final RequiredSymbols lineSeparator = c -> c == '\n' || c == '\r';
    private final BufferedReader reader;
    private int buf;
    private final int mark = 100000000;

    public FastScanner(String filename, RequiredSymbols requiredSymbols) throws IOException {
        reader = new BufferedReader(new FileReader(filename));
        reader.mark(mark);
        buf =  -2;
        this.requiredSymbols = requiredSymbols;
    }

    public FastScanner(InputStream in, Charset charset, RequiredSymbols requiredSymbols) throws IOException {
        reader = new BufferedReader(new InputStreamReader(in, charset));
        reader.mark(mark);
        buf = -2;
        this.requiredSymbols = requiredSymbols;
    }

    public boolean hasNextSequenceInLine() throws IOException {
        int c = buf;
        while (c != -1) {
            if (requiredSymbols.isRequiredSymbol(c)) {
                reader.reset();
                return true;
            } else if (c == '\n' || c == '\r') {
                reader.reset();
                return false;
            }
            c = reader.read();
        }
        return false;
    }

    public void newLine() throws IOException {
        while (buf != '\n' && buf != '\r' && buf != -1) {
            buf = reader.read();
        }
        if (buf == '\r') {
            reader.skip(1);
        }
        buf = reader.read();
        reader.mark(mark);
    }

    public boolean hasNextLine() throws IOException {
        return hasNextExemplar(lineSeparator);
    }

    public boolean hasNextSequence() throws IOException {
        return hasNextExemplar(requiredSymbols);
    }

    private boolean hasNextExemplar(RequiredSymbols req) throws IOException {
        int c = buf;
        if (buf != -1) {
            while (c != -1) {
                if (req.isRequiredSymbol(c)) {
                    reader.reset();
                    return true;
                }
                c = reader.read();
            }
        }
        reader.reset();
        return false;
    }

    private String nextExemplar() throws IOException{
        int c = buf;
        StringBuilder sb = new StringBuilder();
        while (c != -1) {
            while (requiredSymbols.isRequiredSymbol(c)) {
                sb.append((char) c);
                c = reader.read();
            }
            if (sb.length() > 0) {
                buf = c;
                reader.mark(mark);
                return sb.substring(0);
            } else {
                c = reader.read();
            }
        }
        return null;
    }

    public String nextWord() throws IOException {
        return nextExemplar();
    }

    public int nextInt() throws IOException {
        return Integer.parseInt(nextExemplar());
    }

    public int nextHexDec() throws IOException {
        String s = nextExemplar().toLowerCase();
        if ((s.length() > 2) && s.startsWith("0x")) {
            if (s.length() > 9) {
                return Integer.parseUnsignedInt(s.substring(2, 10), 16);
            } else {
                return Integer.parseUnsignedInt(s.substring(2), 16);
            }
        }
        return Integer.parseInt(s);
    }

    public void close() throws IOException {
        reader.close();
    }
}