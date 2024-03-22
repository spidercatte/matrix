package com.coding.challenge.test.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Class representation of the matrix file content
 */
public class Matrix {
    public final static String NEWLINE_PATTERN = "\n";
    public final static String COMMA_PATTERN = ",";

    private final String strMatrix;

    private int dimension;

    public String getStrMatrix() {
        return this.strMatrix;
    }

    public int getDimension() {
        return this.dimension;
    }

    public Matrix (final String strMatrix) {

        this.strMatrix = strMatrix;
    }

    //Return list of numbers in the matrix
    public List<Integer> getNumbers() {
        String flattenStr = this.flatten();
        return Stream.of(flattenStr.split(Matrix.COMMA_PATTERN))
                .flatMap(str -> Stream.of(Integer.valueOf(str)))
                .toList();
    }


    //Return the matrix as a 1 line string, with values separated by commas.
    public String flatten() {
        return this.strMatrix.replaceAll(Matrix.NEWLINE_PATTERN,Matrix.COMMA_PATTERN);
    }

    //Return list of strings based on matrix rows
    public List<String> getRowStrings() {
        List<String> rowStrings = Arrays.stream(this.strMatrix.split(Matrix.NEWLINE_PATTERN)).toList();
        this.dimension = rowStrings.size();
        return rowStrings;
    }

    //Return list of strings based on matrix columns
    public List<String> getColumnStrings() {
        List<List<String>> matrixRow = Arrays.stream(strMatrix.split(Matrix.NEWLINE_PATTERN))
                .map(rowString -> Arrays.asList(rowString.split(COMMA_PATTERN)))
                .collect(Collectors.toList());
        List<String> columnStrings = new ArrayList<String>(matrixRow.size());
        for(int i = 0; i < matrixRow.size(); i++) {
            int tempIndex = i;
            String columString = matrixRow.stream()
                    .map(listRow -> listRow.get(tempIndex))
                    .collect(Collectors.joining(COMMA_PATTERN));
            columnStrings.add(columString);
        }

        return columnStrings;
    }
}
