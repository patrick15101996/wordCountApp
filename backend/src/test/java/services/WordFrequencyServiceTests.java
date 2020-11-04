package services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.ordina.api.interfaces.IWordFrequency;
import com.ordina.api.interfaces.IWordFrequencyService;
import com.ordina.api.models.WordFrequency;
import com.ordina.api.services.WordFrequencyService;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;


public class WordFrequencyServiceTests {
    private IWordFrequencyService IWordFrequencyService;

    @Before
    public void setup() {
        IWordFrequencyService = new WordFrequencyService();
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
        int highestFrequency = IWordFrequencyService.calculateHighestFrequency("The sun shines over the lake");
        assertEquals(2, highestFrequency);
    }

    private void testCalculateHighestFrequencyWithCasedString() {
        int highestFrequency = IWordFrequencyService.calculateHighestFrequency("The sun shines over tHe thE lake");
        assertEquals(3, highestFrequency);
    }

    private void testCalculateHighestFrequencyWithOnlyPunctuations() {
        int highestFrequency = IWordFrequencyService.calculateHighestFrequency("... ,, \"\" :{}:><?");
        assertEquals(0, highestFrequency);
    }

    private void testCalculateHighestFrequencyWithEmptyString() {
        int highestFrequency = IWordFrequencyService.calculateHighestFrequency("");
        assertEquals(0, highestFrequency);
    }

    private void testCalculateHighestFrequencyWithNull() {
        int highestFrequency = IWordFrequencyService.calculateHighestFrequency(null);
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
        int frequencyForWord = IWordFrequencyService.calculateFrequencyForWord("The sun shines over the lake and the lake looks lake-ish. " +
                                                                               "But that's a lake for you.", "lake");
        assertEquals(4, frequencyForWord);
    }

    private void testCalculateFrequencyForWordWithCasedSentence() {
        int frequencyForWord = IWordFrequencyService.calculateFrequencyForWord(
            "      \"the sun\", says: the traveller, \"shines, if I  , , remember correctly    over the laKe\". " +
            "But we'll have to see about that when we get to the lAke", "lake");
        assertEquals(2, frequencyForWord);
    }

    private void testCalculateFrequencyForWordWithCasedWord() {
        int frequencyForWord = IWordFrequencyService.calculateFrequencyForWord(
            "      \"the sun\", says: the traveller, \"shines, if I  , , remember correctly    over the lake\". " +
            "But we'll have to see about that when we get to the lake", "lAkE");
        assertEquals(2, frequencyForWord);
    }

    private void testCalculateFrequencyForWordWithPunctuations() {
        int frequencyForWord = IWordFrequencyService.calculateFrequencyForWord(
            "      \"the sun\", says: the traveller, \"shines, if I  , , remember correctly    over the lake\". " +
            "But we'll have to see about that when we get to the lake", "lake");
        assertEquals(2, frequencyForWord);
    }

    private void testCalculateFrequencyForWordWithEmptyWord() {
        int frequencyForWord = IWordFrequencyService.calculateFrequencyForWord("The sun shines over the lake and the lake looks lake-ish. " +
                                                                               "But that's a lake for you.", "");
        assertEquals(0, frequencyForWord);
    }

    private void testCalculateFrequencyForWordWithEmptySentence() {
        int frequencyForWord = IWordFrequencyService.calculateFrequencyForWord("", "lake");
        assertEquals(0, frequencyForWord);
    }

    private void testCalculateFrequencyForWordWithEmptyStrings() {
        int frequencyForWord = IWordFrequencyService.calculateFrequencyForWord("", "");
        assertEquals(0, frequencyForWord);
    }

    private void testCalculateFrequencyForWordWithNullSentence() {
        int frequencyForWord = IWordFrequencyService.calculateFrequencyForWord(null, "lake");
        assertEquals(0, frequencyForWord);
    }

    private void testCalculateFrequencyForWordWithNullWord() {
        int frequencyForWord = IWordFrequencyService.calculateFrequencyForWord("The sun shines over the lake and the lake looks lake-ish. " +
                                                                               "But that's a lake for you.", null);
        assertEquals(0, frequencyForWord);
    }

    private void testCalculateFrequencyForWordWithNullStrings() {
        int frequencyForWord = IWordFrequencyService.calculateFrequencyForWord(null, null);
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
        List<IWordFrequency> wordFrequencies = IWordFrequencyService.calculateMostFrequentNWords(
            "The sun shines over the lake", 2);
        List<IWordFrequency> expectedWordFrequencies = new ArrayList<>() {
            {
                add(new WordFrequency("the", 2));
                add(new WordFrequency("lake", 1));
                add(new WordFrequency("over", 1));
            }
        };
        assertEquals(expectedWordFrequencies, wordFrequencies);
    }

    private void testCalculateMostFrequentNWordsWithCasedStrings() {
        List<IWordFrequency> wordFrequencies = IWordFrequencyService.calculateMostFrequentNWords(
            "The sun shines over tHe thE lake laKE", 2);
        List<IWordFrequency> expectedWordFrequencies = new ArrayList<>() {
            {
                add(new WordFrequency("the", 3));
                add(new WordFrequency("lake", 2));
                add(new WordFrequency("over", 1));
            }
        };
        assertEquals(expectedWordFrequencies, wordFrequencies);
    }

    private void testCalculateMostFrequentNWordsWithLimitExceedingStringLength() {
        List<IWordFrequency> wordFrequencies = IWordFrequencyService.calculateMostFrequentNWords(
            "The sun shines over tHe thE lake laKE", 20);
        List<IWordFrequency> expectedWordFrequencies = new ArrayList<>() {
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
        List<IWordFrequency> wordFrequencies = IWordFrequencyService.calculateMostFrequentNWords(
            "The sun shines over tHe thE lake laKE", 0);
        List<IWordFrequency> expectedWordFrequencies = Collections.emptyList();
        assertEquals(expectedWordFrequencies, wordFrequencies);
    }

    private void testCalculateMostFrequentNWordsWithNegativeLimit() {
        List<IWordFrequency> wordFrequencies = IWordFrequencyService.calculateMostFrequentNWords(
            "The sun shines over tHe thE lake laKE", -2);
        List<IWordFrequency> expectedWordFrequencies = Collections.emptyList();
        assertEquals(expectedWordFrequencies, wordFrequencies);
    }

    private void testCalculateMostFrequentNWordsWithEmptyString() {
        List<IWordFrequency> wordFrequencies = IWordFrequencyService.calculateMostFrequentNWords(
            "", 2);
        List<IWordFrequency> expectedWordFrequencies = Collections.emptyList();
        assertEquals(expectedWordFrequencies, wordFrequencies);
    }

    private void testCalculateMostFrequentNWordsWithNullString() {
        List<IWordFrequency> wordFrequencies = IWordFrequencyService.calculateMostFrequentNWords(
            null, 2);
        List<IWordFrequency> expectedWordFrequencies = Collections.emptyList();
        assertEquals(expectedWordFrequencies, wordFrequencies);
    }
}
