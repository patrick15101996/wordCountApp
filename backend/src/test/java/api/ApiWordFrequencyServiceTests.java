package api;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.ordina.api.interfaces.IWordFrequency;
import com.ordina.api.interfaces.IWordFrequencyProcessor;
import com.ordina.api.interfaces.IWordFrequencyService;
import com.ordina.api.models.WordFrequency;
import com.ordina.api.models.WordFrequencyRequest;
import com.ordina.api.models.WordFrequencyResponse;
import com.ordina.api.processors.WordFrequencyProcessor;
import com.ordina.api.services.WordFrequencyService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.ResponseEntity;
import static org.junit.Assert.assertEquals;

public class ApiWordFrequencyServiceTests {
    private IWordFrequencyProcessor wordFrequencyProcessor;

    @Before
    public void setup() {
        IWordFrequencyService wordFrequencyService = new WordFrequencyService();
        wordFrequencyProcessor = new WordFrequencyProcessor(wordFrequencyService);
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
        ResponseEntity<WordFrequencyResponse> actualResponseEntity = wordFrequencyProcessor.calculateHighestFrequency(
            new WordFrequencyRequest("The sun shines over the lake", null, 0));
        ResponseEntity<WordFrequencyResponse> expectedResponseEntity = ResponseEntity.ok(new WordFrequencyResponse("success", 2));
        assertEquals(actualResponseEntity, expectedResponseEntity);
    }

    private void testCalculateHighestFrequencyWithCasedString() {
        ResponseEntity<WordFrequencyResponse> actualResponseEntity = wordFrequencyProcessor.calculateHighestFrequency(
            new WordFrequencyRequest("The sun shines over tHe thE lake", null, 0));
        ResponseEntity<WordFrequencyResponse> expectedResponseEntity = ResponseEntity.ok(new WordFrequencyResponse("success", 3));
        assertEquals(actualResponseEntity, expectedResponseEntity);
    }

    private void testCalculateHighestFrequencyWithOnlyPunctuations() {
        ResponseEntity<WordFrequencyResponse> actualResponseEntity = wordFrequencyProcessor.calculateHighestFrequency(
            new WordFrequencyRequest("... ,, \"\" :{}:><?", null, 0));
        ResponseEntity<WordFrequencyResponse> expectedResponseEntity = ResponseEntity.ok(new WordFrequencyResponse("success", 0));
        assertEquals(actualResponseEntity, expectedResponseEntity);
    }

    private void testCalculateHighestFrequencyWithEmptyString() {
        ResponseEntity<WordFrequencyResponse> actualResponseEntity = wordFrequencyProcessor.calculateHighestFrequency(
            new WordFrequencyRequest("", null, 0));
        ResponseEntity<WordFrequencyResponse> expectedResponseEntity = ResponseEntity.unprocessableEntity().body(new WordFrequencyResponse("failed"));
        assertEquals(actualResponseEntity, expectedResponseEntity);
    }

    private void testCalculateHighestFrequencyWithNullRequest() {
        ResponseEntity<WordFrequencyResponse> actualResponseEntity = wordFrequencyProcessor.calculateHighestFrequency(null);
        ResponseEntity<WordFrequencyResponse> expectedResponseEntity = ResponseEntity.unprocessableEntity().body(new WordFrequencyResponse("failed"));
        assertEquals(actualResponseEntity, expectedResponseEntity);
    }

    private void testCalculateHighestFrequencyWithNull() {
        ResponseEntity<WordFrequencyResponse> actualResponseEntity = wordFrequencyProcessor.calculateHighestFrequency(
            new WordFrequencyRequest(null, null, 0));
        ResponseEntity<WordFrequencyResponse> expectedResponseEntity = ResponseEntity.unprocessableEntity().body(new WordFrequencyResponse("failed"));
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
        ResponseEntity<WordFrequencyResponse> actualResponseEntity = wordFrequencyProcessor.calculateFrequencyForWord(
            new WordFrequencyRequest("The sun shines over the lake and the lake looks lake-ish. " +
                                     "But that's a lake for you.", "lake", 0));
        ResponseEntity<WordFrequencyResponse> expectedResponseEntity = ResponseEntity.ok(new WordFrequencyResponse("success", 4));
        assertEquals(actualResponseEntity, expectedResponseEntity);
    }

    private void testCalculateFrequencyForWordWithCasedSentence() {
        ResponseEntity<WordFrequencyResponse> actualResponseEntity = wordFrequencyProcessor.calculateFrequencyForWord(
            new WordFrequencyRequest("      \"the sun\", says: the traveller, \"shines, if I  , , remember correctly    over the laKe\". " +
                                     "But we'll have to see about that when we get to the lAke", "lake", 0));
        ResponseEntity<WordFrequencyResponse> expectedResponseEntity = ResponseEntity.ok(new WordFrequencyResponse("success", 2));
        assertEquals(actualResponseEntity, expectedResponseEntity);
    }

