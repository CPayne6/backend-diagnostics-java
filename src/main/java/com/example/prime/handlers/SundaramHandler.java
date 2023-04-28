package com.example.prime.handlers;

import java.util.ArrayList;

public class SundaramHandler extends PrimeAlgorithmHandler {

    public SundaramHandler(int upperLimit, int lowerLimit) {
        super(upperLimit, lowerLimit);
    }

    @Override
    public int[] generate(int limit) {

        int k = (int) (limit - 2) / 2;

        // Create Prefilled array of false
        boolean[] arr = new boolean[k + 1];

        arr[0] = true;

        for (int i = 1; i < k + 1; i++) {
            for (int j = i; i + j + 2 * i * j <= k; j++) {
                arr[i + j + 2 * i * j] = true;
            }
        }

        ArrayList<Integer> primesList = new ArrayList<Integer>();
        for (int i = 0; i < k + 1; i++) {
            if (!arr[i])
                primesList.add(i * 2 + 1);
        }

        if (limit >= 2) {
            primesList.add(0, 2);
        }

        return this.arrayListToIntArray(primesList);
    }

}
