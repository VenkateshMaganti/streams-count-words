package com.epam.rd.autotasks;


import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Words
{
    public static final String WHITESPACES = "\\s+";
    public static final String WHITESPACE = " ";
    public static final int MIN_LENGTH = 4;
    public static final int MIN_COUNTER = 10;
    public static final String REGEX_WITHOUT_LETTERS = "\\P{LD}+";
    public static final String STRING_FORMAT = "%s - %d\n";

    public String countWords(List<String> lines)
    {
        String stringResult = lines
                .stream()
                .map(str -> str.replaceAll(REGEX_WITHOUT_LETTERS, WHITESPACE))
                .flatMap(str -> Arrays.stream(str.toLowerCase().split(WHITESPACES)))
                .filter(str -> str.length() >= MIN_LENGTH)
                .collect(Collectors.groupingBy(s -> s, Collectors.counting()))
                .entrySet().stream()
                .filter(entry -> entry.getValue() >= MIN_COUNTER)
                .sorted(Map.Entry.comparingByKey())
                .sorted((entry1, entry2) -> (int) (entry2.getValue() - entry1.getValue()))
                .map(entry -> String.format(STRING_FORMAT, entry.getKey(), entry.getValue()))
                .collect(Collectors.joining());

        return delLastChar(stringResult);
    }

    private String delLastChar(String stringResult)
    {
        StringBuilder stringBuilder = new StringBuilder(stringResult);
        return String.valueOf(stringBuilder.deleteCharAt(stringBuilder.length() - 1));
    }
}

//package com.epam.rd.autotasks;
////
////
////import java.util.Arrays;
////import java.util.List;
////import java.util.Map;
////import java.util.stream.Collectors;
////
////public class Words {
////    //private static final String REGEX = "\\P{javaLetter}+";
////
////    private static final String REGEX = "^[A-Za-z]+$";
////    private static final int MIN_LENGTH_WORD = 4;
////    private static final int MIN_AMOUNT_WORDS = 10;
////
////    public String countWords(List<String> lines) {
////        //String string = lines.;
////        String string = lines.stream()
////                .flatMap(line -> Arrays.stream(line.split(REGEX))
////                        .map(String::toLowerCase))
////                .filter(word -> word.length() >= MIN_LENGTH_WORD)
////                .collect(Collectors.groupingBy(word -> word, Collectors.counting()))
////                .entrySet()
////                .stream()
////                .filter(collectorListEntry -> collectorListEntry.getValue() >= MIN_AMOUNT_WORDS)
////                .sorted(Map.Entry.comparingByKey())
////                .sorted((o1, o2) -> o2.getValue().compareTo(o1.getValue()))
////                .map(stringLongEntry -> String.format("%s - %d\n", stringLongEntry.getKey(), stringLongEntry.getValue()))
////                .collect(Collectors.joining());
////        StringBuilder stringBuilder = removeLastChar(string);
////        return stringBuilder.toString();
////    }
////
////    private StringBuilder removeLastChar(String string) {
////        StringBuilder stringBuilder = new StringBuilder(string);
////        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
////        return stringBuilder;
////    }
////}
//
//import java.text.Collator;
//import java.util.Comparator;
//import java.util.HashMap;
//import java.util.Iterator;
//import java.util.LinkedList;
//import java.util.List;
//import java.util.Locale;
//import java.util.Map;
//import java.util.Map.Entry;
//
//public class Words {
//
//    public String countWords(List<String> lines) {
//        /*
//         * Calculate the frequency of each word
//         */
//        Map<String, Integer> frequency = new HashMap<>();
//        for (String each : lines) {
//            each = each.replaceAll("[“”]", "\"").replaceAll("[‘’]", "'").replaceAll("„", ",").replaceAll("—",
//                    "-");
//            String[] words = each.split("[ -/]");
//            for (String ech : words) {
//                if (ech != null && !ech.trim().isEmpty() && ech.length() > 2) {
//                    ech = normalizeWord(ech);
//                    if (frequency.containsKey(ech)) {
//                        int count = frequency.get(ech) + 1;
//                        frequency.put(ech, count);
//                    } else {
//                        frequency.put(ech, 1);
//                    }
//                }
//            }
//        }
//
//        /*
//         * Remove the word if its rare or length is too small
//         */
//        Iterator<Map.Entry<String, Integer>> it = frequency.entrySet().iterator();
//        while (it.hasNext()) {
//            Map.Entry<String, Integer> pair = it.next();
//            if (pair.getKey().length() < 4 || pair.getValue() < 10) {
//                it.remove();
//            }
//        }
//
//        /*
//         * Sort the words by value and if they are equal, sort by name
//         */
//        Locale locale = Locale.US;
//        Collator col = Collator.getInstance(locale);
//
//        List<Entry<String, Integer>> wordList = new LinkedList<>(frequency.entrySet());
//        wordList.sort(new Comparator<Entry<String, Integer>>() {
//            public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
//                int i;
//                if (o1.getValue().equals(o2.getValue())) {
//                    i = o1.getKey().compareTo(o2.getKey());
//                } else {
//                    i = o2.getValue() - o1.getValue();
//                }
//                return i;
//            }
//        });
//
//        /*
//         * Build the output string
//         */
//        StringBuilder output = new StringBuilder();
//        for (Map.Entry<String, Integer> each : wordList) {
//            output.append(each.getKey()).append(" - ").append(each.getValue()).append("\n");
//        }
//        output = new StringBuilder(output.substring(0, output.length() - 1));
//        return output.toString();
//    }
//
//    private String normalizeWord(String ech) {
//        if (ech.contains("'s") || ech.contains("'es") || ech.contains("'t") || ech.contains("'ll")) {
//            ech = ech.replaceAll("'s", "").replaceAll("'es", "").replaceAll("'t", "").replaceAll("'ll", "");
//        }
//        ech = ech.replaceAll("[.\" '<>()\\[\\]!@#$%^&*;:,?“”‘’„—]", "").toLowerCase();
//        ech = ech.trim();
//        return ech;
//    }
//
//}
