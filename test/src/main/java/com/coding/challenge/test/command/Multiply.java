package com.coding.challenge.test.command;

import com.coding.challenge.test.entity.Matrix;
import com.coding.challenge.test.dto.Output;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
/**
 * Return the product of the integers in the matrix.
 */
public class Multiply implements Command {
    @Override
    public Output execute(String input) {
        Matrix matrix = new Matrix(input);
        List<Integer> numbers = matrix.getNumbers();
        Integer product = numbers.stream().reduce((num1, num2) -> num1 * num2).get();
       return Output.build(product);
    }
}
