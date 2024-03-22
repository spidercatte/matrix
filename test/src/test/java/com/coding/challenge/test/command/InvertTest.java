package com.coding.challenge.test.command;

import com.coding.challenge.test.dto.Output;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class InvertTest {

    private static Stream<Arguments> getTestData() {
        return Stream.of(
                Arguments.of(true, "1,2,3\n4,5,6\n7,8,9", "1,4,7\n2,5,8\n3,6,9"),
                Arguments.of(true, "10,20,30\n40,50,60\n70,80,90", "10,40,70\n20,50,80\n30,60,90"),
                Arguments.of(false, "10,20,30\n40,50,60\n70,80,90", "10,20,30\n40,50,60\n70,80,90")
        );
    }

    @ParameterizedTest
    @MethodSource("getTestData")
    void testExecute(boolean assertType, String input, String expected) {
        Invert invertCommand = new Invert();
        Output result = invertCommand.execute(input);
        if (assertType) {
            assertEquals(result.getOutputStr(), expected);
        } else {
            assertNotEquals(result.getOutputStr(), expected);
        }
    }
}