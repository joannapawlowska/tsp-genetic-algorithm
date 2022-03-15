package pl.kat.ue.tspgenetic;

import java.util.ArrayList;
import java.util.Arrays;

public class Population extends ArrayList<Individual> {

    public Population() {
    }

    public Population(Individual... individuals) {
        super.addAll(Arrays.asList(individuals));
    }
}