import java.util.List;

import interfaces.WordFrequency;
import interfaces.WordFrequencyAnalyzer;

public class WordFrequencyAnalyzerImpl implements WordFrequencyAnalyzer {
    public int calculateHighestFrequency(String text) {
        //Generate list of all duplicate words (Map?) and then spit out the highest frequency
        return 0;
    }

    public int calculateFrequencyForWord(String text, String word) {
        //Find word in text (then use substring to find it again?) and count++ for each occurrence
        return 0;
    }

    public List<WordFrequency> calculateMostFrequentNWords(String text, int n) {
        //Return all of the frequencies of every word up until n is reached and sort these by ascending alphabetical order
        //Example: for inputText “The sun shines over the lake” and n = 3, it should return the list {(“the”, 2),(“lake”, 1), (“over”, 1) }
        return null;
    }
}
