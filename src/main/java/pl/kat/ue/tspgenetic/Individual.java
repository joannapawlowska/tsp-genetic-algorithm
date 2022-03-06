package pl.kat.ue.tspgenetic;

import lombok.Getter;
import lombok.Setter;

import pl.kat.ue.tspgenetic.utils.Arrays;

@Getter
@Setter
public class Individual {

    private int assessment;
    private int[] genotype;

    public Individual() {
    }

    public Individual(int genotypeSize) {
        this.genotype = new int[genotypeSize];
        randomlyGenerateGenotype(genotypeSize);
    }

    private void randomlyGenerateGenotype(int genotypeSize) {
        for (int i = 0; i < genotypeSize; i++) {
            genotype[i] = i;
        }
        Arrays.shuffle(genotype);
    }
}