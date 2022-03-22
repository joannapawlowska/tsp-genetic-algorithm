package pl.kat.ue.tspgenetic.operators.mutation;

import pl.kat.ue.tspgenetic.Individual;

import java.util.List;

public class TworsMutation extends Mutation {

    public TworsMutation(double mutationProbability) {
        super(mutationProbability);
    }

    @Override
    protected void mutateIndividual(Individual individual) {
        int[] genotype = individual.getGenotype();
        List<Integer> swapPoints = getSwapPoints(genotype.length);
        swapGenes(genotype, swapPoints.get(0), swapPoints.get(1));
    }

    private void swapGenes(int[] genotype, int swapPoint1, int swapPoint2) {
        int temp = genotype[swapPoint1];
        genotype[swapPoint1] = genotype[swapPoint2];
        genotype[swapPoint2] = temp;
    }
}