package com.coding.challenge.test.command;

import com.coding.challenge.test.entity.Matrix;
import com.coding.challenge.test.dto.Output;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
/**
 * Return the sum of the integers in the matrix
 */
public class Sum implements Command {
    @Override
    public Output execute(String input) {
        Matrix matrix = new Matrix(input);
        List<Integer> numbers = matrix.getNumbers();
        Integer sum = numbers.stream().reduce(0, Integer::sum);
        return Output.build(sum);
    }
}
