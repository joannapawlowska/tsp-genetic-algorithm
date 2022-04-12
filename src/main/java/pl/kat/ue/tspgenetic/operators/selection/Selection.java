package pl.kat.ue.tspgenetic.operators.selection;

import pl.kat.ue.tspgenetic.Population;
import pl.kat.ue.tspgenetic.operators.GeneticOperator;

public abstract class Selection implements GeneticOperator {

    public abstract Population select(Population population);

}