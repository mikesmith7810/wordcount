package com.mike.wordcount.reader;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
public class FileParserTest {

    FileParser fileParser = new FileParser();

    @Mock
    private MultipartFile mockMultipartFile;

    @BeforeEach
    public void setup(){
        createMockMultipartFile();
    }

    @Test
    public void shouldParseFileIntoString(){
        String parsedFile = fileParser.parseFile(mockMultipartFile);

        assertNotNull(parsedFile);
        assert(parsedFile.equals("Hi There"));
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
