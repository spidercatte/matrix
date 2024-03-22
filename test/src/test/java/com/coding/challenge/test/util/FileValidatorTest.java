package com.coding.challenge.test.util;

import com.coding.challenge.test.exception.InvalidFileException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.mock.web.MockMultipartFile;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class FileValidatorTest {

    private static Stream<Arguments> fileTypeAndSizeTestData() {
        return Stream.of(
                // Valid CSV file with size within limit
                Arguments.of(new MockMultipartFile("file", "test.csv", "text/csv", "1,2,3".getBytes()), null, null),

                // Invalid file type
                Arguments.of(new MockMultipartFile("file", "test.txt", "text/plain", "1,2,3".getBytes()), InvalidFileException.class, "Invalid File Type, must be comma separated file (.csv)"),

                // File size exceeds limit
                Arguments.of(new MockMultipartFile("file", "large.csv", "text/csv", new byte[1024 * 1024 + 1]), InvalidFileException.class, "File size exceeds maximum limit")
        );
    }

    @ParameterizedTest
    @MethodSource("fileTypeAndSizeTestData")
    public void testIsValidFileTypeAndSize(MockMultipartFile file, Class<? extends Exception> expectedException, String expectedMessage) {
        if (expectedException != null) {
            Exception exception = assertThrows(expectedException, () -> FileValidator.isValidFileTypeAndSize(file));

            if (exception instanceof InvalidFileException) {
                assertEquals(exception.getMessage(), expectedMessage);
            }

        } else {
            assertDoesNotThrow(() -> FileValidator.isValidFileTypeAndSize(file));
        }
    }

    private static Stream<Arguments> fileContentTestData() {
        return Stream.of(
                // Valid file content
                Arguments.of( "1,2,3", null, null),
                Arguments.of( "1,2,3\n4,5,6\n7,8,9", InvalidFileException.class, "Invalid file content"),
                // Invalid file content
                Arguments.of("1 2 3", InvalidFileException.class, "Invalid file content")

        );
    }

    @ParameterizedTest
    @MethodSource("fileContentTestData")
    public void testIsValidFileContent(String lineContent, Class<? extends Exception> expectedException, String expectedMessage) {
        if (expectedException != null) {
            Exception exception = assertThrows(expectedException, () -> FileValidator.isValidFileContent(lineContent));

            if (exception instanceof InvalidFileException) {
                assertEquals(exception.getMessage(), expectedMessage);
            }

        } else {
            assertDoesNotThrow(() -> FileValidator.isValidFileContent(lineContent));
        }
    }




}
