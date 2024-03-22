package com.coding.challenge.test.service;

import com.coding.challenge.test.command.*;
import com.coding.challenge.test.dto.Output;
import org.springframework.beans.factory.annotation.Autowired;


@org.springframework.stereotype.Service
public class Service implements ServiceInterface{

    Command echo;
    Command flatten;
    Command invert;
    Command multiply;
    Command sum;


    /**
     * Centralizes business logic entry
     */
    @Autowired
    public Service (Echo echo, Flatten flatten,
                    Multiply multiply, Sum sum,
                    Invert invert) {
        this.echo = echo;
        this.flatten = flatten;
        this.multiply = multiply;
        this.sum = sum;
        this.invert = invert;
    }

    public Output echo(String input) {

        return this.echo.execute(input);
    }

    public Output invert(String input) {

        return this.invert.execute(input);
    }

    public Output flatten(String input) {

        return this.flatten.execute(input);
    }

    public Output sum(String input) {

        return this.sum.execute(input);
    }

    public Output multiply(String input) {

        return this.multiply.execute(input);
    }
}
