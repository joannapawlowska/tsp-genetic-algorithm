package pl.kat.ue.tspgenetic.utils;

public class Random {

    private static final java.util.Random random = new java.util.Random();

    private Random() {
    }

    public static double nextDouble() {
        return random.nextDouble();
    }

    public static int nextInt(int bound) {
        return random.nextInt(bound);
    }
}