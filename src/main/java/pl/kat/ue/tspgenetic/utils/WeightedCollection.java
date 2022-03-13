package pl.kat.ue.tspgenetic.utils;

import java.util.NavigableMap;
import java.util.Random;
import java.util.TreeMap;

public class WeightedCollection<T> {

    private final NavigableMap<Double, T> map = new TreeMap<>();
    private static final Random random = new Random();
    private double weightsSum;

    public void add(double weight, T object) {
        if (weight > 0) {
            weightsSum += weight;
            map.put(weightsSum, object);
        }
    }

    public T next() {
        double value = random.nextDouble() * weightsSum;
        return map.ceilingEntry(value).getValue();
    }
}