    private void testCalculateFrequencyForWordWithCasedWord() {
        ResponseEntity<WordFrequencyResponse> actualResponseEntity = wordFrequencyProcessor.calculateFrequencyForWord(
            new WordFrequencyRequest("      \"the sun\", says: the traveller, \"shines, if I  , , remember correctly    over the lake\". " +
                                     "But we'll have to see about that when we get to the lake", "lAkE", 0));
        ResponseEntity<WordFrequencyResponse> expectedResponseEntity = ResponseEntity.ok(new WordFrequencyResponse("success", 2));
        assertEquals(actualResponseEntity, expectedResponseEntity);
    }

    private void testCalculateFrequencyForWordWithPunctuations() {
        ResponseEntity<WordFrequencyResponse> actualResponseEntity = wordFrequencyProcessor.calculateFrequencyForWord(
            new WordFrequencyRequest("      \"the sun\", says: the traveller, \"shines, if I  , , remember correctly    over the lake\". " +
                                     "But we'll have to see about that when we get to the lake", "lake", 0));
        ResponseEntity<WordFrequencyResponse> expectedResponseEntity = ResponseEntity.ok(new WordFrequencyResponse("success", 2));
        assertEquals(actualResponseEntity, expectedResponseEntity);
    }

    private void testCalculateFrequencyForWordWithEmptyWord() {
        ResponseEntity<WordFrequencyResponse> actualResponseEntity = wordFrequencyProcessor.calculateFrequencyForWord(
            new WordFrequencyRequest("The sun shines over the lake and the lake looks lake-ish. " +
                                     "But that's a lake for you.", "", 0));
        ResponseEntity<WordFrequencyResponse> expectedResponseEntity = ResponseEntity.ok(new WordFrequencyResponse("success", 0));
        assertEquals(actualResponseEntity, expectedResponseEntity);
    }

    private void testCalculateFrequencyForWordWithEmptySentence() {
        ResponseEntity<WordFrequencyResponse> actualResponseEntity = wordFrequencyProcessor.calculateFrequencyForWord(
            new WordFrequencyRequest("", "lake", 0));
        ResponseEntity<WordFrequencyResponse> expectedResponseEntity = ResponseEntity.unprocessableEntity().body(new WordFrequencyResponse("failed"));
        assertEquals(actualResponseEntity, expectedResponseEntity);
    }

    private void testCalculateFrequencyForWordWithEmptyStrings() {
        ResponseEntity<WordFrequencyResponse> actualResponseEntity = wordFrequencyProcessor.calculateFrequencyForWord(
            new WordFrequencyRequest("", "", 0));
        ResponseEntity<WordFrequencyResponse> expectedResponseEntity = ResponseEntity.unprocessableEntity().body(new WordFrequencyResponse("failed"));
        assertEquals(actualResponseEntity, expectedResponseEntity);
    }

    private void testCalculateFrequencyForWordWithNullSentence() {
        ResponseEntity<WordFrequencyResponse> actualResponseEntity = wordFrequencyProcessor.calculateFrequencyForWord(
            new WordFrequencyRequest(null, "lake", 0));
        ResponseEntity<WordFrequencyResponse> expectedResponseEntity = ResponseEntity.unprocessableEntity().body(new WordFrequencyResponse("failed"));
        assertEquals(actualResponseEntity, expectedResponseEntity);
    }

    private void testCalculateFrequencyForWordWithNullWord() {
        ResponseEntity<WordFrequencyResponse> actualResponseEntity = wordFrequencyProcessor.calculateFrequencyForWord(
            new WordFrequencyRequest("The sun shines over the lake and the lake looks lake-ish. " +
                                     "But that's a lake for you.", null, 0));
        ResponseEntity<WordFrequencyResponse> expectedResponseEntity = ResponseEntity.ok(new WordFrequencyResponse("success", 0));
        assertEquals(actualResponseEntity, expectedResponseEntity);
    }

    private void testCalculateFrequencyForWordWithNullRequest() {
        ResponseEntity<WordFrequencyResponse> actualResponseEntity = wordFrequencyProcessor.calculateFrequencyForWord(null);
        ResponseEntity<WordFrequencyResponse> expectedResponseEntity = ResponseEntity.unprocessableEntity().body(new WordFrequencyResponse("failed"));
        assertEquals(actualResponseEntity, expectedResponseEntity);
    }

    private void testCalculateFrequencyForWordWithNullStrings() {
        ResponseEntity<WordFrequencyResponse> actualResponseEntity = wordFrequencyProcessor.calculateFrequencyForWord(
            new WordFrequencyRequest(null, null, 0));
        ResponseEntity<WordFrequencyResponse> expectedResponseEntity = ResponseEntity.unprocessableEntity().body(new WordFrequencyResponse("failed"));
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
        ResponseEntity<WordFrequencyResponse> actualResponseEntity = wordFrequencyProcessor.calculateMostFrequentNWords(
            new WordFrequencyRequest("The sun shines over the lake", null, 2));
        List<IWordFrequency> expectedWordFrequencies = new ArrayList<>() {
            {
                add(new WordFrequency("the", 2));
                add(new WordFrequency("lake", 1));
                add(new WordFrequency("over", 1));
            }
        };
        ResponseEntity<WordFrequencyResponse> expectedResponseEntity = ResponseEntity.ok(new WordFrequencyResponse("success", expectedWordFrequencies));
        assertEquals(actualResponseEntity, expectedResponseEntity);
    }

