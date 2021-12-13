package by.tms.composite.parser.impl;

import by.tms.composite.entity.ComponentType;
import by.tms.composite.entity.TextComponent;
import by.tms.composite.entity.TextComposite;
import by.tms.composite.parser.TextParser;

public class ParagraphParser implements TextParser {
    private static final String PARAGRAPH_DELIMITER_REGEX = "\\r\\n";
    private final TextParser sentenceParser = new SentenceParser();

    @Override
    public TextComposite parse(String textValue) {
        TextComposite textComposite = new TextComposite(ComponentType.TEXT);
        String[] paragraphs = textValue.split(PARAGRAPH_DELIMITER_REGEX);
        for (String paragraph : paragraphs) {
            TextComponent paragraphComponent = sentenceParser.parse(paragraph);
            textComposite.add(paragraphComponent);
        }
        return textComposite;
    }
}