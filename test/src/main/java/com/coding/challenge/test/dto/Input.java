package com.coding.challenge.test.dto;

import com.coding.challenge.test.exception.InvalidFileException;
import com.coding.challenge.test.util.FileToString;
import org.springframework.web.multipart.MultipartFile;

/**
 * Data transfer object for input
 */
public class Input {
    private String fileAsString;

    private MultipartFile file;

    public String getFileAsString() {
        return this.fileAsString;
    }

    public MultipartFile getFile(){
        return this.file;
    }

    public static Input build(final MultipartFile file) throws InvalidFileException {
        var inputObj = new Input();
        inputObj.file = file;
        inputObj.fileAsString = FileToString.fileToString(file);
        return inputObj;
    }




}
