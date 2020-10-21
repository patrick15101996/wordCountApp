package api;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.xml.ws.http.HTTPException;

import com.ordina.api.interfaces.IWordFrequency;
import com.ordina.api.interfaces.IWordFrequencyAnalyzer;
import com.ordina.api.interfaces.IWordFrequencyRepository;
import com.ordina.api.models.WordFrequency;
import com.ordina.api.models.WordFrequencyRequest;
import com.ordina.api.repo.WordFrequencyRepository;
import com.ordina.api.services.WordFrequencyAnalyzer;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ApiWordFrequencyAnalyzerTests {
    private IWordFrequencyAnalyzer wordFrequencyAnalyzer;
    private IWordFrequencyRepository wordFrequencyRepository;

    @Before
    public void setup() {
        wordFrequencyAnalyzer = new WordFrequencyAnalyzer();
        wordFrequencyRepository = new WordFrequencyRepository(wordFrequencyAnalyzer);
    }

    @Test
    public void testCalculateHighestFrequency() {
        testCalculateHighestFrequencyWithNormalString();
        testCalculateHighestFrequencyWithCasedString();
        testCalculateHighestFrequencyWithOnlyPunctuations();
        testCalculateHighestFrequencyWithEmptyString();
        testCalculateHighestFrequencyWithNullRequest();
        testCalculateHighestFrequencyWithNull();
    }

    private void testCalculateHighestFrequencyWithNormalString() {
        ResponseEntity actualResponseEntity = wordFrequencyRepository.calculateHighestFrequency(
            new WordFrequencyRequest("The sun shines over the lake", null, 0));
        ResponseEntity expectedResponseEntity = ResponseEntity.ok(2);
        assertEquals(actualResponseEntity, expectedResponseEntity);
    }

    private void testCalculateHighestFrequencyWithCasedString() {
        ResponseEntity actualResponseEntity = wordFrequencyRepository.calculateHighestFrequency(
            new WordFrequencyRequest("The sun shines over tHe thE lake", null, 0));
        ResponseEntity expectedResponseEntity = ResponseEntity.ok(3);
        assertEquals(actualResponseEntity, expectedResponseEntity);
    }

    private void testCalculateHighestFrequencyWithOnlyPunctuations() {
        ResponseEntity actualResponseEntity = wordFrequencyRepository.calculateHighestFrequency(
            new WordFrequencyRequest("... ,, \"\" :{}:><?", null, 0));
        ResponseEntity expectedResponseEntity = ResponseEntity.ok(0);
        assertEquals(actualResponseEntity, expectedResponseEntity);
    }

    private void testCalculateHighestFrequencyWithEmptyString() {
        ResponseEntity actualResponseEntity = wordFrequencyRepository.calculateHighestFrequency(
            new WordFrequencyRequest("", null, 0));
        ResponseEntity expectedResponseEntity = ResponseEntity.ok(0);
        assertEquals(actualResponseEntity, expectedResponseEntity);
    }

    private void testCalculateHighestFrequencyWithNullRequest() {
        boolean threwRightException = false;
        try {
            wordFrequencyRepository.calculateHighestFrequency(
                null);
        } catch (HTTPException ex) {
            if (ex.getStatusCode() == HttpStatus.UNPROCESSABLE_ENTITY.value()) {
                threwRightException = true;
            }
        }
        assertTrue(threwRightException);
    }

    private void testCalculateHighestFrequencyWithNull() {
        ResponseEntity actualResponseEntity = wordFrequencyRepository.calculateHighestFrequency(
            new WordFrequencyRequest(null, null, 0));
        ResponseEntity expectedResponseEntity = ResponseEntity.ok(0);
        assertEquals(actualResponseEntity, expectedResponseEntity);
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
        testCalculateFrequencyForWordWithNullRequest();
        testCalculateFrequencyForWordWithNullStrings();
    }

    private void testCalculateFrequencyForWordWithNormalStrings() {
        ResponseEntity actualResponseEntity = wordFrequencyRepository.calculateFrequencyForWord(
            new WordFrequencyRequest("The sun shines over the lake and the lake looks lake-ish. " +
                                     "But that's a lake for you.", "lake", 0));
        ResponseEntity expectedResponseEntity = ResponseEntity.ok(4);
        assertEquals(actualResponseEntity, expectedResponseEntity);
    }

    private void testCalculateFrequencyForWordWithCasedSentence() {
        ResponseEntity actualResponseEntity = wordFrequencyRepository.calculateFrequencyForWord(
            new WordFrequencyRequest("      \"the sun\", says: the traveller, \"shines, if I  , , remember correctly    over the laKe\". " +
                                     "But we'll have to see about that when we get to the lAke", "lake", 0));
        ResponseEntity expectedResponseEntity = ResponseEntity.ok(2);
        assertEquals(actualResponseEntity, expectedResponseEntity);
    }

    private void testCalculateFrequencyForWordWithCasedWord() {
        ResponseEntity actualResponseEntity = wordFrequencyRepository.calculateFrequencyForWord(
            new WordFrequencyRequest("      \"the sun\", says: the traveller, \"shines, if I  , , remember correctly    over the lake\". " +
                                     "But we'll have to see about that when we get to the lake", "lAkE", 0));
        ResponseEntity expectedResponseEntity = ResponseEntity.ok(2);
        assertEquals(actualResponseEntity, expectedResponseEntity);
    }

    private void testCalculateFrequencyForWordWithPunctuations() {
        ResponseEntity actualResponseEntity = wordFrequencyRepository.calculateFrequencyForWord(
            new WordFrequencyRequest("      \"the sun\", says: the traveller, \"shines, if I  , , remember correctly    over the lake\". " +
                                     "But we'll have to see about that when we get to the lake", "lake", 0));
        ResponseEntity expectedResponseEntity = ResponseEntity.ok(2);
        assertEquals(actualResponseEntity, expectedResponseEntity);
    }

    private void testCalculateFrequencyForWordWithEmptyWord() {
        ResponseEntity actualResponseEntity = wordFrequencyRepository.calculateFrequencyForWord(
            new WordFrequencyRequest("The sun shines over the lake and the lake looks lake-ish. " +
                                     "But that's a lake for you.", "", 0));
        ResponseEntity expectedResponseEntity = ResponseEntity.ok(0);
        assertEquals(actualResponseEntity, expectedResponseEntity);
    }

    private void testCalculateFrequencyForWordWithEmptySentence() {
        ResponseEntity actualResponseEntity = wordFrequencyRepository.calculateFrequencyForWord(
            new WordFrequencyRequest("", "lake", 0));
        ResponseEntity expectedResponseEntity = ResponseEntity.ok(0);
        assertEquals(actualResponseEntity, expectedResponseEntity);
    }

    private void testCalculateFrequencyForWordWithEmptyStrings() {
        ResponseEntity actualResponseEntity = wordFrequencyRepository.calculateFrequencyForWord(
            new WordFrequencyRequest("", "", 0));
        ResponseEntity expectedResponseEntity = ResponseEntity.ok(0);
        assertEquals(actualResponseEntity, expectedResponseEntity);
    }

    private void testCalculateFrequencyForWordWithNullSentence() {
        ResponseEntity actualResponseEntity = wordFrequencyRepository.calculateFrequencyForWord(
            new WordFrequencyRequest(null, "lake", 0));
        ResponseEntity expectedResponseEntity = ResponseEntity.ok(0);
        assertEquals(actualResponseEntity, expectedResponseEntity);
    }

    private void testCalculateFrequencyForWordWithNullWord() {
        ResponseEntity actualResponseEntity = wordFrequencyRepository.calculateFrequencyForWord(
            new WordFrequencyRequest("The sun shines over the lake and the lake looks lake-ish. " +
                                     "But that's a lake for you.", null, 0));
        ResponseEntity expectedResponseEntity = ResponseEntity.ok(0);
        assertEquals(actualResponseEntity, expectedResponseEntity);
    }

    private void testCalculateFrequencyForWordWithNullRequest() {
        boolean threwRightException = false;
        try {
            wordFrequencyRepository.calculateFrequencyForWord(
                null);
        } catch (HTTPException ex) {
            if (ex.getStatusCode() == HttpStatus.UNPROCESSABLE_ENTITY.value()) {
                threwRightException = true;
            }
        }
        assertTrue(threwRightException);
    }

    private void testCalculateFrequencyForWordWithNullStrings() {
        ResponseEntity actualResponseEntity = wordFrequencyRepository.calculateFrequencyForWord(
            new WordFrequencyRequest(null, null, 0));
        ResponseEntity expectedResponseEntity = ResponseEntity.ok(0);
        assertEquals(actualResponseEntity, expectedResponseEntity);
    }

    @Test
    public void testCalculateMostFrequentNWords() {
        testCalculateMostFrequentNWordsWithNormalStrings();
        testCalculateMostFrequentNWordsWithCasedStrings();
        testCalculateMostFrequentNWordsWithLimitExceedingStringLength();
        testCalculateMostFrequentNWordsWithNoLimit();
        testCalculateMostFrequentNWordsWithNegativeLimit();
        testCalculateMostFrequentNWordsWithEmptyString();
        testCalculateMostFrequentNWordsWithNullRequest();
        testCalculateMostFrequentNWordsWithNullString();
    }

    private void testCalculateMostFrequentNWordsWithNormalStrings() {
        ResponseEntity actualResponseEntity = wordFrequencyRepository.calculateMostFrequentNWords(
            new WordFrequencyRequest("The sun shines over the lake", null, 2));
        List expectedWordFrequencies = new ArrayList<IWordFrequency>() {
            {
                add(new WordFrequency("the", 2));
                add(new WordFrequency("lake", 1));
                add(new WordFrequency("over", 1));
            }
        };
        ResponseEntity expectedResponseEntity = ResponseEntity.ok(expectedWordFrequencies);
        assertEquals(actualResponseEntity, expectedResponseEntity);
    }

    private void testCalculateMostFrequentNWordsWithCasedStrings() {
        ResponseEntity actualResponseEntity = wordFrequencyRepository.calculateMostFrequentNWords(
            new WordFrequencyRequest("The sun shines over tHe thE lake laKE", null, 2));
        List expectedWordFrequencies = new ArrayList<IWordFrequency>() {
            {
                add(new WordFrequency("the", 3));
                add(new WordFrequency("lake", 2));
                add(new WordFrequency("over", 1));
            }
        };
        ResponseEntity expectedResponseEntity = ResponseEntity.ok(expectedWordFrequencies);
        assertEquals(actualResponseEntity, expectedResponseEntity);
    }

    private void testCalculateMostFrequentNWordsWithLimitExceedingStringLength() {
        ResponseEntity actualResponseEntity = wordFrequencyRepository.calculateMostFrequentNWords(
            new WordFrequencyRequest("The sun shines over tHe thE lake laKE", null, 20));
        List expectedWordFrequencies = new ArrayList<IWordFrequency>() {
            {
                add(new WordFrequency("the", 3));
                add(new WordFrequency("lake", 2));
                add(new WordFrequency("over", 1));
                add(new WordFrequency("shines", 1));
                add(new WordFrequency("sun", 1));
            }
        };
        ResponseEntity expectedResponseEntity = ResponseEntity.ok(expectedWordFrequencies);
        assertEquals(actualResponseEntity, expectedResponseEntity);
    }

    private void testCalculateMostFrequentNWordsWithNoLimit() {
        ResponseEntity actualResponseEntity = wordFrequencyRepository.calculateMostFrequentNWords(
            new WordFrequencyRequest("The sun shines over tHe thE lake laKE", null, 0));
        List expectedWordFrequencies = Collections.emptyList();
        ResponseEntity expectedResponseEntity = ResponseEntity.ok(expectedWordFrequencies);
        assertEquals(actualResponseEntity, expectedResponseEntity);
    }

    private void testCalculateMostFrequentNWordsWithNegativeLimit() {
        ResponseEntity actualResponseEntity = wordFrequencyRepository.calculateMostFrequentNWords(
            new WordFrequencyRequest("The sun shines over tHe thE lake laKE", null, -2));
        List expectedWordFrequencies = Collections.emptyList();
        ResponseEntity expectedResponseEntity = ResponseEntity.ok(expectedWordFrequencies);
        assertEquals(actualResponseEntity, expectedResponseEntity);
    }

    private void testCalculateMostFrequentNWordsWithEmptyString() {
        ResponseEntity actualResponseEntity = wordFrequencyRepository.calculateMostFrequentNWords(
            new WordFrequencyRequest("", null, 2));
        List expectedWordFrequencies = Collections.emptyList();
        ResponseEntity expectedResponseEntity = ResponseEntity.ok(expectedWordFrequencies);
        assertEquals(actualResponseEntity, expectedResponseEntity);
    }

    private void testCalculateMostFrequentNWordsWithNullRequest() {
        boolean threwRightException = false;
        try {
            wordFrequencyRepository.calculateMostFrequentNWords(
                null);
        } catch (HTTPException ex) {
            if (ex.getStatusCode() == HttpStatus.UNPROCESSABLE_ENTITY.value()) {
                threwRightException = true;
            }
        }
        assertTrue(threwRightException);
    }

    private void testCalculateMostFrequentNWordsWithNullString() {
        ResponseEntity actualResponseEntity = wordFrequencyRepository.calculateMostFrequentNWords(
            new WordFrequencyRequest(null, null, 2));
        List expectedWordFrequencies = Collections.emptyList();
        ResponseEntity expectedResponseEntity = ResponseEntity.ok(expectedWordFrequencies);
        assertEquals(actualResponseEntity, expectedResponseEntity);
    }
}
