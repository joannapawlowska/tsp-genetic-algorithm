package pl.kat.ue.tspgenetic.operators.crossover;

import pl.kat.ue.tspgenetic.Individual;
import pl.kat.ue.tspgenetic.Population;
import pl.kat.ue.tspgenetic.utils.Pair;
import pl.kat.ue.tspgenetic.utils.Random;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public abstract class Crossover {

    private final double crossoverProbability;
    protected int[] crossed1;
    protected int[] crossed2;
    protected int[] genotype1;
    protected int[] genotype2;
    protected int genotypeLength;

    public Crossover(double crossoverProbability) {
        this.crossoverProbability = crossoverProbability;
    }

    public abstract Pair<Individual> crossPair(Pair<Individual> pair);

    protected void setCurrentPairData(Pair<Individual> pair) {
        this.genotype1 = pair.getFirst().getGenotype();
        this.genotype2 = pair.getSecond().getGenotype();
        this.genotypeLength = genotype1.length;
        this.crossed1 = new int[genotypeLength];
        this.crossed2 = new int[genotypeLength];
    }

    protected List<Integer> selectCutPoints(int range) {
        return Random.nextDistinctInts(2, range).stream()
                .map(i -> i + 1)
                .sorted()
                .collect(Collectors.toList());
    }

    public void cross(Population evolvingPopulation) {
        Population recombinedPopulation = new Population();

        while (shouldRecombine(evolvingPopulation)) {
            Pair<Individual> pair = selectRandomlyPair(evolvingPopulation);
            recombinedPopulation.add(shouldCross() ? crossPair(pair) : pair);
        }
        evolvingPopulation.addAll(recombinedPopulation);
    }

    private boolean shouldRecombine(Population population) {
        return population.size() > 1;
    }

    private Pair<Individual> selectRandomlyPair(Population population) {
        List<Individual> pair = Random.nextDistinctInts(2, population.size()).stream()
                .sorted(Collections.reverseOrder())
                .mapToInt(Integer::intValue)
                .mapToObj(population::remove)
                .collect(Collectors.toList());
        return new Pair<>(pair.get(0), pair.get(1));
    }

    private boolean shouldCross() {
        return Random.nextDouble() < crossoverProbability;
    }
}
