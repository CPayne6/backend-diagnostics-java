package com.example.prime.handlers;

import java.util.ArrayList;

public class AtkinHandler extends PrimeAlgorithmHandler {

    public AtkinHandler(int upperLimit, int lowerLimit) {
        super(upperLimit, lowerLimit);
    }

    @Override
    public int[] generate(int limit) {

        // Create Prefilled array of false (true indicates prime)
        boolean[] arr = new boolean[limit + 1];

        int k = (int) Math.sqrt(limit) + 1;

        System.out.println(k);

        for (int x = 1; x < k; x++) {
            for (int y = 1; y < k; y++) {
                int n1 = 4 * x * x + y * y;
                if (n1 <= limit && (n1 % 12 == 1 || n1 % 12 == 5)) {
                    arr[n1] = !arr[n1];
                }

                int n2 = 3 * x * x + y * y;
                if (n2 <= limit && n2 % 12 == 7) {
                    arr[n2] = !arr[n2];
                }

                int n3 = 3 * x * x - y * y;
                if (x > y && n3 <= limit && n3 % 12 == 11) {
                    arr[n3] = !arr[n3];
                }
            }
        }

        System.out.println(arr[100]);

        // Eliminate squares and multiples of squares
        for (int r = 5; r < k; r++) {
            if (arr[r]) {
                for (int i = 1; i * r * r <= limit; i++) {
                    arr[i * r * r] = false;
                }
            }
        }

        // Create list of primes by adding the indicies of arr that are true
        ArrayList<Integer> primesList = new ArrayList<Integer>();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i])
                primesList.add(i);
        }

        if (limit >= 3) {
            primesList.add(0, 3);
        }
        if (limit >= 2) {
            primesList.add(0, 2);

        }

        return this.arrayListToIntArray(primesList);
    }

}
