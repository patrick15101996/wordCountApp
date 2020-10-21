package com.ordina.api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class WordFrequencyRequest {
    @JsonProperty("sentence")
    String sentence;
    @JsonProperty("word")
    String word;
    @JsonProperty("n")
    @JsonInclude(Include.NON_DEFAULT)
    int n;

    public boolean isEmpty() {
        return sentence == null || sentence.isEmpty();
    }
}
