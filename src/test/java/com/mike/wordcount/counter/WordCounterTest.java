package com.mike.wordcount.counter;

import com.mike.wordcount.stats.WordCountStats;
import com.mike.wordcount.util.Pair;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
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



    HashMap<Integer,Integer> exampleWordLengths;

    @BeforeEach
    public void setup(){
        setExampleFileContents("Hi There, hope you're having a nice day!");
        setExampleWords(new String[]{"Hi", "There", "Nice", "To", "See", "You"});

        setExampleWordLengths(new HashMap<Integer, Integer>() {{
            put(1, 3);
            put(2, 6);
            put(3, 3);
            put(4, 10);
            put(6, 2);
        }});
    }

    @Test
    public void shouldReturnCorrectNumberOfWords(){
        WordCountStats wordCountStats = wordCounter.getWordCountStatsFrom(exampleFileContents);

        assertThat(new Integer(wordCountStats.getNumberOfWords()), is(comparesEqualTo(new Integer(8))));
    }

    @Test
    public void shouldLoopThroughAllWordsAndCountLengths(){

        int[] exampleWordResults = {2,2,1,1};

        HashMap<Integer,Integer> wordLengths = wordCounter.getWordCountStats(exampleWords).getWordLengths();

        Iterator exampleWordLengthsIterator = wordLengths.entrySet().iterator();

        int i=0;
        while (exampleWordLengthsIterator.hasNext()) {
            Map.Entry mapElement = (Map.Entry)exampleWordLengthsIterator.next();

            assertEquals(mapElement.getValue(),new Integer(exampleWordResults[i]));
            i++;
        }
    }

    @Test
    public void shouldLoopThroughAllWordsAndCalculateTheAverageLength(){

        double exampleAverageWordLength = 19d / 6d;
        double averageWordLength = wordCounter.getWordCountStats(exampleWords).getAverageWordLength();

        assertEquals(String.format("%.3f",averageWordLength),String.format("%.3f",exampleAverageWordLength));

    }

    @Test
    public void shouldRemoveFullStopsFromWords(){
        String exampleWord = "testword.";
        String formattedWord = wordCounter.removeFullStops(exampleWord);

        assertThat(formattedWord, is("testword"));
    }

    @Test
    public void shouldNotAddFullStopsToWords(){
        String exampleWord = "testword";
        String formattedWord = wordCounter.removeFullStops(exampleWord);

        assertThat(formattedWord, is("testword"));
    }

    @Test
    public void shouldCalculateTheMostCommonOccuringLength(){

        Pair exampleCommonWordLengthPair = new Pair(4,10);
        ArrayList<Pair> exampleCommonWordLengths = new ArrayList<Pair>();
        exampleCommonWordLengths.add(exampleCommonWordLengthPair);

        ArrayList<Pair> mostCommonLengths = wordCounter.calculateMostCommonOccurringLength(getExampleWordLengths());


        assertThat(mostCommonLengths, is(exampleCommonWordLengths));
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

    public HashMap<Integer, Integer> getExampleWordLengths() {
        return exampleWordLengths;
    }

    public void setExampleWordLengths(HashMap<Integer, Integer> exampleWordLengths) {
        this.exampleWordLengths = exampleWordLengths;
    }
}
