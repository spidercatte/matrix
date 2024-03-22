package com.coding.challenge.test.util;

import com.coding.challenge.test.exception.InvalidFileException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.mock.web.MockMultipartFile;

import java.util.stream.Stream;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.*;

public class FileToStringTest {


    private static Stream<Arguments> fileTestData() {
        return Stream.of(
                // Valid CSV file with size within limit
                Arguments.of(new MockMultipartFile("file", "test.csv", "text/csv", "1,2,3".getBytes()), null, "1,2,3"),
                Arguments.of(new MockMultipartFile("file", "test.csv", "text/csv", "1,2,3\n4,5,6\n7,8,9".getBytes()), null,"1,2,3\n4,5,6\n7,8,9"),

                // Invalid file type
                Arguments.of(new MockMultipartFile("file", "test.txt", "text/plain", "1,2,3".getBytes()), InvalidFileException.class, "Invalid File Type, must be comma separated file (.csv)"),

                // File size exceeds limit
                Arguments.of(new MockMultipartFile("file", "large.csv", "text/csv", new byte[1024 * 1024 + 1]), InvalidFileException.class, "File size exceeds maximum limit"),

                // Invalid file content
                Arguments.of(new MockMultipartFile("file", "test.csv", "text/csv", "1 2 3".getBytes()), InvalidFileException.class, "Invalid file content")
        );
    }

    @ParameterizedTest
    @MethodSource("fileTestData")
    void fileToString_validFile(MockMultipartFile file, Class<? extends Exception> expectedException, String expectedValue) throws InvalidFileException {
        if (expectedException != null) {
            Exception exception = assertThrows(expectedException, () -> FileToString.fileToString(file));

            if (exception instanceof InvalidFileException) {
                assertEquals(exception.getMessage(), expectedValue);
            }

        } else {
            String fileToString = FileToString.fileToString(file);
            assertEquals(fileToString, expectedValue);
        }
    }
}
