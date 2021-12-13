package by.tms.composite.parser.impl;

import by.tms.composite.entity.ComponentType;
import by.tms.composite.entity.SymbolLeaf;
import by.tms.composite.entity.TextComponent;
import by.tms.composite.entity.TextComposite;
import by.tms.composite.parser.TextParser;

public class LetterParser implements TextParser {
    private static final String LETTER_DELIMITER_REGEX = "";

    @Override
    public TextComposite parse(String textValue) {
        TextComposite letterComposite = new TextComposite(ComponentType.LETTER);
        String[] symbols = textValue.split(LETTER_DELIMITER_REGEX);
        for (String symbol : symbols) {
            TextComponent letterComponent =
                    new SymbolLeaf(Character.isLetter(symbol.charAt(0)) ? ComponentType.LETTER : ComponentType.SYMBOL, symbol.charAt(0));
            letterComposite.add(letterComponent);
        }
        return letterComposite;
    }
}