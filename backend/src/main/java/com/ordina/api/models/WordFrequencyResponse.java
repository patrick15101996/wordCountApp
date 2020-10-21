package com.ordina.api.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ordina.api.interfaces.IWordFrequency;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_EMPTY)
public class WordFrequencyResponse {
    @JsonProperty("result")
    String result;
    @JsonProperty
    List<IWordFrequency> wordFrequencies;
    @JsonProperty("frequency")
    @JsonInclude(Include.NON_DEFAULT)
    int frequency;

    public WordFrequencyResponse(String result) {
        this.result = result;
    }

    public WordFrequencyResponse(String result, int frequency) {
        this.result = result;
        this.frequency = frequency;
    }

    public WordFrequencyResponse(String result, List<IWordFrequency> wordFrequencies) {
        this.result = result;
        this.wordFrequencies = wordFrequencies;
    }
}
