package pl.kat.ue.tspgenetic;

import pl.kat.ue.tspgenetic.operators.GeneticOperator;
import pl.kat.ue.tspgenetic.operators.crossover.Crossover;
import pl.kat.ue.tspgenetic.operators.mutation.Mutation;
import pl.kat.ue.tspgenetic.operators.selection.Selection;
import pl.kat.ue.tspgenetic.operators.succession.Succession;

import java.util.NavigableMap;
import java.util.TreeMap;

public class TspGeneticAlgorithmBuilder {

    int populationSize;
    int[][] dataSet;
    int epochs;
    NavigableMap<Integer, Selection> selections = new TreeMap<>();
    NavigableMap<Integer, Crossover> crossovers = new TreeMap<>();
    NavigableMap<Integer, Mutation> mutations = new TreeMap<>();
    Succession succession;

    TspGeneticAlgorithmBuilder() {
    }

    public TspGeneticAlgorithmBuilder populationSize(int populationSize) {
        this.populationSize = populationSize;
        return this;
    }

    public TspGeneticAlgorithmBuilder dataSet(int[][] dataSet) {
        this.dataSet = dataSet;
        return this;
    }

    public TspGeneticAlgorithmBuilder epochs(int epochs) {
        this.epochs = epochs;
        return this;
    }

    public GeneticOperatorMetadata<Selection> selection(Selection selection) {
        return new GeneticOperatorMetadata<>(selection, this);
    }

    public GeneticOperatorMetadata<Crossover> crossover(Crossover crossover) {
        return new GeneticOperatorMetadata<>(crossover, this);

    }

    public GeneticOperatorMetadata<Mutation> mutation(Mutation mutation) {
        return new GeneticOperatorMetadata<>(mutation, this);

    }

    public TspGeneticAlgorithmBuilder succession(Succession succession) {
        this.succession = succession;
        return this;
    }

    private static <T extends GeneticOperator> NavigableMap<Integer, T> getMapForType(
            T geneticOperator, TspGeneticAlgorithmBuilder builder) {

        if (geneticOperator instanceof Selection) {
            return (NavigableMap<Integer, T>) builder.selections;
        } else if (geneticOperator instanceof Mutation) {
            return (NavigableMap<Integer, T>) builder.mutations;
        } else if (geneticOperator instanceof Crossover) {
            return (NavigableMap<Integer, T>) builder.crossovers;
        } else {
            return new TreeMap<>();
        }
    }

    public TspGeneticAlgorithm build() {
        return new TspGeneticAlgorithm(this);
    }

    public static class GeneticOperatorMetadata<T extends GeneticOperator> {

        private final T geneticOperator;
        private final TspGeneticAlgorithmBuilder builder;

        public GeneticOperatorMetadata(T geneticOperator, TspGeneticAlgorithmBuilder builder) {
            this.geneticOperator = geneticOperator;
            this.builder = builder;
        }

        public TspGeneticAlgorithmBuilder performUpToEpoch(Integer maxEpoch) {
            getMapForType(geneticOperator, builder).put(maxEpoch, geneticOperator);
            return builder;
        }
    }
}