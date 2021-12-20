package com.mike.wordcount.reader;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Service
public class FileParser {

    public String parseFile(MultipartFile multipartFile) {

        String parsedFile = null;
        try {
            parsedFile = new String(multipartFile.getBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return parsedFile;
    }
}
