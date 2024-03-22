package com.coding.challenge.test.command;

import com.coding.challenge.test.entity.Matrix;
import com.coding.challenge.test.dto.Output;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
/**
 * Invert command that return the matrix as a string in matrix format where the columns and rows are inverted
 */
public class Invert implements Command {
    @Override
    public Output execute(String input) {
        Matrix matrix = new Matrix(input);
        String inverted = matrix.getColumnStrings().stream()
                .collect(Collectors.joining(Matrix.NEWLINE_PATTERN));

        return Output.build(inverted);

    }
}
