package by.tms.composite.parser.impl;

import by.tms.composite.entity.ComponentType;
import by.tms.composite.entity.TextComponent;
import by.tms.composite.entity.TextComposite;
import by.tms.composite.parser.TextParser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SentenceParser implements TextParser {
    private static final String SENTENCE_DELIMITER_REGEX = ".+?[.?!…](?=\\s|$)";
    private final TextParser lexemeParser = new LexemeParser();

    @Override
    public TextComposite parse(String paragraphValue) {
        TextComposite paragraphComposite = new TextComposite(ComponentType.PARAGRAPH);
        Pattern sentencePattern = Pattern.compile(SENTENCE_DELIMITER_REGEX);
        Matcher sentences = sentencePattern.matcher(paragraphValue);
        while (sentences.find()) {
            TextComponent sentenceComponent = lexemeParser.parse(sentences.group());
            paragraphComposite.add(sentenceComponent);
        }
        return paragraphComposite;
    }
}