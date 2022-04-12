package pl.kat.ue.tspgenetic.operators.selection;

import lombok.AllArgsConstructor;
import pl.kat.ue.tspgenetic.Individual;
import pl.kat.ue.tspgenetic.Population;
import pl.kat.ue.tspgenetic.utils.Random;

import java.util.stream.IntStream;

@AllArgsConstructor
public class TournamentSelection extends Selection {

    private int selectivePressure;

    @Override
    public Population select(Population population) {
        Population selected = new Population();
        while (selected.size() < population.size()) {
            selected.add(performTournament(population));
        }
        return selected;
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