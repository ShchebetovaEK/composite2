package by.tms.composite.service.impl;

import by.tms.composite.entity.ComponentType;
import by.tms.composite.entity.TextComponent;
import by.tms.composite.entity.TextComposite;
import by.tms.composite.service.TextOperation;

import java.util.*;
import java.util.stream.Collectors;

public class TextOperationImpl implements TextOperation {
    private static final String VOWELS = "(?iu:[aouieyаоэуыеяёюи])";
    private static final String CONSONANTS = "(?iu:[b-zб-ь&&[^еёиоуыeiouy]])";

    @Override
    public List<TextComponent> sortParagraphsBySentence(TextComposite textComposite) {
        return textComposite
                .getChild()
                .stream()
                .sorted(new TextComposite.SentenceAmountComparator())
                .toList();
    }

    @Override
    public List<TextComponent> findSentenceWithLongestWord(TextComposite textComposite) {
        int maxLength = findLongestWordLength(textComposite);
        return textComposite
                .getChild()
                .stream()
                .flatMap(p -> p.getChild().stream())
                .flatMap(s -> s.getChild().stream())
                .filter(l -> l.getChild().stream()
                        .anyMatch(w -> w.getType().equals(ComponentType.WORD) && w.toString().length() == maxLength))
                .toList();
    }

    public int findLongestWordLength(TextComposite textComposite) {
        TextComponent textComponent = textComposite
                .getChild()
                .stream()
                .flatMap(p -> p.getChild().stream())
                .flatMap(s -> s.getChild().stream())
                .flatMap(l -> l.getChild().stream())
                .filter(w -> w.getType().equals(ComponentType.WORD))
                .max(Comparator.comparingInt(w -> w.toString().length()))
                .get();
        return textComponent.toString().length();
    }

    @Override
    public List<TextComponent> removeAllSentenceByNumberWord(TextComposite textComposite, int minWordAmount) {
        return textComposite
                .getChild()
                .stream()
                .flatMap(p -> p.getChild().stream())
                .filter(s -> s.getChild().stream()
                        .flatMap(l -> l.getChild().stream())
                        .filter(w -> w.getType().equals(ComponentType.WORD)).count() >= minWordAmount).toList();
    }

    @Override
    public Map<String, Long> countSameWord(TextComposite textComposite) {
        Map<String, Long> similarWords = textComposite.getChild().stream()
                .flatMap(p -> p.getChild().stream())
                .flatMap(s -> s.getChild().stream())
                .flatMap(l -> l.getChild().stream())
                .filter(w -> w.getType().equals(ComponentType.WORD))
                .collect(Collectors.groupingBy(w -> w.toString().toLowerCase(), Collectors.counting()));
        similarWords.entrySet().removeIf(w -> w.getValue() == 1);
        return similarWords;
    }

    @Override
    public long numberOfVowel(TextComponent sentenceComponent) {
        return sentenceComponent.getChild().stream()
                .flatMap(l -> l.getChild().stream())
                .filter(w -> w.getType().equals(ComponentType.WORD))
                .flatMap(w -> w.getChild().stream())
                .flatMap(l -> l.getChild().stream())
                .filter(l -> l.toString().matches(CONSONANTS))
                .count();
    }

    @Override
    public long numberOfСonsonants(TextComponent sentenceComponent) {
        return sentenceComponent.getChild().stream()
                .flatMap(l -> l.getChild().stream())
                .filter(w -> w.getType().equals(ComponentType.WORD))
                .flatMap(w -> w.getChild().stream())
                .flatMap(l -> l.getChild().stream())
                .filter(l -> l.toString().matches(VOWELS))
                .count();
    }
}