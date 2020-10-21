package com.ordina.api.repo;

import java.util.List;

import com.ordina.api.interfaces.IWordFrequency;
import com.ordina.api.interfaces.IWordFrequencyAnalyzer;
import com.ordina.api.interfaces.IWordFrequencyRepository;
import com.ordina.api.models.WordFrequencyRequest;
import com.ordina.api.models.WordFrequencyResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

@Repository
public class WordFrequencyRepository implements IWordFrequencyRepository {
    private IWordFrequencyAnalyzer wordFrequencyAnalyzer;

    @Autowired
    public WordFrequencyRepository(IWordFrequencyAnalyzer wordFrequencyAnalyzer) {
        this.wordFrequencyAnalyzer = wordFrequencyAnalyzer;
    }

    @Override
    public ResponseEntity calculateHighestFrequency(WordFrequencyRequest wordFrequencyRequest) {
        if (isRequestErroneous(wordFrequencyRequest)) {
            return ResponseEntity.unprocessableEntity().body(new WordFrequencyResponse("failed"));
        }
        int highestFrequency = wordFrequencyAnalyzer.calculateHighestFrequency(wordFrequencyRequest.getSentence());
        return ResponseEntity.ok(new WordFrequencyResponse("success", highestFrequency));
    }

    @Override
    public ResponseEntity calculateFrequencyForWord(WordFrequencyRequest wordFrequencyRequest) {
        if (isRequestErroneous(wordFrequencyRequest)) {
            return ResponseEntity.unprocessableEntity().body(new WordFrequencyResponse("failed"));
        }
        int highestFrequencyForWord = wordFrequencyAnalyzer.calculateFrequencyForWord(wordFrequencyRequest.getSentence(), wordFrequencyRequest.getWord());
        return ResponseEntity.ok(new WordFrequencyResponse("success", highestFrequencyForWord));
    }

    @Override
    public ResponseEntity calculateMostFrequentNWords(WordFrequencyRequest wordFrequencyRequest) {
        if (isRequestErroneous(wordFrequencyRequest)) {
            return ResponseEntity.unprocessableEntity().body(new WordFrequencyResponse("failed"));
        }
        List<IWordFrequency> wordFrequencies = wordFrequencyAnalyzer.calculateMostFrequentNWords(wordFrequencyRequest.getSentence(), wordFrequencyRequest.getN());
        return ResponseEntity.ok(new WordFrequencyResponse("success", wordFrequencies));
    }

    private boolean isRequestErroneous(WordFrequencyRequest wordFrequencyRequest) {
        return wordFrequencyRequest == null || wordFrequencyRequest.isEmpty();
    }
}
