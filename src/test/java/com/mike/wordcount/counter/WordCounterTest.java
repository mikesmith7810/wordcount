package com.mike.wordcount.counter;

import com.mike.wordcount.stats.WordCountStats;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

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
        setExampleWords(new String[]{"Hi", "There", "Nice", "To", "See", "You"});
    }

    @Test
    public void shouldReturnCorrectNumberOfWords(){
        WordCountStats wordCountStats = wordCounter.getWordCountStatsFrom(exampleFileContents);

        assertThat(new Integer(wordCountStats.getNumberOfWords()), is(comparesEqualTo(new Integer(8))));
    }

    @Test
    public void shouldLoopThroughAllWordsAndCountLengths(){

        int[] exampleWordResults = {2,2,1,1};

        HashMap<Integer,Integer> exampleWordLengths = wordCounter.getWordLengthsFrom(exampleWords);

        Iterator exampleWordLengthsIterator = exampleWordLengths.entrySet().iterator();

        int i=0;
        while (exampleWordLengthsIterator.hasNext()) {
            Map.Entry mapElement = (Map.Entry)exampleWordLengthsIterator.next();

            assertEquals(mapElement.getValue(),new Integer(exampleWordResults[i]));
            i++;
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
