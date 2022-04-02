package pl.kat.ue.tspgenetic.operators.mutation;

import pl.kat.ue.tspgenetic.Individual;

import java.util.List;

public class ReverseSequenceMutation extends Mutation {

    public ReverseSequenceMutation(double mutationProbability) {
        super(mutationProbability);
    }

    @Override
    protected void mutateIndividual(Individual individual) {
        int[] genotype = individual.getGenotype();
        individual.setAssessment(null);
        List<Integer> swapPoints = getSwapPoints(genotype.length);
        reverseGenes(genotype, swapPoints.get(0), swapPoints.get(1));
    }

    private void reverseGenes(int[] genotype, int swapPoint1, int swapPoint2) {
        int range = (swapPoint2 - swapPoint1) / 2;

        for (int i = 0; i <= range; i++) {
            int temp = genotype[swapPoint1 + i];
            genotype[swapPoint1 + i] = genotype[swapPoint2 - i];
            genotype[swapPoint2 - i] = temp;
        }
    }
}