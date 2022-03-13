package pl.kat.ue.tspgenetic;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import pl.kat.ue.tspgenetic.utils.Arrays;

@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Individual implements Comparable<Individual> {

    private int assessment;
    private int[] genotype;

    public Individual() {
    }

    public Individual(int genotypeSize) {
        this.genotype = new int[genotypeSize];
        randomlyGenerateGenotype(genotypeSize);
    }

    public Individual(int[] genotype) {
        this.genotype = genotype;
    }

    private void randomlyGenerateGenotype(int genotypeSize) {
        for (int i = 0; i < genotypeSize; i++) {
            genotype[i] = i;
        }
        Arrays.shuffle(genotype);
    }

    public Individual copy() {
        return new Individual(assessment, genotype.clone());
    }

    @Override
    public int compareTo(Individual individual) {
        return Integer.compare(getAssessment(), individual.getAssessment());
    }
}