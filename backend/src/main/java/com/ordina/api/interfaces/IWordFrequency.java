package com.ordina.api.interfaces;

public interface IWordFrequency extends Comparable<IWordFrequency> {
    String getWord();

    int getFrequency();

    @Override
    int compareTo(IWordFrequency iWordFrequency);
}
