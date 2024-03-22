package com.coding.challenge.test.command;

import com.coding.challenge.test.dto.Output;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.junit.jupiter.api.Assertions.*;


import java.util.stream.Stream;

class EchoTest {

    private static Stream<Arguments> getTestData() {
        return Stream.of(
                Arguments.of(true, "1,2,3\n4,5,6\n7,8,9", "1,2,3\n4,5,6\n7,8,9"),
                Arguments.of(true, "10,20,30\n40,50,60\n70,80,90", "10,20,30\n40,50,60\n70,80,90"),
                Arguments.of(false, "100,200,300\n400,500,600\n700,800,900", "100,200,300\n400,500,600\n700,800")
        );
    }

    @ParameterizedTest
    @MethodSource("getTestData")
    void testExecute(boolean assertType, String input, String expected) {
        Echo echoCommand = new Echo();
        Output result = echoCommand.execute(input);
        if (assertType) {
            assertEquals(result.getOutputStr(), expected);
        } else {
            assertNotEquals(result.getOutputStr(), expected);
        }
    }

}