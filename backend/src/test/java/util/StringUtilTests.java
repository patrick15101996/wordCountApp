package util;

import org.junit.Test;
import utils.StringUtil;
import static org.junit.Assert.*;

public class StringUtilTests {
    @Test
    public void testSplitSentence() {
        testSplitSentenceWithoutPunctuation();
        testSplitSentenceWithQuotations();
        testSplitSentenceWithMultipleCharacters();
    }

    private void testSplitSentenceWithoutPunctuation() {
        String sentence = "the sun shines over the lake";
        String[] splitSentence = StringUtil.splitSentence(sentence);
        String[] expectedSplitSentence = new String[] { "the", "sun", "shines", "over", "the", "lake" };
        assertArrayEquals(expectedSplitSentence, splitSentence);
    }

    private void testSplitSentenceWithQuotations() {
        String sentence = "\"the sun\", says the traveller, \"shines, if I remember correctly over the lake\"";
        String[] splitSentence = StringUtil.splitSentence(sentence);
        String[] expectedSplitSentence = new String[] { "the", "sun", "says", "the", "traveller", "shines", "if", "i", "remember",
            "correctly", "over", "the", "lake" };
        assertArrayEquals(expectedSplitSentence, splitSentence);
    }

    private void testSplitSentenceWithMultipleCharacters() {
        String sentence = "      \"the sun\", says: the traveller, \"shines, if I  , , remember correctly    over the lake\". " +
                          "But we'll have to see about that";
        String[] splitSentence = StringUtil.splitSentence(sentence);
        String[] expectedSplitSentence = new String[] { "the", "sun", "says", "the", "traveller", "shines", "if", "i", "remember",
            "correctly", "over", "the", "lake", "but", "we", "ll", "have", "to", "see", "about", "that" };
        assertArrayEquals(expectedSplitSentence, splitSentence);
    }

    @Test
    public void testIsEmptyOrNull() {
        testIsEmptyOrNullWithNull();
        testIsEmptyOrNullWithNullAndNormalStrings();
        testIsEmptyOrNullWithEmptyString();
        testIsEmptyOrNullWithEmptyAndNormalStrings();
        testIsEmptyOrNullWithSingleString();
        testIsEmptyOrNullWithMultipleStrings();
    }

    private void testIsEmptyOrNullWithNull() {
        boolean actual = StringUtil.isEmptyOrNull((String) null); //Cast to String to suppress warning
        assertTrue(actual);
    }

    private void testIsEmptyOrNullWithNullAndNormalStrings() {
        boolean actual = StringUtil.isEmptyOrNull(null, "testString", null);
        assertTrue(actual);
    }

    private void testIsEmptyOrNullWithEmptyString() {
        boolean actual = StringUtil.isEmptyOrNull("", " ");
        assertTrue(actual);
    }

    private void testIsEmptyOrNullWithEmptyAndNormalStrings() {
        boolean actual = StringUtil.isEmptyOrNull("", " ", "testString");
        assertTrue(actual);
    }

    private void testIsEmptyOrNullWithSingleString() {
        boolean actual = StringUtil.isEmptyOrNull("testString");
        assertFalse(actual);
    }

    private void testIsEmptyOrNullWithMultipleStrings() {
        boolean actual = StringUtil.isEmptyOrNull("testString", "testString", "testString");
        assertFalse(actual);
    }
}
