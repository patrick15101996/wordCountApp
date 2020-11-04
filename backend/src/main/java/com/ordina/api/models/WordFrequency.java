package com.ordina.api.models;

import com.ordina.api.interfaces.IWordFrequency;

public class WordFrequency implements IWordFrequency {
    private final String word;
    private final int frequency;

    public WordFrequency(String word, int frequency) {
        this.word = word;
        this.frequency = frequency;
    }

    @Override
    public String getWord() {
        return word;
    }

    @Override
    public int getFrequency() {
        return frequency;
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (!(object instanceof WordFrequency)) {
            return false;
        }
        WordFrequency wordFrequency = (WordFrequency) object;
        return word.toLowerCase().equals(wordFrequency.word.toLowerCase()) && frequency == wordFrequency.frequency;
    }

    @Override
    public int compareTo(IWordFrequency iWordFrequency) {
        if (iWordFrequency.getFrequency() > frequency) {
            return 1;
        }
        return word.compareToIgnoreCase(iWordFrequency.getWord());
    }
}
