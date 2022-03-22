package pl.kat.ue.tspgenetic.operators.mutation;

import pl.kat.ue.tspgenetic.Individual;
import pl.kat.ue.tspgenetic.Population;
import pl.kat.ue.tspgenetic.utils.Random;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public abstract class Mutation {

    private double mutationProbability;
    private Predicate<Individual> shouldMutate = i -> Random.nextDouble() < mutationProbability;

    public Mutation(double mutationProbability) {
        this.mutationProbability = mutationProbability;
    }

    public void mutate(Population evolvingPopulation) {
        evolvingPopulation.stream()
                .filter(shouldMutate)
                .forEach(this::mutateIndividual);
    }

    protected abstract void mutateIndividual(Individual individual);

    protected List<Integer> getSwapPoints(int range) {
        return Random.nextDistinctInts(2, range)
                .stream()
                .sorted()
                .collect(Collectors.toList());
    }
}