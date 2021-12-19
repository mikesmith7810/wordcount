package com.mike.wordcount.stats;


import com.mike.wordcount.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;

public class WordCountStats {


    private String fileContent;

    private int numberOfWords;

    private HashMap<Integer, Integer> wordLengths;

    private double averageWordLength;

    private ArrayList<Pair> mostFrequentlyOccuringLength;

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

    public double getAverageWordLength() {
        return averageWordLength;
    }

    public void setAverageWordLength(double averageWordLength) {
        this.averageWordLength = averageWordLength;
    }

    public ArrayList<Pair> getMostFrequentlyOccuringLength() {
        return mostFrequentlyOccuringLength;
    }

    public void setMostFrequentlyOccuringLength(ArrayList<Pair> mostFrequentlyOccuringLength) {
        this.mostFrequentlyOccuringLength = mostFrequentlyOccuringLength;
    }
}
