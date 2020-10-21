package analyzer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import interfaces.IWordFrequency;
import interfaces.IWordFrequencyAnalyzer;
import models.WordFrequency;
import models.WordFrequencyAnalyzer;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;


public class WordFrequencyAnalyzerTests {
    private IWordFrequencyAnalyzer IWordFrequencyAnalyzer;

    @Before
    public void setup() {
        IWordFrequencyAnalyzer = new WordFrequencyAnalyzer();
    }

    @Test
    public void testCalculateHighestFrequency() {
        testCalculateHighestFrequencyWithNormalString();
        testCalculateHighestFrequencyWithCasedString();
        testCalculateHighestFrequencyWithOnlyPunctuations();
        testCalculateHighestFrequencyWithEmptyString();
        testCalculateHighestFrequencyWithNull();
    }

    private void testCalculateHighestFrequencyWithNormalString() {
        int highestFrequency = IWordFrequencyAnalyzer.calculateHighestFrequency("The sun shines over the lake");
        assertEquals(2, highestFrequency);
    }

    private void testCalculateHighestFrequencyWithCasedString() {
        int highestFrequency = IWordFrequencyAnalyzer.calculateHighestFrequency("The sun shines over tHe thE lake");
        assertEquals(3, highestFrequency);
    }

    private void testCalculateHighestFrequencyWithOnlyPunctuations() {
        int highestFrequency = IWordFrequencyAnalyzer.calculateHighestFrequency("... ,, \"\" :{}:><?");
        assertEquals(0, highestFrequency);
    }

    private void testCalculateHighestFrequencyWithEmptyString() {
        int highestFrequency = IWordFrequencyAnalyzer.calculateHighestFrequency("");
        assertEquals(0, highestFrequency);
    }

    private void testCalculateHighestFrequencyWithNull() {
        int highestFrequency = IWordFrequencyAnalyzer.calculateHighestFrequency(null);
        assertEquals(0, highestFrequency);
    }

    @Test
    public void testCalculateFrequencyForWord() {
        testCalculateFrequencyForWordWithNormalStrings();
        testCalculateFrequencyForWordWithCasedSentence();
        testCalculateFrequencyForWordWithCasedWord();
        testCalculateFrequencyForWordWithPunctuations();
        testCalculateFrequencyForWordWithEmptyWord();
        testCalculateFrequencyForWordWithEmptySentence();
        testCalculateFrequencyForWordWithEmptyStrings();
        testCalculateFrequencyForWordWithNullSentence();
        testCalculateFrequencyForWordWithNullWord();
        testCalculateFrequencyForWordWithNullStrings();
    }

    private void testCalculateFrequencyForWordWithNormalStrings() {
        int frequencyForWord = IWordFrequencyAnalyzer.calculateFrequencyForWord("The sun shines over the lake and the lake looks lake-ish. " +
                                                                                "But that's a lake for you.", "lake");
        assertEquals(4, frequencyForWord);
    }

    private void testCalculateFrequencyForWordWithCasedSentence() {
        int frequencyForWord = IWordFrequencyAnalyzer.calculateFrequencyForWord(
            "      \"the sun\", says: the traveller, \"shines, if I  , , remember correctly    over the laKe\". " +
            "But we'll have to see about that when we get to the lAke", "lake");
        assertEquals(2, frequencyForWord);
    }

    private void testCalculateFrequencyForWordWithCasedWord() {
        int frequencyForWord = IWordFrequencyAnalyzer.calculateFrequencyForWord(
            "      \"the sun\", says: the traveller, \"shines, if I  , , remember correctly    over the lake\". " +
            "But we'll have to see about that when we get to the lake", "lAkE");
        assertEquals(2, frequencyForWord);
    }

    private void testCalculateFrequencyForWordWithPunctuations() {
        int frequencyForWord = IWordFrequencyAnalyzer.calculateFrequencyForWord(
            "      \"the sun\", says: the traveller, \"shines, if I  , , remember correctly    over the lake\". " +
            "But we'll have to see about that when we get to the lake", "lake");
        assertEquals(2, frequencyForWord);
    }

    private void testCalculateFrequencyForWordWithEmptyWord() {
        int frequencyForWord = IWordFrequencyAnalyzer.calculateFrequencyForWord("The sun shines over the lake and the lake looks lake-ish. " +
                                                                                "But that's a lake for you.", "");
        assertEquals(0, frequencyForWord);
    }

    private void testCalculateFrequencyForWordWithEmptySentence() {
        int frequencyForWord = IWordFrequencyAnalyzer.calculateFrequencyForWord("", "lake");
        assertEquals(0, frequencyForWord);
    }

