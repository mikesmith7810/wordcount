package com.mike.wordcount.mockmvc;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class WordCountControllerMockMvcTest {

    public static final String JSON_TEST_RESPONSE = "{\"fileContent\":null,\"numberOfWords\":2,\"wordLengths\":{\"2\":1,\"5\":1},\"averageWordLength\":3.5,\"mostFrequentlyOccuringLength\":[{\"key\":2,\"value\":1,\"pair\":[2,1]},{\"key\":5,\"value\":1,\"pair\":[5,1]}]}";
    public static final String TEXT_TEST_RESPONSE = "Word count = 2<br>Average word length = 3.500<br>Number of words of length 2 is 1<br>Number of words of length 5 is 1<br>The most frequently occurring word length is 1, for word lengths of 2 & 5";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Test
    public void shouldReturnOkWhenFileUploadForTextResponseInvoked() throws Exception {
        MockMultipartFile file
                = new MockMultipartFile(
                "file",
                "test.txt",
                MediaType.TEXT_PLAIN_VALUE,
                "Hi There".getBytes()
        );

        this.mockMvc.perform(multipart("/fileupload").file(file))
                .andExpect(status().isOk())
                .andExpect(content().string(TEXT_TEST_RESPONSE));
    }

    @Test
    public void shouldReturnOkWhenFileUploadForJSONResponseInvoked() throws Exception {
        MockMultipartFile file
                = new MockMultipartFile(
                "file",
                "test.txt",
                MediaType.TEXT_PLAIN_VALUE,
                "Hi There".getBytes()
        );

        this.mockMvc.perform(multipart("/fileuploadjson").file(file))
                .andExpect(status().isOk())
                .andExpect(content().json(JSON_TEST_RESPONSE));
        ;
    }

}

