package by.tms.composite.parser.impl;

import by.tms.composite.entity.ComponentType;
import by.tms.composite.entity.TextComponent;
import by.tms.composite.entity.TextComposite;
import by.tms.composite.parser.TextParser;

public class WordParser implements TextParser {
    private final TextParser letterParser = new LetterParser();

    @Override
    public TextComposite parse(String lexemeValue) {
        TextComposite wordComposite = new TextComposite(ComponentType.WORD);
        TextComponent wordComponent = letterParser.parse(lexemeValue);
        wordComposite.add(wordComponent);
        return wordComposite;
    }
}