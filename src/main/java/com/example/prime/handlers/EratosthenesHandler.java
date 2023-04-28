package com.example.prime.handlers;

import java.util.ArrayList;

public class EratosthenesHandler extends PrimeAlgorithmHandler {

    public EratosthenesHandler(int upperLimit, int lowerLimit) {
        super(upperLimit, lowerLimit);
    }

    @Override
    public int[] generate(int limit) {

        // Create Prefilled array of false (false indicates prime)
        boolean[] arr = new boolean[limit];

        arr[0] = true;
        arr[1] = true;

        for (int i = 2; i < Math.sqrt(limit); i++) {
            if (!arr[i]) {
                int j = i * i;
                while (j < limit) {
                    arr[j] = true;
                    j += i;
                }
            }
        }

        // Create list of primes by adding the indicies of arr that are true
        ArrayList<Integer> primesList = new ArrayList<Integer>();
        for (int i = 0; i < arr.length; i++) {
            if (!arr[i])
                primesList.add(i);
        }

        return this.arrayListToIntArray(primesList);
    }
}
