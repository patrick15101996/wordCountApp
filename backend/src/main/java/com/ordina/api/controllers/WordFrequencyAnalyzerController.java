package com.ordina.api.controllers;

import com.ordina.api.interfaces.IWordFrequencyAnalyzerController;
import com.ordina.api.interfaces.IWordFrequencyProcessor;
import com.ordina.api.models.WordFrequencyRequest;
import com.ordina.api.models.WordFrequencyResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WordFrequencyAnalyzerController implements IWordFrequencyAnalyzerController {
    private final IWordFrequencyProcessor wordFrequencyProcessor;

    @Autowired
    public WordFrequencyAnalyzerController(IWordFrequencyProcessor wordFrequencyProcessor) {
        this.wordFrequencyProcessor = wordFrequencyProcessor;
    }

    @Override
    public ResponseEntity<WordFrequencyResponse> calculateHighestFrequency(WordFrequencyRequest wordFrequencyRequest) {
        return wordFrequencyProcessor.calculateHighestFrequency(wordFrequencyRequest);
    }

    @Override
    public ResponseEntity<WordFrequencyResponse> calculateFrequencyForWord(WordFrequencyRequest wordFrequencyRequest) {
        return wordFrequencyProcessor.calculateFrequencyForWord(wordFrequencyRequest);
    }

    @Override
    public ResponseEntity<WordFrequencyResponse> calculateMostFrequentNWords(WordFrequencyRequest wordFrequencyRequest) {
        return wordFrequencyProcessor.calculateMostFrequentNWords(wordFrequencyRequest);
    }
}
