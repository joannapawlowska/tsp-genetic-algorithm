package pl.kat.ue.tspgenetic.utils;

import java.util.ArrayList;
import java.util.List;

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

    public static List<Integer> nextDistinctInts(int size, int bound) {
        List<Integer> ints = new ArrayList<>();
        while(ints.size() < size){
            int i = nextInt(bound);
            if(!ints.contains(i)){
                ints.add(i);
            }
        }
        return ints;
    }
}