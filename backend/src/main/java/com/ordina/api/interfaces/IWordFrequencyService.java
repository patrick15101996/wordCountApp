package com.ordina.api.interfaces;

import java.util.List;

public interface IWordFrequencyService {
    int calculateHighestFrequency(String sentence);

    int calculateFrequencyForWord(String sentence, String word);

    List<IWordFrequency> calculateMostFrequentNWords(String sentence, int n);
}
