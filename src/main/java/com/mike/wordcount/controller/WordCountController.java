package com.mike.wordcount.controller;

import com.mike.wordcount.counter.WordCounter;
import com.mike.wordcount.reader.FileParser;
import com.mike.wordcount.stats.WordCountStats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@RestController
public class WordCountController {

    @Autowired
    private FileParser fileParser;

    @Autowired
    private WordCounter wordCounter;

    @GetMapping("/wordcount")
    public String getWordCountFor() {
        getFileParser().readFile(new String());
        return null;
    }

    @PostMapping("/fileupload")
    public String uploadFile(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {

        String parsedFile = getFileParser().parseFile(file);
        WordCountStats wordCountStats = getWordCounter().getWordCountStatsFrom(parsedFile);

        return parsedFile ;
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