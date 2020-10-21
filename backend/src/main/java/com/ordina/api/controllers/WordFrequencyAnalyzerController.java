package com.ordina.api.controllers;

import com.ordina.api.interfaces.IWordFrequencyAnalyzerController;
import com.ordina.api.interfaces.IWordFrequencyRepository;
import com.ordina.api.models.WordFrequencyRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WordFrequencyAnalyzerController implements IWordFrequencyAnalyzerController {
    private IWordFrequencyRepository wordFrequencyRepository;

    @Autowired
    public WordFrequencyAnalyzerController(IWordFrequencyRepository wordFrequencyRepository) {
        this.wordFrequencyRepository = wordFrequencyRepository;
    }

    @Override
    public ResponseEntity calculateHighestFrequency(WordFrequencyRequest wordFrequencyRequest) {
        return wordFrequencyRepository.calculateHighestFrequency(wordFrequencyRequest);
    }

    @Override
    public ResponseEntity calculateFrequencyForWord(WordFrequencyRequest wordFrequencyRequest) {
        return wordFrequencyRepository.calculateFrequencyForWord(wordFrequencyRequest);
    }

    @Override
    public ResponseEntity calculateMostFrequentNWords(WordFrequencyRequest wordFrequencyRequest) {
        return wordFrequencyRepository.calculateMostFrequentNWords(wordFrequencyRequest);
    }
}
