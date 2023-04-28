package com.example.prime.handlers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import com.example.prime.PrimesBody;

@Controller
public abstract class PrimeAlgorithmHandler {
    @Value("${upperLimit: #{1000000}}")
    private int UPPER_LIMIT;

    @Value("${lowerLimit:1}")
    private int LOWER_LIMIT;

    PrimeAlgorithmHandler(int upperLimit, int lowerLimit) {
        this.UPPER_LIMIT = upperLimit;
        this.LOWER_LIMIT = lowerLimit;
    }

    public abstract int[] generate(int limit);

    /**
     * Converts an ArrayList<Integer> to an array or type int
     * 
     * @param list
     * @return equivalent int array
     */
    public int[] arrayListToIntArray(ArrayList<Integer> list) {
        int[] primes = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            primes[i] = list.get(i);
        }
        return primes;
    }

    /**
     * Validate that the limit is within the bounds specified
     * 
     * @param limit
     * @return
     */
    protected boolean validate(int limit) {
        if (limit <= this.UPPER_LIMIT && limit >= LOWER_LIMIT)
            return true;

        return false;
    }

    /**
     * Validates the input from the client and calls the child's generate method
     * 
     * Records the time duration that the algorithm takes to run
     * 
     * @param limit
     * @return Payload for the client
     */
    public ResponseEntity<String> handler(int limit) {
        if (!this.validate(limit)) {
            System.out.println("Invalid input: " + limit);
            System.out.println("Bounds: " + LOWER_LIMIT + " - " + UPPER_LIMIT);
            return ResponseEntity.badRequest()
                    .body("\"limit\" must be an integer from " + LOWER_LIMIT + " to " + UPPER_LIMIT);
        }

        long startTime = System.nanoTime();

        int[] primes = this.generate(limit);

        long microseconds = (System.nanoTime() - startTime) / 1000;

        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<String>(new PrimesBody(microseconds, primes).toString(), httpHeaders,
                HttpStatus.OK);
    }
}
