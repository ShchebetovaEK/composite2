package by.tms.composite.service;

import by.tms.composite.entity.TextComponent;
import by.tms.composite.entity.TextComposite;

import java.util.List;
import java.util.Map;

public interface TextOperation {
    List<TextComponent> sortParagraphsBySentence(TextComposite textComposite);
    List<TextComponent> findSentenceWithLongestWord(TextComposite textComposite);
    List<TextComponent> removeAllSentenceByNumberWord(TextComposite textComposite, int minWordAmount);
    Map<String, Long> countSameWord(TextComposite textComposite);
    long numberOfVowel(TextComponent sentenceComponent);
    long numberOfСonsonants(TextComponent sentenceComponent);
}