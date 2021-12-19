package com.mike.wordcount.controller;

import com.mike.wordcount.counter.WordCounter;
import com.mike.wordcount.reader.FileParser;
import com.mike.wordcount.stats.WordCountStats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Iterator;
import java.util.Map;


@RestController
public class WordCountController {

    public static final String CARRIAGE_RETURN = "<br>";
    public static final String COMMA = ", ";
    public static final String AMPERSAND = " & ";
    @Autowired
    private FileParser fileParser;

    @Autowired
    private WordCounter wordCounter;

    @PostMapping("/fileupload")
    public String uploadFile(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {

        String parsedFile = getFileParser().parseFile(file);
        WordCountStats wordCountStats = getWordCounter().getWordCountStatsFrom(parsedFile);


        String textOutput =
                "Word count = "+ wordCountStats.getNumberOfWords() + CARRIAGE_RETURN +
                "Average word length = "+ String.format("%.3f",wordCountStats.getAverageWordLength()) + CARRIAGE_RETURN;

        Iterator wordLengthsIterator = wordCountStats.getWordLengths().entrySet().iterator();

        while (wordLengthsIterator.hasNext()) {
            Map.Entry mapElement = (Map.Entry)wordLengthsIterator.next();
            textOutput = textOutput + "Number of words of length " + mapElement.getKey() + " is "+ mapElement.getValue() + CARRIAGE_RETURN;
        }
            textOutput = textOutput +
            "The most frequently occurring word length is " +
            wordCountStats.getMostFrequentlyOccuringLength().get(0).getValue() + COMMA +
            "for word lengths of ";
            for (int i=0;i<wordCountStats.getMostFrequentlyOccuringLength().size();i++){
                if (i!=wordCountStats.getMostFrequentlyOccuringLength().size()-1)
                    textOutput = textOutput + wordCountStats.getMostFrequentlyOccuringLength().get(i).getKey() + AMPERSAND;
                else
                    textOutput = textOutput + wordCountStats.getMostFrequentlyOccuringLength().get(i).getKey();
            }


        return textOutput ;
    }

    @PostMapping("/fileuploadjson")
    @ResponseBody
    public WordCountStats uploadFileForJSON(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {

        String parsedFile = getFileParser().parseFile(file);
        WordCountStats wordCountStats = getWordCounter().getWordCountStatsFrom(parsedFile);
        System.out.println(parsedFile);
        System.out.println(wordCountStats.getNumberOfWords());
        System.out.println(String.format("%.3f",wordCountStats.getAverageWordLength()));
        Iterator wordLengthsIterator = wordCountStats.getWordLengths().entrySet().iterator();


        while (wordLengthsIterator.hasNext()) {
            Map.Entry mapElement = (Map.Entry)wordLengthsIterator.next();
            System.out.println("Number of Words with a length of " + mapElement.getKey() + " is "+mapElement.getValue());
        }

        return wordCountStats ;
    }

    public FileParser getFileParser() {
        return fileParser;
    }

    public void setFileParser(FileParser fileParser) {
        this.fileParser = fileParser;
    }

    public WordCounter getWordCounter() {
        return wordCounter;
    }

    public void setWordCounter(WordCounter wordCounter) {
        this.wordCounter = wordCounter;
    }
}
