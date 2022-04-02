package pl.kat.ue.tspgenetic;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import pl.kat.ue.tspgenetic.utils.Arrays;

import java.util.Objects;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
public class Individual implements Comparable<Individual> {

    private Integer assessment;
    private int[] genotype;

    public Individual() {
    }

    public Individual(int genotypeSize) {
        this.genotype = new int[genotypeSize];
        randomlyGenerateGenotype(genotypeSize);
    }

    public Individual(int[] genotype) {
        this.genotype = genotype;
        this.assessment = null;
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

    public boolean isNotAssessed(){
        return Objects.isNull(assessment);
    }

    @Override
    public int compareTo(Individual individual) {
        return Integer.compare(getAssessment(), individual.getAssessment());
    }

    @Override
    public String toString() {
        return java.util.Arrays.stream(genotype)
                .mapToObj(String::valueOf)
                .collect(Collectors.joining("-")) + " " + assessment;
    }
}