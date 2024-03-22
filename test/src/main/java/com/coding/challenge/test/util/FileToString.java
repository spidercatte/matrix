package com.coding.challenge.test.util;

import com.coding.challenge.test.exception.InvalidFileException;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;

/**
 * Utility class to convert multipart file format content to single string
 */
public class FileToString {

    /**
     * Static method that converts the multipart content to string.
     * Uses FileValidator to check for invalid file format and file content
     * @param file - Multipart
     * @return String
     * @throws InvalidFileException
     */
    public static String fileToString(MultipartFile file) throws InvalidFileException{
        FileValidator.isValidFileTypeAndSize(file);

        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            StringBuilder stringBuilder = new StringBuilder();
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                FileValidator.isValidFileContent(line);
                stringBuilder.append(line);
                stringBuilder.append("\n");  // Add newline to separate lines if needed
            }

            // Remove the trailing newline if added
            if (stringBuilder.length() > 0 && stringBuilder.charAt(stringBuilder.length() - 1) == '\n') {
                stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            }

            return stringBuilder.toString();
        } catch (IOException e) {
            throw new InvalidFileException("Issue processing the file");
        }
    }
}
