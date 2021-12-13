package by.tms.composite.reader.impl;

import by.tms.composite.exception.TextException;
import by.tms.composite.reader.TextReader;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TextReaderImpl implements TextReader {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public String readText(String filename) throws TextException {
        if (getClass().getClassLoader().getResource(filename) == null) {
            logger.log(Level.ERROR,"File {} doesn't exist", filename);
            throw new TextException("File " + filename + " doesn't exist");
        }
        String fileText;
        try {
            Path pathToFile = Paths.get(getClass().getClassLoader().getResource(filename).toURI());
            fileText = Files.readString(pathToFile);
        } catch (IOException | URISyntaxException exception) {
            logger.log(Level.ERROR,"Error of reading file \"{}\": {}", filename, exception);
            throw new TextException("Error of reading file \"" + filename + "\": ", exception);
        }
        return fileText;
    }
}