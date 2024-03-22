package com.coding.challenge.test.controller;

import com.coding.challenge.test.dto.Input;
import com.coding.challenge.test.dto.Output;
import com.coding.challenge.test.exception.InvalidFileException;
import com.coding.challenge.test.service.Service;
import com.coding.challenge.test.service.ServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


/**
 * Implements the api endpoints
 */
@org.springframework.stereotype.Controller
@RequestMapping("/api")
public class Controller {

    ServiceInterface service;
    @Autowired
    public Controller(Service service) {
        this.service = service;
    }

    /**
     * Liveliness endpoint
     * @return String
     */
    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok().body("Hello, World!");
    }

    /**
     * Echoes the input matrix for response.
     * @param file - MultipartFile
     * @return json
     */
    @PostMapping("/echo")
    public ResponseEntity<Output> echo(final @NonNull @RequestParam("file") MultipartFile file) {
        Input input = null;
        Output output = null;
        try {
            input = Input.build(file);
            output = this.service.echo(input.getFileAsString());
            return ResponseEntity.ok().body(output);
        } catch (InvalidFileException e) {
            output = Output.error(e.getMessage());
            return ResponseEntity.badRequest().body(output);
        }

    }

    /**
     * Inverts the matrix where the columns and rows are inverted
     * @param file - MultipartFile
     * @return json
     */
    @PostMapping("/invert")
    public ResponseEntity<Output> invert(final @NonNull @RequestParam("file") MultipartFile file) {
        Input input = null;
        Output output = null;
        try {
            input = Input.build(file);
            output = this.service.invert(input.getFileAsString());
            return ResponseEntity.ok(output);
        } catch (InvalidFileException e) {
            output = Output.error(e.getMessage());
            return ResponseEntity.badRequest().body(output);
        }
    }

    /**
     * Flattens the matrix as a 1 line string, with values separated by commas.
     * @param file - MultipartFile
     * @return json
     */
    @PostMapping("/flatten")
    public ResponseEntity<Output> flatten(final @NonNull @RequestParam("file") MultipartFile file) {
        Input input = null;
        Output output = null;
        try {
            input = Input.build(file);
            output = this.service.flatten(input.getFileAsString());
            return ResponseEntity.ok(output);
        } catch (InvalidFileException e) {
            output = Output.error(e.getMessage());
            return ResponseEntity.badRequest().body(output);
        }

    }

    /**
     * Multiply all the numbers in the matrix.
     * @param file - MultipartFile
     * @return json
     */
    @PostMapping("/multiply")
    public ResponseEntity<Output> multiply(final @NonNull @RequestParam("file") MultipartFile file) {
        Input input = null;
        Output output = null;
        try {
            input = Input.build(file);
            output = this.service.multiply(input.getFileAsString());
            return ResponseEntity.ok(output);
        } catch (InvalidFileException e) {
            output = Output.error(e.getMessage());
            return ResponseEntity.badRequest().body(output);
        }
    }

    /**
     * Sum all the numbers in the matrix.
     * @param file - MultipartFile
     * @return json
     */
    @PostMapping("/sum")
    public ResponseEntity<Output> sum(final @RequestBody @RequestParam("file") MultipartFile file) {
        Input input = null;
        Output output = null;
        try {
            input = Input.build(file);
            output = this.service.sum(input.getFileAsString());
            return ResponseEntity.ok(output);
        } catch (InvalidFileException e) {
            output = Output.error(e.getMessage());
            return ResponseEntity.badRequest().body(output);
        }
    }



}
