package pl.kat.ue.tspgenetic.operators.selection;

import pl.kat.ue.tspgenetic.Population;

public interface Selection {

    Population select(Population population);

}