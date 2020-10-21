package com.ordina.api.repo;

import java.util.List;

import javax.xml.ws.http.HTTPException;

import com.ordina.api.interfaces.IWordFrequency;
import com.ordina.api.interfaces.IWordFrequencyAnalyzer;
import com.ordina.api.interfaces.IWordFrequencyRepository;
import com.ordina.api.models.WordFrequencyRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
        checkRequestIntegrity(wordFrequencyRequest);
        int highestFrequency = wordFrequencyAnalyzer.calculateHighestFrequency(wordFrequencyRequest.getSentence());
        return ResponseEntity.ok(highestFrequency);
    }

    @Override
    public ResponseEntity calculateFrequencyForWord(WordFrequencyRequest wordFrequencyRequest) {
        checkRequestIntegrity(wordFrequencyRequest);
        int highestFrequencyForWord = wordFrequencyAnalyzer.calculateFrequencyForWord(wordFrequencyRequest.getSentence(), wordFrequencyRequest.getWord());
        return ResponseEntity.ok(highestFrequencyForWord);
    }

    @Override
    public ResponseEntity calculateMostFrequentNWords(WordFrequencyRequest wordFrequencyRequest) {
        checkRequestIntegrity(wordFrequencyRequest);
        List<IWordFrequency> wordFrequencies = wordFrequencyAnalyzer.calculateMostFrequentNWords(wordFrequencyRequest.getSentence(), wordFrequencyRequest.getN());
        return ResponseEntity.ok(wordFrequencies);
    }

    private void checkRequestIntegrity(WordFrequencyRequest wordFrequencyRequest) {
        if (wordFrequencyRequest == null) {
            throw new HTTPException(HttpStatus.UNPROCESSABLE_ENTITY.value());
        }
    }
}
