package com.ordina.api.interfaces;


import com.ordina.api.models.WordFrequencyRequest;
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
    ResponseEntity calculateHighestFrequency(@RequestBody WordFrequencyRequest wordFrequencyRequest);

    @PostMapping("calculateFrequencyForWord")
    ResponseEntity calculateFrequencyForWord(@RequestBody WordFrequencyRequest wordFrequencyRequest);

    @PostMapping("calculateMostFrequentNWords")
    ResponseEntity calculateMostFrequentNWords(@RequestBody WordFrequencyRequest wordFrequencyRequest);
}
