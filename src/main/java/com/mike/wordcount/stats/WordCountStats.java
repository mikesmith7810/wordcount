package com.mike.wordcount.stats;

import java.util.HashMap;

public class WordCountStats {



    private String fileContent;

    private int numberOfWords;


    private HashMap<Integer, Integer> wordLengths;

    public String getFileContent() {
        return fileContent;
    }

    public void setFileContent(String fileContent) {
        this.fileContent = fileContent;
    }

    public int getNumberOfWords() {
        return numberOfWords;
    }

    public void setNumberOfWords(int numberOfWords) {
        this.numberOfWords = numberOfWords;
    }

    public HashMap<Integer, Integer> getWordLengths() {
        return wordLengths;
    }

    public void setWordLengths(HashMap<Integer, Integer> wordLengths) {
        this.wordLengths = wordLengths;
    }
}
