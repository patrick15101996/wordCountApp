package com.ordina.api.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.ordina.api.interfaces.IWordFrequency;
import com.ordina.api.interfaces.IWordFrequencyAnalyzer;
import com.ordina.api.models.WordFrequency;
import com.ordina.api.utils.StringUtil;
import org.springframework.stereotype.Service;
import static java.util.Collections.frequency;

@Service
public class WordFrequencyAnalyzer implements IWordFrequencyAnalyzer {
    @Override
    public int calculateHighestFrequency(String sentence) {
        if (!StringUtil.isEmptyOrNull(sentence)) {
            int frequency = 0;
            sentence = sentence.toLowerCase();
            List<String> splitSentence = Arrays.asList(StringUtil.splitSentence(sentence));
            for (String word : splitSentence) {
                int frequencyForWord = frequency(splitSentence, word);
                if (frequencyForWord > frequency) {
                    frequency = frequencyForWord;
                }
            }
            return frequency;
        }
        return 0;
    }

    @Override
    public int calculateFrequencyForWord(String sentence, String word) {
        if (!StringUtil.isEmptyOrNull(sentence, word)) {
            sentence = sentence.toLowerCase();
            word = word.toLowerCase();
            if (sentence.contains(word)) {
                List<String> splitSentence = Arrays.asList(StringUtil.splitSentence(sentence));
                return frequency(splitSentence, word);
            }
        }
        return 0;
    }

    @Override
    public List<IWordFrequency> calculateMostFrequentNWords(String sentence, int n) {
        if (!StringUtil.isEmptyOrNull(sentence) && n > 0) {
            Map<String, Integer> wordFrequencyMap = new HashMap<>();
            sentence = sentence.toLowerCase();
            String[] splitSentence = StringUtil.splitSentence(sentence);
            Stream.of(splitSentence).forEach(word -> {
                if (wordFrequencyMap.containsKey(word)) {
                    wordFrequencyMap.put(word, wordFrequencyMap.get(word) + 1);
                } else {
                    wordFrequencyMap.put(word, 1);
                }
            });
            List<IWordFrequency> wordFrequencies = new ArrayList<>();
            wordFrequencyMap.forEach((key, value) -> wordFrequencies.add(new WordFrequency(key, value)));
            wordFrequencies.sort(IWordFrequency::compareTo);
            return wordFrequencies.stream().limit(n + 1).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }
}
