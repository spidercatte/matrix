package com.coding.challenge.test.dto;


import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Data transfer object for output
 * Put the response values to their respective attributes
 * outputStr - string output
 * outputInt - int output
 * error - error message
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Output {
    String outputStr;
    Integer outputInt;
    String error;

    public Integer getOutputInt() {
        return this.outputInt;
    }

    public String getOutputStr() { return this.outputStr;}

    public String getError() { return this.error;}

    public static Output error(String error) {
        Output output = new Output();
        output.error = error;
        return output;
    }

    public static Output build(String outputStr) {
        Output output = new Output();
        output.outputStr = outputStr;

        return output;
    }

    public static Output build(int outputInt) {
        Output output = new Output();
        output.outputInt= outputInt;
        return output;
    }
}
