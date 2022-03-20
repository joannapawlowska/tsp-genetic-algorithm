package pl.kat.ue.tspgenetic.operators.crossover;

import pl.kat.ue.tspgenetic.Individual;
import pl.kat.ue.tspgenetic.utils.Arrays;
import pl.kat.ue.tspgenetic.utils.Pair;

import java.util.List;

public class OrderCrossover extends Crossover {

    private int cutPoint1;
    private int cutPoint2;

    public OrderCrossover(double crossoverProbability) {
        super(crossoverProbability);
    }

    @Override
    public Pair<Individual> crossPair(Pair<Individual> pair) {
        setCurrentPairData(pair);
        mapGenesBetweenCutPoints();
        mapGenesOutsideCutPointsInterval();
        return new Pair<>(
                new Individual(crossed1),
                new Individual(crossed2)
        );
    }

    @Override
    protected void setCurrentPairData(Pair<Individual> pair) {
        super.setCurrentPairData(pair);
        List<Integer> cutPoints = selectCutPoints(genotypeLength - 1);
        this.cutPoint1 = cutPoints.get(0);
        this.cutPoint2 = cutPoints.get(1);
    }

    private void mapGenesBetweenCutPoints() {
        for (int i = cutPoint1; i <= cutPoint2; i++) {
            crossed1[i] = genotype1[i];
            crossed2[i] = genotype2[i];
        }
    }

    private void mapGenesOutsideCutPointsInterval() {
        int l = 0;
        int k = 0;

        for (int i = cutPoint2 + 1; i < genotypeLength + cutPoint2 + 1; i++) {
            int index = i % genotypeLength;

            if (!isGeneAlreadyInGenotype(genotype2[index], genotype1)) {
                l = getIndexConsideringOffset(l);
                crossed1[l++] = genotype2[index];
            }
            if (!isGeneAlreadyInGenotype(genotype1[index], genotype2)) {
                k = getIndexConsideringOffset(k);
                crossed2[k++] = genotype1[index];
            }
        }
    }

    private boolean isGeneAlreadyInGenotype(int geneCandidate, int[] genotype) {
        return Arrays.contains(cutPoint1, cutPoint2, genotype, geneCandidate) != -1;
    }

    private int getIndexConsideringOffset(int i) {
        if (i == cutPoint1) {
            i += cutPoint2 - cutPoint1 + 1;
        }
        return i;
    }
}
