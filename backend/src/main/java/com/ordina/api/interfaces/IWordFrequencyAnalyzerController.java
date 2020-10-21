package com.ordina.api.interfaces;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.ordina.api.models.WordFrequencyRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RestController
@RequestMapping("api")
public interface IWordFrequencyAnalyzerController {

    @PostMapping("calculateHighestFrequency")
    ResponseEntity calculateHighestFrequency(@RequestBody WordFrequencyRequest wordFrequencyRequest);

    @PostMapping("calculateFrequencyForWord")
    ResponseEntity calculateFrequencyForWord(@RequestBody WordFrequencyRequest wordFrequencyRequest);

    @PostMapping("calculateMostFrequentNWords")
    ResponseEntity calculateMostFrequentNWords(@RequestBody WordFrequencyRequest wordFrequencyRequest);
}
