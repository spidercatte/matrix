package com.coding.challenge.test.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class ControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    MockMultipartFile validFile = new MockMultipartFile("file", "test.csv",
            "text/csv", "1,2,3\n4,5,6\n7,8,9".getBytes());

    MockMultipartFile invalidFileType = new MockMultipartFile("file", "test.txt",
            "text/plain", "1,2,3\n4,5,6\n7,8,9".getBytes());
    String expectedValueInvalidType = "Invalid File Type, must be comma separated file (.csv)";

    MockMultipartFile largeFile =  new MockMultipartFile("file", "large.csv",
            "text/csv", new byte[1024 * 1024 + 1]);
    String expectedValueLargeFile = "File size exceeds maximum limit";

    MockMultipartFile ivalidFileContent = new MockMultipartFile("file", "test.csv",
            "text/csv", "1 2 3\n4 5 6\n7 8 9".getBytes());
    String expectedValueInvalidContent = "Invalid file content";

    @Test
    public void testHello_success() throws Exception {

        mvc.perform(get("/api/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello, World!")); // Adjust the expected response


    }

    //echo
    @Test
    public void testEcho_with_valid_file_success() throws Exception {
        String expectedValue = "1,2,3\n4,5,6\n7,8,9";
        mvc.perform(MockMvcRequestBuilders.multipart("/api/echo")
                        .file(validFile))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.outputStr").value(expectedValue));

    }

    @Test
    public void testEcho_with_invalid_file_type() throws Exception {
        mvc.perform(MockMvcRequestBuilders.multipart("/api/echo")
                        .file(invalidFileType))
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.error").value(expectedValueInvalidType));
    }

    @Test
    public void testEcho_with_large_file() throws Exception {
        mvc.perform(MockMvcRequestBuilders.multipart("/api/echo")
                        .file(largeFile))
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.error").value(expectedValueLargeFile));
    }

    @Test
    public void testEcho_with_invalid_file_content() throws Exception {
        mvc.perform(MockMvcRequestBuilders.multipart("/api/echo")
                        .file(ivalidFileContent))
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.error").value(expectedValueInvalidContent));
    }

    @Test
    public void testEcho_with_null_parameter() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/api/echo")
                        .contentType(MediaType.MULTIPART_FORM_DATA))
                .andExpect(status().isBadRequest());
    }

    //flatten
    @Test
    public void testFlatten_with_valid_file_success() throws Exception {
        String expectedValue = "1,2,3,4,5,6,7,8,9";
        mvc.perform(MockMvcRequestBuilders.multipart("/api/flatten")
                        .file(validFile))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.outputStr").value(expectedValue));

    }

    @Test
    public void testFlatten_with_invalid_file_type() throws Exception {
        mvc.perform(MockMvcRequestBuilders.multipart("/api/flatten")
                        .file(invalidFileType))
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.error").value(expectedValueInvalidType));
    }

    @Test
    public void testFlatten_with_large_file() throws Exception {
        mvc.perform(MockMvcRequestBuilders.multipart("/api/flatten")
                        .file(largeFile))
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.error").value(expectedValueLargeFile));
    }

    @Test
    public void testFlatten_with_invalid_file_content() throws Exception {
        mvc.perform(MockMvcRequestBuilders.multipart("/api/flatten")
                        .file(ivalidFileContent))
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.error").value(expectedValueInvalidContent));
    }

    @Test
    public void testFlatten_with_null_parameter() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/api/flatten")
                        .contentType(MediaType.MULTIPART_FORM_DATA))
                .andExpect(status().isBadRequest());
    }

    //invert
    @Test
    public void testInvert_with_valid_file_success() throws Exception {
        String expectedValue =
                                "1,4,7\n" +
                                "2,5,8\n" +
                                "3,6,9";
        mvc.perform(MockMvcRequestBuilders.multipart("/api/invert")
                        .file(validFile))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.outputStr").value(expectedValue));

    }

    @Test
    public void testInvert_with_invalid_file_type() throws Exception {
        mvc.perform(MockMvcRequestBuilders.multipart("/api/invert")
                        .file(invalidFileType))
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.error").value(expectedValueInvalidType));
    }

    @Test
    public void testInvert_with_large_file() throws Exception {
        mvc.perform(MockMvcRequestBuilders.multipart("/api/invert")
                        .file(largeFile))
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.error").value(expectedValueLargeFile));
    }

    @Test
    public void testInvert_with_invalid_file_content() throws Exception {
        mvc.perform(MockMvcRequestBuilders.multipart("/api/invert")
                        .file(ivalidFileContent))
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.error").value(expectedValueInvalidContent));
    }

    @Test
    public void testInvert_with_null_parameter() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/api/invert")
                        .contentType(MediaType.MULTIPART_FORM_DATA))
                .andExpect(status().isBadRequest());
    }


    //sum
    @Test
    public void testSum_with_valid_file_success() throws Exception {
        int expectedValue = 45;
        mvc.perform(MockMvcRequestBuilders.multipart("/api/sum")
                        .file(validFile))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.outputInt").value(expectedValue));

    }

    @Test
    public void testSum_with_invalid_file_type() throws Exception {
        mvc.perform(MockMvcRequestBuilders.multipart("/api/sum")
                        .file(invalidFileType))
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.error").value(expectedValueInvalidType));
    }

    @Test
    public void testSum_with_large_file() throws Exception {
        mvc.perform(MockMvcRequestBuilders.multipart("/api/sum")
                        .file(largeFile))
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.error").value(expectedValueLargeFile));
    }

    @Test
    public void testSum_with_invalid_file_content() throws Exception {
        mvc.perform(MockMvcRequestBuilders.multipart("/api/sum")
                        .file(ivalidFileContent))
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.error").value(expectedValueInvalidContent));
    }

    @Test
    public void testSum_with_null_parameter() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/api/sum")
                        .contentType(MediaType.MULTIPART_FORM_DATA))
                .andExpect(status().isBadRequest());
    }

    // multiply
    @Test
    public void testMultiply_with_valid_file_success() throws Exception {
        int expectedValue = 362880;
        mvc.perform(MockMvcRequestBuilders.multipart("/api/multiply")
                        .file(validFile))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.outputInt").value(expectedValue));

    }

    @Test
    public void testMultiply_with_invalid_file_type() throws Exception {
        mvc.perform(MockMvcRequestBuilders.multipart("/api/multiply")
                        .file(invalidFileType))
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.error").value(expectedValueInvalidType));
    }

    @Test
    public void testMultiply_with_large_file() throws Exception {
        mvc.perform(MockMvcRequestBuilders.multipart("/api/multiply")
                        .file(largeFile))
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.error").value(expectedValueLargeFile));
    }

    @Test
    public void testMultiply_with_invalid_file_content() throws Exception {
        mvc.perform(MockMvcRequestBuilders.multipart("/api/multiply")
                        .file(ivalidFileContent))
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.error").value(expectedValueInvalidContent));
    }

    @Test
    public void testMultiply_with_null_parameter() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/api/multiply")
                        .contentType(MediaType.MULTIPART_FORM_DATA))
                .andExpect(status().isBadRequest());
    }


}