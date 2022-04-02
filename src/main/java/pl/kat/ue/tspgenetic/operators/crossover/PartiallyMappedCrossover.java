package pl.kat.ue.tspgenetic.operators.crossover;

import pl.kat.ue.tspgenetic.Individual;
import pl.kat.ue.tspgenetic.utils.Arrays;
import pl.kat.ue.tspgenetic.utils.Pair;

import java.util.List;

public class PartiallyMappedCrossover extends Crossover {

    private int cutPoint1;
    private int cutPoint2;

    public PartiallyMappedCrossover(double crossoverProbability) {
        super(crossoverProbability);
    }

    public PartiallyMappedCrossover(double crossoverProbability, int noCrossingEpoch) {
        super(crossoverProbability, noCrossingEpoch);
    }

    @Override
    public Pair<Individual> crossPair(Pair<Individual> pair) {
        setCurrentPairData(pair);
        mapGenesBetweenPoints(0, cutPoint1);
        mapGenesBetweenCutPoints();
        mapGenesBetweenPoints(cutPoint2 + 1, genotypeLength);
        return new Pair<>(new Individual(crossed1), new Individual(crossed2));
    }

    @Override
    protected void setCurrentPairData(Pair<Individual> pair) {
        super.setCurrentPairData(pair);
        List<Integer> cutPoints = selectCutPoints(genotypeLength - 1);
        this.cutPoint1 = cutPoints.get(0);
        this.cutPoint2 = cutPoints.get(1);
    }

    private void mapGenesBetweenPoints(int point1, int point2) {
        for (int i = point1; i < point2; i++) {
            crossed1[i] = getGeneAfterSkippingConflicts(i, genotype2, genotype1);
            crossed2[i] = getGeneAfterSkippingConflicts(i, genotype1, genotype2);
        }
    }

    private void mapGenesBetweenCutPoints() {
        for (int i = cutPoint1; i <= cutPoint2; i++) {
            crossed1[i] = genotype1[i];
            crossed2[i] = genotype2[i];
        }
    }

    private int getGeneAfterSkippingConflicts(int gene, int[] genotypeSource, int[] genotype) {
        int geneCandidate;
        int index = gene;
        do {
            geneCandidate = genotypeSource[index];
            index = Arrays.contains(cutPoint1, cutPoint2, genotype, geneCandidate);
        } while (index != -1);
        return geneCandidate;
    }
}