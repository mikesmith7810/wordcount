package com.mike.wordcount.counter;

import com.mike.wordcount.stats.WordCountStats;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class WordCounter {
    public WordCountStats getWordCountStatsFrom(String parsedFileContents) {
        String[] words = parsedFileContents.split(" ");

        WordCountStats wordCountStats = new WordCountStats();
        wordCountStats.setNumberOfWords(words.length);
        wordCountStats.setWordLengths(getWordLengthsFrom(words));
        return wordCountStats;
    }

    public HashMap<Integer, Integer> getWordLengthsFrom(String[] exampleWords) {
        HashMap<Integer, Integer> wordLengths = new HashMap<Integer, Integer>();

        for (int i=0;i<exampleWords.length;i++){
            int lengthOfWord = exampleWords[i].length();
            if (wordLengths.get(lengthOfWord)==null)
                wordLengths.put(lengthOfWord,1);
            else {
                int currentNumberOfWordsWithLength = wordLengths.get(lengthOfWord);
                wordLengths.put(lengthOfWord,currentNumberOfWordsWithLength + 1);
            }
        }
        return wordLengths;
    }
}
