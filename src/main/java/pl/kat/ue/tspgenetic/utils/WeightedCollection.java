package pl.kat.ue.tspgenetic.utils;

import java.util.NavigableMap;
import java.util.TreeMap;

public class WeightedCollection<T> {

    private final NavigableMap<Double, T> map = new TreeMap<>();
    private double weightsSum = 0;

    public void add(double weight, T object) {
        if (weight > 0) {
            weightsSum += weight;
            map.put(weightsSum, object);
        }
    }

    public T next() {
        double value = Random.nextDouble() * weightsSum;
        return map.ceilingEntry(value).getValue();
    }
}