    private void testCalculateMostFrequentNWordsWithCasedStrings() {
        ResponseEntity<WordFrequencyResponse> actualResponseEntity = wordFrequencyProcessor.calculateMostFrequentNWords(
            new WordFrequencyRequest("The sun shines over tHe thE lake laKE", null, 2));
        List<IWordFrequency> expectedWordFrequencies = new ArrayList<>() {
            {
                add(new WordFrequency("the", 3));
                add(new WordFrequency("lake", 2));
                add(new WordFrequency("over", 1));
            }
        };
        ResponseEntity<WordFrequencyResponse> expectedResponseEntity = ResponseEntity.ok(new WordFrequencyResponse("success", expectedWordFrequencies));
        assertEquals(actualResponseEntity, expectedResponseEntity);
    }

    private void testCalculateMostFrequentNWordsWithLimitExceedingStringLength() {
        ResponseEntity<WordFrequencyResponse> actualResponseEntity = wordFrequencyProcessor.calculateMostFrequentNWords(
            new WordFrequencyRequest("The sun shines over tHe thE lake laKE", null, 20));
        List<IWordFrequency> expectedWordFrequencies = new ArrayList<>() {
            {
                add(new WordFrequency("the", 3));
                add(new WordFrequency("lake", 2));
                add(new WordFrequency("over", 1));
                add(new WordFrequency("shines", 1));
                add(new WordFrequency("sun", 1));
            }
        };
        ResponseEntity<WordFrequencyResponse> expectedResponseEntity = ResponseEntity.ok(new WordFrequencyResponse("success", expectedWordFrequencies));
        assertEquals(actualResponseEntity, expectedResponseEntity);
    }

    private void testCalculateMostFrequentNWordsWithZeroLimit() {
        ResponseEntity<WordFrequencyResponse> actualResponseEntity = wordFrequencyProcessor.calculateMostFrequentNWords(
            new WordFrequencyRequest("The sun shines over tHe thE lake laKE", null, 0));
        List<IWordFrequency> expectedWordFrequencies = Collections.emptyList();
        ResponseEntity<WordFrequencyResponse> expectedResponseEntity = ResponseEntity.ok(new WordFrequencyResponse("success", expectedWordFrequencies));
        assertEquals(actualResponseEntity, expectedResponseEntity);
    }

    private void testCalculateMostFrequentNWordsWithNegativeLimit() {
        ResponseEntity<WordFrequencyResponse> actualResponseEntity = wordFrequencyProcessor.calculateMostFrequentNWords(
            new WordFrequencyRequest("The sun shines over tHe thE lake laKE", null, -2));
        List<IWordFrequency> expectedWordFrequencies = Collections.emptyList();
        ResponseEntity<WordFrequencyResponse> expectedResponseEntity = ResponseEntity.ok(new WordFrequencyResponse("success", expectedWordFrequencies));
        assertEquals(actualResponseEntity, expectedResponseEntity);
    }

    private void testCalculateMostFrequentNWordsWithEmptyString() {
        ResponseEntity<WordFrequencyResponse> actualResponseEntity = wordFrequencyProcessor.calculateMostFrequentNWords(
            new WordFrequencyRequest("", null, 2));
        ResponseEntity<WordFrequencyResponse> expectedResponseEntity = ResponseEntity.unprocessableEntity().body(new WordFrequencyResponse("failed"));
        assertEquals(actualResponseEntity, expectedResponseEntity);
    }

    private void testCalculateMostFrequentNWordsWithNullString() {
        ResponseEntity<WordFrequencyResponse> actualResponseEntity = wordFrequencyProcessor.calculateMostFrequentNWords(
            new WordFrequencyRequest(null, null, 2));
        ResponseEntity<WordFrequencyResponse> expectedResponseEntity = ResponseEntity.unprocessableEntity().body(new WordFrequencyResponse("failed"));
        assertEquals(actualResponseEntity, expectedResponseEntity);
    }

    private void testCalculateMostFrequentNWordsWithNullRequest() {
        ResponseEntity<WordFrequencyResponse> actualResponseEntity = wordFrequencyProcessor.calculateMostFrequentNWords(null);
        ResponseEntity<WordFrequencyResponse> expectedResponseEntity = ResponseEntity.unprocessableEntity().body(new WordFrequencyResponse("failed"));
        assertEquals(actualResponseEntity, expectedResponseEntity);
    }
}
