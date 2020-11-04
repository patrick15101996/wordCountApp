package com.ordina.api.interfaces;

import com.ordina.api.models.WordFrequencyRequest;
import com.ordina.api.models.WordFrequencyResponse;
import org.springframework.http.ResponseEntity;

public interface IWordFrequencyProcessor {
    ResponseEntity<WordFrequencyResponse> calculateHighestFrequency(WordFrequencyRequest wordFrequencyRequest);

    ResponseEntity<WordFrequencyResponse> calculateFrequencyForWord(WordFrequencyRequest wordFrequencyRequest);

    ResponseEntity<WordFrequencyResponse> calculateMostFrequentNWords(WordFrequencyRequest wordFrequencyRequest);
}
