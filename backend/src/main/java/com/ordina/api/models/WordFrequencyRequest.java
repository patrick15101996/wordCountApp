package com.ordina.api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class WordFrequencyRequest {
    @JsonProperty("sentence")
    String sentence;
    @JsonProperty("word")
    String word;
    @JsonProperty("n")
    int n;
}
