package api;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.ordina.api.interfaces.IWordFrequency;
import com.ordina.api.interfaces.IWordFrequencyAnalyzer;
import com.ordina.api.interfaces.IWordFrequencyRepository;
import com.ordina.api.models.WordFrequency;
import com.ordina.api.models.WordFrequencyRequest;
import com.ordina.api.models.WordFrequencyResponse;
import com.ordina.api.repo.WordFrequencyRepository;
import com.ordina.api.services.WordFrequencyAnalyzer;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.ResponseEntity;
import static org.junit.Assert.assertEquals;

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
        testCalculateHighestFrequencyWithNull();
        testCalculateHighestFrequencyWithNullRequest();
    }

    private void testCalculateHighestFrequencyWithNormalString() {
        ResponseEntity actualResponseEntity = wordFrequencyRepository.calculateHighestFrequency(
            new WordFrequencyRequest("The sun shines over the lake", null, 0));
        ResponseEntity expectedResponseEntity = ResponseEntity.ok(new WordFrequencyResponse("success", 2));
        assertEquals(actualResponseEntity, expectedResponseEntity);
    }

    private void testCalculateHighestFrequencyWithCasedString() {
        ResponseEntity actualResponseEntity = wordFrequencyRepository.calculateHighestFrequency(
            new WordFrequencyRequest("The sun shines over tHe thE lake", null, 0));
        ResponseEntity expectedResponseEntity = ResponseEntity.ok(new WordFrequencyResponse("success", 3));
        assertEquals(actualResponseEntity, expectedResponseEntity);
    }

    private void testCalculateHighestFrequencyWithOnlyPunctuations() {
        ResponseEntity actualResponseEntity = wordFrequencyRepository.calculateHighestFrequency(
            new WordFrequencyRequest("... ,, \"\" :{}:><?", null, 0));
        ResponseEntity expectedResponseEntity = ResponseEntity.ok(new WordFrequencyResponse("success", 0));
        assertEquals(actualResponseEntity, expectedResponseEntity);
    }

    private void testCalculateHighestFrequencyWithEmptyString() {
        ResponseEntity actualResponseEntity = wordFrequencyRepository.calculateHighestFrequency(
            new WordFrequencyRequest("", null, 0));
        ResponseEntity expectedResponseEntity = ResponseEntity.unprocessableEntity().body(new WordFrequencyResponse("failed"));
        assertEquals(actualResponseEntity, expectedResponseEntity);
    }

    private void testCalculateHighestFrequencyWithNullRequest() {
        ResponseEntity actualResponseEntity = wordFrequencyRepository.calculateHighestFrequency(null);
        ResponseEntity expectedResponseEntity = ResponseEntity.unprocessableEntity().body(new WordFrequencyResponse("failed"));
        assertEquals(actualResponseEntity, expectedResponseEntity);
    }

    private void testCalculateHighestFrequencyWithNull() {
        ResponseEntity actualResponseEntity = wordFrequencyRepository.calculateHighestFrequency(
            new WordFrequencyRequest(null, null, 0));
        ResponseEntity expectedResponseEntity = ResponseEntity.unprocessableEntity().body(new WordFrequencyResponse("failed"));
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
        testCalculateFrequencyForWordWithNullStrings();
        testCalculateFrequencyForWordWithNullRequest();
    }

    private void testCalculateFrequencyForWordWithNormalStrings() {
        ResponseEntity actualResponseEntity = wordFrequencyRepository.calculateFrequencyForWord(
            new WordFrequencyRequest("The sun shines over the lake and the lake looks lake-ish. " +
                                     "But that's a lake for you.", "lake", 0));
        ResponseEntity expectedResponseEntity = ResponseEntity.ok(new WordFrequencyResponse("success", 4));
        assertEquals(actualResponseEntity, expectedResponseEntity);
    }

    private void testCalculateFrequencyForWordWithCasedSentence() {
        ResponseEntity actualResponseEntity = wordFrequencyRepository.calculateFrequencyForWord(
            new WordFrequencyRequest("      \"the sun\", says: the traveller, \"shines, if I  , , remember correctly    over the laKe\". " +
                                     "But we'll have to see about that when we get to the lAke", "lake", 0));
        ResponseEntity expectedResponseEntity = ResponseEntity.ok(new WordFrequencyResponse("success", 2));
        assertEquals(actualResponseEntity, expectedResponseEntity);
    }

    private void testCalculateFrequencyForWordWithCasedWord() {
        ResponseEntity actualResponseEntity = wordFrequencyRepository.calculateFrequencyForWord(
            new WordFrequencyRequest("      \"the sun\", says: the traveller, \"shines, if I  , , remember correctly    over the lake\". " +
                                     "But we'll have to see about that when we get to the lake", "lAkE", 0));
        ResponseEntity expectedResponseEntity = ResponseEntity.ok(new WordFrequencyResponse("success", 2));
        assertEquals(actualResponseEntity, expectedResponseEntity);
    }

    private void testCalculateFrequencyForWordWithPunctuations() {
        ResponseEntity actualResponseEntity = wordFrequencyRepository.calculateFrequencyForWord(
            new WordFrequencyRequest("      \"the sun\", says: the traveller, \"shines, if I  , , remember correctly    over the lake\". " +
                                     "But we'll have to see about that when we get to the lake", "lake", 0));
        ResponseEntity expectedResponseEntity = ResponseEntity.ok(new WordFrequencyResponse("success", 2));
        assertEquals(actualResponseEntity, expectedResponseEntity);
    }

    private void testCalculateFrequencyForWordWithEmptyWord() {
        ResponseEntity actualResponseEntity = wordFrequencyRepository.calculateFrequencyForWord(
            new WordFrequencyRequest("The sun shines over the lake and the lake looks lake-ish. " +
                                     "But that's a lake for you.", "", 0));
        ResponseEntity expectedResponseEntity = ResponseEntity.ok(new WordFrequencyResponse("success", 0));
        assertEquals(actualResponseEntity, expectedResponseEntity);
    }

    private void testCalculateFrequencyForWordWithEmptySentence() {
        ResponseEntity actualResponseEntity = wordFrequencyRepository.calculateFrequencyForWord(
            new WordFrequencyRequest("", "lake", 0));
        ResponseEntity expectedResponseEntity = ResponseEntity.unprocessableEntity().body(new WordFrequencyResponse("failed"));
        assertEquals(actualResponseEntity, expectedResponseEntity);
    }

    private void testCalculateFrequencyForWordWithEmptyStrings() {
        ResponseEntity actualResponseEntity = wordFrequencyRepository.calculateFrequencyForWord(
            new WordFrequencyRequest("", "", 0));
        ResponseEntity expectedResponseEntity = ResponseEntity.unprocessableEntity().body(new WordFrequencyResponse("failed"));
        assertEquals(actualResponseEntity, expectedResponseEntity);
    }

    private void testCalculateFrequencyForWordWithNullSentence() {
        ResponseEntity actualResponseEntity = wordFrequencyRepository.calculateFrequencyForWord(
            new WordFrequencyRequest(null, "lake", 0));
        ResponseEntity expectedResponseEntity = ResponseEntity.unprocessableEntity().body(new WordFrequencyResponse("failed"));
        assertEquals(actualResponseEntity, expectedResponseEntity);
    }

    private void testCalculateFrequencyForWordWithNullWord() {
        ResponseEntity actualResponseEntity = wordFrequencyRepository.calculateFrequencyForWord(
            new WordFrequencyRequest("The sun shines over the lake and the lake looks lake-ish. " +
                                     "But that's a lake for you.", null, 0));
        ResponseEntity expectedResponseEntity = ResponseEntity.ok(new WordFrequencyResponse("success", 0));
        assertEquals(actualResponseEntity, expectedResponseEntity);
    }

    private void testCalculateFrequencyForWordWithNullRequest() {
        ResponseEntity actualResponseEntity = wordFrequencyRepository.calculateFrequencyForWord(null);
        ResponseEntity expectedResponseEntity = ResponseEntity.unprocessableEntity().body(new WordFrequencyResponse("failed"));
        assertEquals(actualResponseEntity, expectedResponseEntity);
    }

    private void testCalculateFrequencyForWordWithNullStrings() {
        ResponseEntity actualResponseEntity = wordFrequencyRepository.calculateFrequencyForWord(
            new WordFrequencyRequest(null, null, 0));
        ResponseEntity expectedResponseEntity = ResponseEntity.unprocessableEntity().body(new WordFrequencyResponse("failed"));
        assertEquals(actualResponseEntity, expectedResponseEntity);
    }

    @Test
    public void testCalculateMostFrequentNWords() {
        testCalculateMostFrequentNWordsWithNormalStrings();
        testCalculateMostFrequentNWordsWithCasedStrings();
        testCalculateMostFrequentNWordsWithLimitExceedingStringLength();
        testCalculateMostFrequentNWordsWithZeroLimit();
        testCalculateMostFrequentNWordsWithNegativeLimit();
        testCalculateMostFrequentNWordsWithEmptyString();
        testCalculateMostFrequentNWordsWithNullString();
        testCalculateMostFrequentNWordsWithNullRequest();
    }

    private void testCalculateMostFrequentNWordsWithNormalStrings() {
        ResponseEntity actualResponseEntity = wordFrequencyRepository.calculateMostFrequentNWords(
            new WordFrequencyRequest("The sun shines over the lake", null, 2));
        List<IWordFrequency> expectedWordFrequencies = new ArrayList<IWordFrequency>() {
            {
                add(new WordFrequency("the", 2));
                add(new WordFrequency("lake", 1));
                add(new WordFrequency("over", 1));
            }
        };
        ResponseEntity expectedResponseEntity = ResponseEntity.ok(new WordFrequencyResponse("success", expectedWordFrequencies));
        assertEquals(actualResponseEntity, expectedResponseEntity);
    }

    private void testCalculateMostFrequentNWordsWithCasedStrings() {
        ResponseEntity actualResponseEntity = wordFrequencyRepository.calculateMostFrequentNWords(
            new WordFrequencyRequest("The sun shines over tHe thE lake laKE", null, 2));
        List<IWordFrequency> expectedWordFrequencies = new ArrayList<IWordFrequency>() {
            {
                add(new WordFrequency("the", 3));
                add(new WordFrequency("lake", 2));
                add(new WordFrequency("over", 1));
            }
        };
        ResponseEntity expectedResponseEntity = ResponseEntity.ok(new WordFrequencyResponse("success", expectedWordFrequencies));
        assertEquals(actualResponseEntity, expectedResponseEntity);
    }

    private void testCalculateMostFrequentNWordsWithLimitExceedingStringLength() {
        ResponseEntity actualResponseEntity = wordFrequencyRepository.calculateMostFrequentNWords(
            new WordFrequencyRequest("The sun shines over tHe thE lake laKE", null, 20));
        List<IWordFrequency> expectedWordFrequencies = new ArrayList<IWordFrequency>() {
            {
                add(new WordFrequency("the", 3));
                add(new WordFrequency("lake", 2));
                add(new WordFrequency("over", 1));
                add(new WordFrequency("shines", 1));
                add(new WordFrequency("sun", 1));
            }
        };
        ResponseEntity expectedResponseEntity = ResponseEntity.ok(new WordFrequencyResponse("success", expectedWordFrequencies));
        assertEquals(actualResponseEntity, expectedResponseEntity);
    }

    private void testCalculateMostFrequentNWordsWithZeroLimit() {
        ResponseEntity actualResponseEntity = wordFrequencyRepository.calculateMostFrequentNWords(
            new WordFrequencyRequest("The sun shines over tHe thE lake laKE", null, 0));
        List<IWordFrequency> expectedWordFrequencies = Collections.emptyList();
        ResponseEntity expectedResponseEntity = ResponseEntity.ok(new WordFrequencyResponse("success", expectedWordFrequencies));
        assertEquals(actualResponseEntity, expectedResponseEntity);
    }

    private void testCalculateMostFrequentNWordsWithNegativeLimit() {
        ResponseEntity actualResponseEntity = wordFrequencyRepository.calculateMostFrequentNWords(
            new WordFrequencyRequest("The sun shines over tHe thE lake laKE", null, -2));
        List<IWordFrequency> expectedWordFrequencies = Collections.emptyList();
        ResponseEntity expectedResponseEntity = ResponseEntity.ok(new WordFrequencyResponse("success", expectedWordFrequencies));
        assertEquals(actualResponseEntity, expectedResponseEntity);
    }

    private void testCalculateMostFrequentNWordsWithEmptyString() {
        ResponseEntity actualResponseEntity = wordFrequencyRepository.calculateMostFrequentNWords(
            new WordFrequencyRequest("", null, 2));
        ResponseEntity expectedResponseEntity = ResponseEntity.unprocessableEntity().body(new WordFrequencyResponse("failed"));
        assertEquals(actualResponseEntity, expectedResponseEntity);
    }

    private void testCalculateMostFrequentNWordsWithNullString() {
        ResponseEntity actualResponseEntity = wordFrequencyRepository.calculateMostFrequentNWords(
            new WordFrequencyRequest(null, null, 2));
        ResponseEntity expectedResponseEntity = ResponseEntity.unprocessableEntity().body(new WordFrequencyResponse("failed"));
        assertEquals(actualResponseEntity, expectedResponseEntity);
    }

    private void testCalculateMostFrequentNWordsWithNullRequest() {
        ResponseEntity actualResponseEntity = wordFrequencyRepository.calculateMostFrequentNWords(null);
        ResponseEntity expectedResponseEntity = ResponseEntity.unprocessableEntity().body(new WordFrequencyResponse("failed"));
        assertEquals(actualResponseEntity, expectedResponseEntity);
    }
}
