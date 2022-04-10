package pl.kat.ue.tspgenetic.operators.crossover;

import pl.kat.ue.tspgenetic.Individual;
import pl.kat.ue.tspgenetic.utils.Arrays;
import pl.kat.ue.tspgenetic.utils.Pair;

import java.util.ArrayList;
import java.util.List;

public class CycleCrossover extends Crossover {

    private List<Integer> cycle;

    public CycleCrossover(double crossoverProbability) {
        super(crossoverProbability);
    }

    public CycleCrossover(double crossoverProbability, int noCrossingEpoch) {
        super(crossoverProbability, noCrossingEpoch);
    }

    @Override
    public Pair<Individual> crossPair(Pair<Individual> pair) {
        setCurrentPairData(pair);
        findCycle();
        mapGenes();
        return new Pair<>(
                new Individual(crossed1),
                new Individual(crossed2)
        );
    }

    @Override
    protected void setCurrentPairData(Pair<Individual> pair) {
        super.setCurrentPairData(pair);
        this.cycle = new ArrayList<>();
    }

    private void mapGenes() {
        for (int i = 0; i < genotypeLength; i++) {
            if (cycle.contains(i)) {
                crossed1[i] = genotype1[i];
                crossed2[i] = genotype2[i];
            } else {
                crossed1[i] = genotype2[i];
                crossed2[i] = genotype1[i];
            }
        }
    }

    private void findCycle() {
        int index = 0;
        int gene;
        do {
            cycle.add(index);
            gene = genotype2[index];
            index = findIndexOfGeneInFirstGenotype(gene);
        } while (gene != genotype1[cycle.get(0)]);
    }

    private int findIndexOfGeneInFirstGenotype(int geneCandidate) {
        return Arrays.contains(0, genotypeLength - 1, genotype1, geneCandidate);
    }
}
