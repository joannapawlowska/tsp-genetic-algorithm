package pl.kat.ue.tspgenetic.utils;

public class Pair<T> {

    private final T t1;
    private final T t2;

    public Pair(T t1, T t2) {
        this.t1 = t1;
        this.t2 = t2;
    }

    public T getFirst() {
        return t1;
    }

    public T getSecond() {
        return t2;
    }
}