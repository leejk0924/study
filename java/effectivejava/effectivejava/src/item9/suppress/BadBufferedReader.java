package item9.suppress;

import java.io.*;
import java.util.stream.Stream;

public class BadBufferedReader extends BufferedReader {
    public BadBufferedReader(Reader in, int sz) {
        super(in, sz);
    }

    @Override
    public void close() throws IOException {
        throw new StreamCorruptedException();
    }

    @Override
    public String readLine() throws IOException {
        throw new CharConversionException();
    }
}
