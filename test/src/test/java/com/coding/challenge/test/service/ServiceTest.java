package com.coding.challenge.test.service;

import com.coding.challenge.test.command.*;
import com.coding.challenge.test.dto.Output;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class ServiceTest {

    @Mock
    private Echo echo;

    @Mock
    private Flatten flatten;

    @Mock
    private Multiply multiply;

    @Mock
    private Sum sum;

    @Mock
    private Invert invert;

    @InjectMocks
    private Service service;

    private String input = "1,2,3\n4,5,6\n7,8,9";

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testEcho_given_valid_input_success() {
        String output = input;

        Output expectedOutput = Output.build(output);

        when(echo.execute(input)).thenReturn(expectedOutput);

        Output actualOutput = service.echo(input);

        verify(echo, times(1)).execute(input);
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testFlatten_given_valid_input_success() {
        String output = "1,2,3,4,5,6,7,8,9";

        Output expectedOutput = Output.build(output);

        when(flatten.execute(input)).thenReturn(expectedOutput);

        Output actualOutput = service.flatten(input);

        verify(flatten, times(1)).execute(input);
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testInvert_given_valid_input_success() {
        String output = "1,4,7\n2,5,8\n3,6,9";

        Output expectedOutput = Output.build(output);

        when(invert.execute(input)).thenReturn(expectedOutput);

        Output actualOutput = service.invert(input);

        verify(invert, times(1)).execute(input);
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testSum_given_valid_input_success() {
        int output = 45;

        Output expectedOutput = Output.build(output);

        when(sum.execute(input)).thenReturn(expectedOutput);

        Output actualOutput = service.sum(input);

        verify(sum, times(1)).execute(input);
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testMultiply_given_valid_input_success() {
        int output = 362880;

        Output expectedOutput = Output.build(output);

        when(multiply.execute(input)).thenReturn(expectedOutput);

        Output actualOutput = service.multiply(input);

        verify(multiply, times(1)).execute(input);
        assertEquals(expectedOutput.getOutputInt(), actualOutput.getOutputInt());
    }
}
