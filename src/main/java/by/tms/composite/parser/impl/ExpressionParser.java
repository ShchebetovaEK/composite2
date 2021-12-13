package by.tms.composite.parser.impl;

import by.tms.composite.entity.ComponentType;
import by.tms.composite.entity.TextComponent;
import by.tms.composite.entity.TextComposite;
import by.tms.composite.parser.TextParser;

public class ExpressionParser implements TextParser {
    private final TextParser letterParser = new LetterParser();

    @Override
    public TextComposite parse(String expressionValue) {
        TextComposite expressionComposite = new TextComposite(ComponentType.EXPRESSION);
        TextComponent expressionComponent = letterParser.parse(expressionValue);
        expressionComposite.add(expressionComponent);
        return expressionComposite;
    }
}