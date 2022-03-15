package pl.kat.ue.tspgenetic.operators.selection;

import lombok.AllArgsConstructor;
import pl.kat.ue.tspgenetic.Individual;
import pl.kat.ue.tspgenetic.Population;
import pl.kat.ue.tspgenetic.utils.Random;

import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toCollection;

@AllArgsConstructor
public class TournamentSelection implements Selection {

    private int selectivePressure;

    @Override
    public Population select(Population population) {
        return Stream.generate(() -> performTournament(population))
                .limit(population.size())
                .collect(toCollection(Population::new));
    }

    private Individual performTournament(Population population) {
        return IntStream.generate(() -> Random.nextInt(population.size()))
                .limit(selectivePressure)
                .mapToObj(population::get)
                .sorted()
                .findFirst().get()
                .copy();
    }
}