package com.mike.wordcount.controller;
import com.mike.wordcount.counter.WordCounter;
import com.mike.wordcount.reader.FileParser;
import com.mike.wordcount.stats.WordCountStats;
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

        String parsedFileContents = wordCountController.uploadFile(mockMultipartFile, mockRedirectAttributes);

        assert(parsedFileContents.equals("Hi There"));
    }

    @Test
    public void shouldCountWords(){
        when(mockFileParser.parseFile(mockMultipartFile)).thenReturn("Hi There");
        when(mockWordCounter.getWordCountStatsFrom("Hi There")).thenReturn(exampleWordCountStats);

        String parsedFileContents = wordCountController.uploadFile(mockMultipartFile, mockRedirectAttributes);

        verify(mockWordCounter).getWordCountStatsFrom(parsedFileContents);
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
