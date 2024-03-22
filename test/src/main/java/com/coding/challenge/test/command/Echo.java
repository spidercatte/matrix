package com.coding.challenge.test.command;

import com.coding.challenge.test.dto.Output;
import org.springframework.stereotype.Component;


@Component
/**
 * Echo Command that echos the input as the matrix as a string in matrix format.
 */
public class Echo implements Command {
    @Override
    public Output execute (String input) {

        return Output.build(input);
    }
}
