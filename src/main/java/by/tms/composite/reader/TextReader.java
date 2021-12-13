package by.tms.composite.reader;

import by.tms.composite.exception.TextException;

public interface TextReader {
    String readText(String filename) throws TextException;
}