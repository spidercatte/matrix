package com.coding.challenge.test.util;

import com.coding.challenge.test.exception.InvalidFileException;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Utility class to validate multipart file format and content
 */
public class FileValidator {

    private final static String MATRIX_REGEX = "\\d+\\s*,\\s*\\d+\\s*,\\s*\\d+";
    private final static Pattern pattern = Pattern.compile(MATRIX_REGEX);

    /**
     * Static method to check file type extension and size
     * @param file - Multipart
     * @throws InvalidFileException - throws exception when file is not .csv type and when it exceeds size of 1024 * 1024
     */
    public static void isValidFileTypeAndSize(MultipartFile file) throws InvalidFileException {
        try {
            String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
            if (!fileName.contains(".csv")) {
                throw new InvalidFileException("Invalid File Type, must be comma separated file (.csv)");
            }
            if (file.getBytes().length > (1024 * 1024)) {
                throw new InvalidFileException("File size exceeds maximum limit");
            }
        } catch (IOException e) {
            throw new RuntimeException("Issue processing the file");
        }
    }

    /**
     * Static method to validate if the string pattern follows this pattern: "\\d+\\s*,\\s*\\d+\\s*,\\s*\\d+"
     * @param input - String
     * @throws InvalidFileException - throws exception if pattern not matching
     */
    public static void isValidFileContent(String input) throws InvalidFileException {
        Matcher matcher = FileValidator.pattern.matcher(input);
        if(!matcher.matches()){
            throw new InvalidFileException("Invalid file content");
        }
    }
}
