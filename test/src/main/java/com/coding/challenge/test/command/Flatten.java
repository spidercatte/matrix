package com.coding.challenge.test.command;

import com.coding.challenge.test.entity.Matrix;
import com.coding.challenge.test.dto.Output;
import org.springframework.stereotype.Component;

@Component
/**
 * Flatten command that return the matrix as a 1 line string, with values separated by commas.
 */
public class Flatten implements Command {
    @Override
    public Output execute(String input) {
        Matrix matrix = new Matrix(input);
        return Output.build(matrix.flatten());
    }
}
