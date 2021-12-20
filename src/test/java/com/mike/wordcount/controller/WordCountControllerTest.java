package com.mike.wordcount.controller;
import com.mike.wordcount.counter.WordCounter;
import com.mike.wordcount.reader.FileParser;
import com.mike.wordcount.stats.WordCountStats;
import com.mike.wordcount.util.Pair;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import java.util.ArrayList;
import java.util.HashMap;

import static org.mockito.Mockito.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
public class WordCountControllerTest {

    WordCountController wordCountController = new WordCountController();

    @Mock
    private FileParser mockFileParser;

    @Mock
    private MultipartFile mockMultipartFile;

    @Mock
    private RedirectAttributes mockRedirectAttributes;

    @Mock
    private WordCounter mockWordCounter;

    private WordCountStats exampleWordCountStats;

    @BeforeEach
    public void setup(){
        wordCountController.setFileParser(mockFileParser);
        wordCountController.setWordCounter(mockWordCounter);

        createMockMultipartFile();
        exampleWordCountStats = new WordCountStats();
        exampleWordCountStats.setNumberOfWords(3);
        exampleWordCountStats.setFileContent("Hi There");
        exampleWordCountStats.setWordLengths(new HashMap<Integer, Integer>());
        ArrayList<Pair> exampleMostFrequentlyOccuringLength = new ArrayList<>();
        exampleMostFrequentlyOccuringLength.add(new Pair(3,4));
        exampleWordCountStats.setMostFrequentlyOccuringLength(exampleMostFrequentlyOccuringLength);
    }

    @Test
    public void shouldCallFileParser() {

        when(mockFileParser.parseFile(mockMultipartFile)).thenReturn("Hi There");
        when(mockWordCounter.getWordCountStatsFrom("Hi There")).thenReturn(exampleWordCountStats);

        wordCountController.uploadFile(mockMultipartFile, mockRedirectAttributes);

        verify(mockFileParser).parseFile(mockMultipartFile);
    }

    @Test
    public void shouldReturnFileContentsWhenFileUploaded() {

        when(mockFileParser.parseFile(mockMultipartFile)).thenReturn("Hi There");
        when(mockWordCounter.getWordCountStatsFrom("Hi There")).thenReturn(exampleWordCountStats);

        String resultOutput = wordCountController.uploadFile(mockMultipartFile, mockRedirectAttributes);

        assert(resultOutput.equals("Word count = 3<br>Average word length = 0.000<br>The most frequently occurring word length is 4, for word lengths of 3"));
    }

    @Test
    public void shouldCountWordsForTextResponse(){
        when(mockFileParser.parseFile(mockMultipartFile)).thenReturn("Word count = 3<br>Average word length = 0.000<br>The most frequently occurring word length is 4, for word lengths of 3");
        when(mockWordCounter.getWordCountStatsFrom("Word count = 3<br>Average word length = 0.000<br>The most frequently occurring word length is 4, for word lengths of 3")).thenReturn(exampleWordCountStats);

        String parsedFileContents = wordCountController.uploadFile(mockMultipartFile, mockRedirectAttributes);

        verify(mockWordCounter).getWordCountStatsFrom("Word count = 3<br>Average word length = 0.000<br>The most frequently occurring word length is 4, for word lengths of 3");
    }

    @Test
    public void shouldCountWordsForJSONResponse(){
        when(mockFileParser.parseFile(mockMultipartFile)).thenReturn("Word count = 3<br>Average word length = 0.000<br>The most frequently occurring word length is 4, for word lengths of 3");
        when(mockWordCounter.getWordCountStatsFrom("Word count = 3<br>Average word length = 0.000<br>The most frequently occurring word length is 4, for word lengths of 3")).thenReturn(exampleWordCountStats);

        WordCountStats wordCountStats = wordCountController.uploadFileForJSON(mockMultipartFile, mockRedirectAttributes);

        verify(mockWordCounter).getWordCountStatsFrom("Word count = 3<br>Average word length = 0.000<br>The most frequently occurring word length is 4, for word lengths of 3");
    }

    private void createMockMultipartFile() {
        mockMultipartFile
                = new MockMultipartFile(
                "file",
                "test.txt",
                MediaType.TEXT_PLAIN_VALUE,
                "Hi There".getBytes()
        );
    }
}
