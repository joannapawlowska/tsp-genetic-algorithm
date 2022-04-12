# TSP Genetic Algorithm

>Library for solving TSP problem with genetic algorithm.

### Technologies
- Java

### Dataset format
In order for data to be loaded correctly the dataset file should be in the following form:
```
4
0
1 0
4 2 0
7 2 5 0
```
Where the first line indicates the amount of cities and each subsequent line contains the distance between cities separated by a space.

### Sample usage
```
TspGeneticAlgorithm algorithm = TspGeneticAlgorithm.builder()
            .dataSet(DataSetReader.loadMatrix("dataset.txt"))
            .populationSize(200)
            .epochs(1000)
            .selection(new RouletteWheelSelection()).performUpToEpoch(1000)
            .crossover(new CycleCrossover(0.75)).performUpToEpoch(1000)
            .mutation(new TworsMutation(0.5)).performUpToEpoch(1000)
            .succession(new PartialReplacementSuccession(0.7))
            .build();
                
TspGeneticAlgorithm.Results results = algorithm.perform();
```