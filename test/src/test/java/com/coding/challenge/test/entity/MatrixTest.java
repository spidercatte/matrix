package com.coding.challenge.test.entity;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.junit.jupiter.api.Assertions.*;


import java.util.List;
import java.util.stream.Stream;


class MatrixTest {

    private static Stream<Arguments> getNumbersTestData() {
        return Stream.of(
                Arguments.of(true, "1,2,3\n4,5,6\n7,8,9", List.of(1, 2, 3, 4, 5, 6, 7, 8, 9)),
                Arguments.of(true, "10,20,30\n40,50,60\n70,80,90", List.of(10, 20, 30, 40, 50, 60, 70, 80, 90)),
                Arguments.of(false, "1,2,3\n4,5,6\n7,8,9", List.of(1, 2, 3, 4, 5, 6, 7, 8))
        );
    }

    @ParameterizedTest
    @MethodSource("getNumbersTestData")
    void testGetNumbers(boolean assertType, String matrixStr, List<Integer> expectedNumbers) {
        Matrix matrix = new Matrix(matrixStr);
        List<Integer> numbers = matrix.getNumbers();
        if(assertType) {
            assertEquals(expectedNumbers, numbers);
        } else {
            assertNotEquals(expectedNumbers, numbers);
        }

    }

    private static Stream<Arguments> getFlattenTestData() {
        return Stream.of(
                Arguments.of(true, "1,2,3\n4,5,6\n7,8,9", "1,2,3,4,5,6,7,8,9"),
                Arguments.of(true, "10,20,30\n40,50,60\n70,80,90", "10,20,30,40,50,60,70,80,90"),
                Arguments.of(false, "1,2,3\n4,5,6\n7,8,9", "1,2,3,4,5,6,7,8")
        );
    }

    @ParameterizedTest
    @MethodSource("getFlattenTestData")
    void testFlatten(boolean assertType, String matrixStr, String expectedFlatten) {
        Matrix matrix = new Matrix(matrixStr);
        String flattenedString = matrix.flatten();
        if (assertType) {
            assertEquals(flattenedString, expectedFlatten);
        } else {
            assertNotEquals(flattenedString, expectedFlatten);
        }
    }


    private static Stream<Arguments> getRowStringsTestData() {
        return Stream.of(
                Arguments.of(true, "1,2,3\n4,5,6\n7,8,9", List.of("1,2,3", "4,5,6", "7,8,9")),
                Arguments.of(true, "10,20,30\n40,50,60\n70,80,90", List.of("10,20,30", "40,50,60", "70,80,90")),
                Arguments.of(false, "1,2,3\n4,5,6\n7,8,9", List.of("1,2,3", "4,5,6"))
        );
    }

    @ParameterizedTest
    @MethodSource("getRowStringsTestData")
    void testGetRowStrings(boolean assertType, String matrixStr, List<String> expectedString) {
        Matrix matrix = new Matrix(matrixStr);
        List<String> rowStrings = matrix.getRowStrings();
        if (assertType) {
            assertEquals(rowStrings, expectedString);
        } else {
            assertNotEquals(rowStrings, expectedString);
        }
    }


    private static Stream<Arguments> getColumnStringsTestData() {
        return Stream.of(
                Arguments.of(true, "1,2,3\n4,5,6\n7,8,9", List.of("1,4,7", "2,5,8", "3,6,9")),
                Arguments.of(true, "10,20,30\n40,50,60\n70,80,90", List.of("10,40,70", "20,50,80", "30,60,90")),
                Arguments.of(false, "10,20,30\n40,50,60\n70,80,90", List.of("10,20,30", "40,50,60", "70,80,90"))
        );
    }

    @ParameterizedTest
    @MethodSource("getColumnStringsTestData")
    void testGetColumnStrings(boolean assertType, String matrixStr,  List<String> expectedString) {
        Matrix matrix = new Matrix(matrixStr);
        List<String> columnStrings = matrix.getColumnStrings();
        if (assertType) {
            assertEquals(columnStrings, expectedString);
        } else {
            assertNotEquals(columnStrings, expectedString);
        }


    }

}
