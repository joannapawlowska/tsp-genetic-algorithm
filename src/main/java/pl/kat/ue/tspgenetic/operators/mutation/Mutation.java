package pl.kat.ue.tspgenetic.operators.mutation;

import pl.kat.ue.tspgenetic.Individual;
import pl.kat.ue.tspgenetic.Population;
import pl.kat.ue.tspgenetic.operators.GeneticOperator;
import pl.kat.ue.tspgenetic.utils.Random;

import java.util.List;
import java.util.stream.Collectors;

public abstract class Mutation implements GeneticOperator {

    private final double mutationProbability;

    public Mutation(double mutationProbability) {
        this.mutationProbability = mutationProbability;
    }

    public void mutate(Population evolvingPopulation) {
        for (Individual individual : evolvingPopulation) {
            if (shouldMutate()) {
                mutateIndividual(individual);
            }
        }
    }

    private boolean shouldMutate() {
        return Random.nextDouble() < mutationProbability;
    }

    protected abstract void mutateIndividual(Individual individual);

    protected List<Integer> getSwapPoints(int range) {
        return Random.nextDistinctInts(2, range)
                .stream()
                .sorted()
                .collect(Collectors.toList());
    }
}