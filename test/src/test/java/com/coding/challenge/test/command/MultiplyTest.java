package com.coding.challenge.test.command;

import com.coding.challenge.test.dto.Output;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class MultiplyTest {


    private static Stream<Arguments> getTestData() {
        return Stream.of(
                Arguments.of(true, "1,2,3\n4,5,6\n7,8,9", 362880),
                Arguments.of(false, "10,20,30\n40,50,60\n70,80,90", 362882)
        );
    }

    @ParameterizedTest
    @MethodSource("getTestData")
    void testExecute(boolean assertType, String input, int expected) {
        Multiply multiplyCommand = new Multiply();
        Output result = multiplyCommand.execute(input);
        if (assertType) {
            assertEquals(result.getOutputInt(), expected);
        } else {
            assertNotEquals(result.getOutputStr(), expected);
        }
    }

}