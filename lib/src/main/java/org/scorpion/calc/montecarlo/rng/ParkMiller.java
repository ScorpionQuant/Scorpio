package org.scorpion.calc.montecarlo.rng;

class ParkMiller {

    private final long m = 2147483647;

    private long seed;

    /* Park Miller requires that the seed should not be 0 */
    ParkMiller(long seed) {
        this.seed = seed;
        if (0 == seed) {
            this.seed = 1;
        }
    }

    long getSeed() {
        return seed;
    }

    void setSeed(long seed) {
        this.seed = seed;
        if (0 == seed) {
            this.seed = 1;
        }
    }

    long min() {
        return 1;
    }

    long max() {
        return m - 1;
    }

    long getOneRandomInteger() {
        long a = 16807;
        long q = 127773;
        long r = 2836;
        long k = seed / q;
        seed = a * (seed - k * q) - r * k;
        if (seed < 0) seed += m;
        return seed;
    }
}
