package com.ordina.api.interfaces;


import com.ordina.api.models.WordFrequencyRequest;
import com.ordina.api.models.WordFrequencyResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
public interface IWordFrequencyAnalyzerController {

    @PostMapping("calculateHighestFrequency")
    ResponseEntity<WordFrequencyResponse> calculateHighestFrequency(@RequestBody WordFrequencyRequest wordFrequencyRequest);

    @PostMapping("calculateFrequencyForWord")
    ResponseEntity<WordFrequencyResponse> calculateFrequencyForWord(@RequestBody WordFrequencyRequest wordFrequencyRequest);

    @PostMapping("calculateMostFrequentNWords")
    ResponseEntity<WordFrequencyResponse> calculateMostFrequentNWords(@RequestBody WordFrequencyRequest wordFrequencyRequest);
}
