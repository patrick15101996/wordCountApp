package com.ordina.api.processors;

import java.util.List;

import com.ordina.api.interfaces.IWordFrequency;
import com.ordina.api.interfaces.IWordFrequencyProcessor;
import com.ordina.api.interfaces.IWordFrequencyService;
import com.ordina.api.models.WordFrequencyRequest;
import com.ordina.api.models.WordFrequencyResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

@Repository
public class WordFrequencyProcessor implements IWordFrequencyProcessor {
    private final IWordFrequencyService wordFrequencyService;

    @Autowired
    public WordFrequencyProcessor(IWordFrequencyService wordFrequencyService) {
        this.wordFrequencyService = wordFrequencyService;
    }

    @Override
    public ResponseEntity<WordFrequencyResponse> calculateHighestFrequency(WordFrequencyRequest wordFrequencyRequest) {
        if (isRequestErroneous(wordFrequencyRequest)) {
            return ResponseEntity.unprocessableEntity().body(new WordFrequencyResponse("failed"));
        }
        int highestFrequency = wordFrequencyService.calculateHighestFrequency(wordFrequencyRequest.getSentence());
        return ResponseEntity.ok(new WordFrequencyResponse("success", highestFrequency));
    }

    @Override
    public ResponseEntity<WordFrequencyResponse> calculateFrequencyForWord(WordFrequencyRequest wordFrequencyRequest) {
        if (isRequestErroneous(wordFrequencyRequest)) {
            return ResponseEntity.unprocessableEntity().body(new WordFrequencyResponse("failed"));
        }
        int highestFrequencyForWord = wordFrequencyService.calculateFrequencyForWord(wordFrequencyRequest.getSentence(), wordFrequencyRequest.getWord());
        return ResponseEntity.ok(new WordFrequencyResponse("success", highestFrequencyForWord));
    }

    @Override
    public ResponseEntity<WordFrequencyResponse> calculateMostFrequentNWords(WordFrequencyRequest wordFrequencyRequest) {
        if (isRequestErroneous(wordFrequencyRequest)) {
            return ResponseEntity.unprocessableEntity().body(new WordFrequencyResponse("failed"));
        }
        List<IWordFrequency> wordFrequencies = wordFrequencyService.calculateMostFrequentNWords(wordFrequencyRequest.getSentence(), wordFrequencyRequest.getN());
        return ResponseEntity.ok(new WordFrequencyResponse("success", wordFrequencies));
    }

    private boolean isRequestErroneous(WordFrequencyRequest wordFrequencyRequest) {
        return wordFrequencyRequest == null || wordFrequencyRequest.isEmpty();
    }
}
