package com.coding.challenge.test.service;

import com.coding.challenge.test.dto.Output;

public interface ServiceInterface {

    public Output echo(String input);

    public Output invert(String input);

    public Output flatten(String input);

    public Output sum(String input);

    public Output multiply(String input);
}
