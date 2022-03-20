package pl.kat.ue.tspgenetic;

import pl.kat.ue.tspgenetic.utils.Pair;

import java.util.ArrayList;
import java.util.Arrays;

public class Population extends ArrayList<Individual> {

    public Population() {
    }

    public Population(Individual... individuals) {
        super.addAll(Arrays.asList(individuals));
    }

    public void add(Pair<Individual> individualPair) {
        super.add(individualPair.getFirst());
        super.add(individualPair.getSecond());
    }
}