package com.mike.wordcount.counter;

import com.mike.wordcount.stats.WordCountStats;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.HashMap;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.comparesEqualTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
public class WordCounterTest {

    WordCounter wordCounter = new WordCounter();

    private String exampleFileContents;



    private String[] exampleWords;

    @BeforeEach
    public void setup(){
        setExampleFileContents("Hi There, hope you're having a nice day!");
        setExampleWords(new String[]{"Hi", "There"});
    }

    @Test
    public void shouldReturnCorrectNumberOfWords(){
        WordCountStats wordCountStats = wordCounter.getWordCountStatsFrom(exampleFileContents);

        assertThat(new Integer(wordCountStats.getNumberOfWords()), is(comparesEqualTo(new Integer(8))));
    }

    @Test
    public void shouldLoopThroughAllWordsAndCountLengths(){

        HashMap<Integer,Integer> exampleWordLengths = wordCounter.getLengthsOfWords(exampleWords);

        for(int i=0;i<getExampleWords().length;i++){
            assertEquals(exampleWordLengths.get(getExampleWords()[i].length()),new Integer(getExampleWords()[i].length()));
        }
    }

    public String getExampleFileContents() {
        return exampleFileContents;
    }

    public void setExampleFileContents(String exampleFileContents) {
        this.exampleFileContents = exampleFileContents;
    }

    public String[] getExampleWords() {
        return exampleWords;
    }

    public void setExampleWords(String[] exampleWords) {
        this.exampleWords = exampleWords;
    }
}
