package pl.kat.ue.tspgenetic;

import pl.kat.ue.tspgenetic.operators.crossover.Crossover;
import pl.kat.ue.tspgenetic.operators.mutation.Mutation;
import pl.kat.ue.tspgenetic.operators.selection.Selection;
import pl.kat.ue.tspgenetic.operators.succession.Succession;

import java.util.NavigableMap;
import java.util.Objects;
import java.util.TreeMap;

public class GeneticAlgorithmBuilder {

    int populationSize;
    int[][] dataSet;
    int epochs;
    Selection selection;
    Crossover crossover;
    NavigableMap<Integer, Mutation> mutations;
    Succession succession;

    GeneticAlgorithmBuilder() {
    }

    public GeneticAlgorithmBuilder populationSize(int populationSize) {
        this.populationSize = populationSize;
        return this;
    }

    public GeneticAlgorithmBuilder dataSet(int[][] dataSet) {
        this.dataSet = dataSet;
        return this;
    }

    public GeneticAlgorithmBuilder epochs(int epochs) {
        this.epochs = epochs;
        return this;
    }

    public GeneticAlgorithmBuilder crossover(Crossover crossover) {
        this.crossover = crossover;
        return this;
    }

    public GeneticAlgorithmBuilder mutation(Mutation mutation, int maxEpoch) {
        if (Objects.isNull(mutations)) {
            mutations = new TreeMap<>();
        }
        this.mutations.put(maxEpoch, mutation);
        return this;
    }

    public GeneticAlgorithmBuilder succession(Succession succession) {
        this.succession = succession;
        return this;
    }

    public GeneticAlgorithmBuilder selection(Selection selection) {
        this.selection = selection;
        return this;
    }

    public GeneticAlgorithm build() {
        return new GeneticAlgorithm(this);
    }
}