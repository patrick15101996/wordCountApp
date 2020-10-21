package com.ordina.api.utils;

import java.util.stream.Stream;

public class StringUtil {
    public static boolean isEmptyOrNull(String... strings) {
        if (strings == null || strings.length == 0) {
            return true;
        }
        return Stream.of(strings).anyMatch(StringUtil::isEmptyOrNull);
    }

    private static boolean isEmptyOrNull(String string) {
        return string == null || string.isEmpty();
    }

    public static String[] splitSentence(String sentence) {
        if (!StringUtil.isEmptyOrNull(sentence)) {
            sentence = sentence.trim().toLowerCase();
            String[] splitSentence = sentence.split("\\P{L}+");
            splitSentence = Stream.of(splitSentence).filter(word -> !StringUtil.isEmptyOrNull(word)).toArray(String[]::new);
            return splitSentence;
        }
        return new String[] {};
    }
}