    private void testCalculateFrequencyForWordWithEmptyStrings() {
        int frequencyForWord = IWordFrequencyAnalyzer.calculateFrequencyForWord("", "");
        assertEquals(0, frequencyForWord);
    }

    private void testCalculateFrequencyForWordWithNullSentence() {
        int frequencyForWord = IWordFrequencyAnalyzer.calculateFrequencyForWord(null, "lake");
        assertEquals(0, frequencyForWord);
    }

    private void testCalculateFrequencyForWordWithNullWord() {
        int frequencyForWord = IWordFrequencyAnalyzer.calculateFrequencyForWord("The sun shines over the lake and the lake looks lake-ish. " +
                                                                                "But that's a lake for you.", null);
        assertEquals(0, frequencyForWord);
    }

    private void testCalculateFrequencyForWordWithNullStrings() {
        int frequencyForWord = IWordFrequencyAnalyzer.calculateFrequencyForWord(null, null);
        assertEquals(0, frequencyForWord);
    }

    @Test
    public void testCalculateMostFrequentNWords() {
        testCalculateMostFrequentNWordsWithNormalStrings();
        testCalculateMostFrequentNWordsWithCasedStrings();
        testCalculateMostFrequentNWordsWithLimitExceedingStringLength();
        testCalculateMostFrequentNWordsWithNoLimit();
        testCalculateMostFrequentNWordsWithNegativeLimit();
        testCalculateMostFrequentNWordsWithEmptyString();
        testCalculateMostFrequentNWordsWithNullString();
    }

    private void testCalculateMostFrequentNWordsWithNormalStrings() {
        List<IWordFrequency> wordFrequencies = IWordFrequencyAnalyzer.calculateMostFrequentNWords(
            "The sun shines over the lake", 2);
        List expectedWordFrequencies = new ArrayList<IWordFrequency>() {
            {
                add(new WordFrequency("the", 2));
                add(new WordFrequency("lake", 1));
                add(new WordFrequency("over", 1));
            }
        };
        assertEquals(expectedWordFrequencies, wordFrequencies);
    }

    private void testCalculateMostFrequentNWordsWithCasedStrings() {
        List<IWordFrequency> wordFrequencies = IWordFrequencyAnalyzer.calculateMostFrequentNWords(
            "The sun shines over tHe thE lake laKE", 2);
        List expectedWordFrequencies = new ArrayList<IWordFrequency>() {
            {
                add(new WordFrequency("the", 3));
                add(new WordFrequency("lake", 2));
                add(new WordFrequency("over", 1));
            }
        };
        assertEquals(expectedWordFrequencies, wordFrequencies);
    }

    private void testCalculateMostFrequentNWordsWithLimitExceedingStringLength() {
        List<IWordFrequency> wordFrequencies = IWordFrequencyAnalyzer.calculateMostFrequentNWords(
            "The sun shines over tHe thE lake laKE", 20);
        List expectedWordFrequencies = new ArrayList<IWordFrequency>() {
            {
                add(new WordFrequency("the", 3));
                add(new WordFrequency("lake", 2));
                add(new WordFrequency("over", 1));
                add(new WordFrequency("shines", 1));
                add(new WordFrequency("sun", 1));
            }
        };
        assertEquals(expectedWordFrequencies, wordFrequencies);
    }

    private void testCalculateMostFrequentNWordsWithNoLimit() {
        List<IWordFrequency> wordFrequencies = IWordFrequencyAnalyzer.calculateMostFrequentNWords(
            "The sun shines over tHe thE lake laKE", 0);
        List expectedWordFrequencies = Collections.emptyList();
        assertEquals(expectedWordFrequencies, wordFrequencies);
    }

    private void testCalculateMostFrequentNWordsWithNegativeLimit() {
        List<IWordFrequency> wordFrequencies = IWordFrequencyAnalyzer.calculateMostFrequentNWords(
            "The sun shines over tHe thE lake laKE", -2);
        List expectedWordFrequencies = Collections.emptyList();
        assertEquals(expectedWordFrequencies, wordFrequencies);
    }

    private void testCalculateMostFrequentNWordsWithEmptyString() {
        List<IWordFrequency> wordFrequencies = IWordFrequencyAnalyzer.calculateMostFrequentNWords(
            "", 2);
        List expectedWordFrequencies = Collections.emptyList();
        assertEquals(expectedWordFrequencies, wordFrequencies);
    }

    private void testCalculateMostFrequentNWordsWithNullString() {
        List<IWordFrequency> wordFrequencies = IWordFrequencyAnalyzer.calculateMostFrequentNWords(
            null, 2);
        List expectedWordFrequencies = Collections.emptyList();
        assertEquals(expectedWordFrequencies, wordFrequencies);
    }
}
