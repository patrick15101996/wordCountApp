package com.ordina.api.interfaces;

import com.ordina.api.models.WordFrequencyRequest;
import org.springframework.http.ResponseEntity;

public interface IWordFrequencyRepository {
    ResponseEntity calculateHighestFrequency(WordFrequencyRequest wordFrequencyRequest);

    ResponseEntity calculateFrequencyForWord(WordFrequencyRequest wordFrequencyRequest);

    ResponseEntity calculateMostFrequentNWords(WordFrequencyRequest wordFrequencyRequest);
}
