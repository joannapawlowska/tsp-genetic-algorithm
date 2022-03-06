package pl.kat.ue.tspgenetic;

public class GeneticAlgorithm {

    private int populationSize;
    private Population population;
    private int[][] dataSet;

    public GeneticAlgorithm(int[][] dataSet, int populationSize) {
        this.dataSet = dataSet;
        this.populationSize = populationSize;
        this.population = new Population();
        fillPopulationWithIndividuals();
    }

    private void fillPopulationWithIndividuals() {
        for (int i = 0; i < populationSize; i++) {
            Individual individual = new Individual(dataSet.length);
            individual.setAssessment(calculateAssessment(individual));
            population.add(individual);
        }
    }

    protected int calculateAssessment(Individual individual) {
        int assessment = 0;
        int[] genotype = individual.getGenotype();
        int genotypeLength = genotype.length;
        for (int gene = 0; gene < genotypeLength; gene++) {
            assessment += dataSet[genotype[gene]][genotype[(gene + 1) % genotypeLength]];
        }
        return assessment;
    }
}