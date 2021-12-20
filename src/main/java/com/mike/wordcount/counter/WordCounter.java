package com.mike.wordcount.counter;

import com.mike.wordcount.stats.WordCountStats;

import com.mike.wordcount.util.Pair;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

@Service
public class WordCounter {
    public WordCountStats getWordCountStatsFrom(String parsedFileContents) {
        String[] words = parsedFileContents.split(" +");

        return getWordCountStats(words);
    }

    public WordCountStats getWordCountStats(String[] exampleWords) {

        WordCountStats wordCountStats = new WordCountStats();

        wordCountStats.setNumberOfWords(exampleWords.length);

        HashMap<Integer, Integer> wordLengths = new HashMap<Integer, Integer>();

        String formattedWord = "";
        int totalLength = 0;

        for (int i=0;i<exampleWords.length;i++){

            formattedWord = removeFullStops(exampleWords[i]);

            int lengthOfWord = formattedWord.length();

            if (wordLengths.get(lengthOfWord)==null) {
                wordLengths.put(lengthOfWord, 1);
            }
            else {
                int currentNumberOfWordsWithLength = wordLengths.get(lengthOfWord);
                wordLengths.put(lengthOfWord,currentNumberOfWordsWithLength + 1);
            }

            totalLength = totalLength + lengthOfWord;
        }

        wordCountStats.setWordLengths(wordLengths);

        double totalLengthDouble = new Double(totalLength);
        double totalNumberOfWords = new Double(exampleWords.length);
        double averageLength = totalLengthDouble / totalNumberOfWords;

        wordCountStats.setAverageWordLength(averageLength);

        wordCountStats.setMostFrequentlyOccuringLength(calculateMostCommonOccurringLength(wordLengths));

        return wordCountStats;
    }

    public String removeFullStops(String word) {

        if (word.endsWith("."))
            word = word.substring(0,word.length()-1);

        return word;
    }

    public ArrayList<Pair> calculateMostCommonOccurringLength(HashMap<Integer, Integer> wordLengths) {

        Iterator wordLengthsIterator = wordLengths.entrySet().iterator();
        ArrayList<Pair> mostOccuringLengths = new ArrayList<>();
        int occurancesOfLength = 0;

        while (wordLengthsIterator.hasNext()){

            HashMap.Entry lengthOccuranceEntry = (HashMap.Entry)wordLengthsIterator.next();
            int currentLength = (Integer)lengthOccuranceEntry.getKey();
            int currentOccurancesOfLength = (Integer)lengthOccuranceEntry.getValue();

            if (currentOccurancesOfLength>occurancesOfLength) {
                mostOccuringLengths = new ArrayList<>();
                mostOccuringLengths.add(new Pair(currentLength,currentOccurancesOfLength));
                occurancesOfLength = currentOccurancesOfLength;
            }
            else if (currentOccurancesOfLength==occurancesOfLength) {
                mostOccuringLengths.add(new Pair(currentLength,currentOccurancesOfLength));
                occurancesOfLength = currentOccurancesOfLength;
            }
        }
        return mostOccuringLengths;
    }
}
