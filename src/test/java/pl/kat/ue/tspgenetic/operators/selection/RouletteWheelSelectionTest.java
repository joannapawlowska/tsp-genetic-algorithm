package pl.kat.ue.tspgenetic.operators.selection;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.MockedConstruction;
import org.mockito.Mockito;
import pl.kat.ue.tspgenetic.Individual;
import pl.kat.ue.tspgenetic.Population;
import pl.kat.ue.tspgenetic.utils.WeightedCollection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class RouletteWheelSelectionTest {

    @Test
    void shouldSelectWithRouletteWheel() {
        //GIVEN
        Individual i0 = new Individual(0, new int[3]);
        Individual i1 = new Individual(1, new int[3]);
        Individual i2 = new Individual(2, new int[3]);
        Individual i3 = new Individual(3, new int[3]);
        Population population = new Population(i0, i1, i2, i3);
        ArgumentCaptor<Double> i3WeightCaptor = ArgumentCaptor.forClass(Double.class);
        ArgumentCaptor<Double> i2WeightCaptor = ArgumentCaptor.forClass(Double.class);

        try (MockedConstruction<WeightedCollection> mocked = Mockito.mockConstruction(WeightedCollection.class,
                (mock, context) -> {
                    when(mock.next()).thenReturn(i3, i2);
                    doNothing().when(mock).add(i3WeightCaptor.capture(), eq(i3));
                    doNothing().when(mock).add(i2WeightCaptor.capture(), eq(i2));
                })) {

            //WHEN
            Population actual = new RouletteWheelSelection().select(population);

            //THEN
            assertEquals(1, i3WeightCaptor.getValue());
            assertEquals(2, i2WeightCaptor.getValue());
            assertNotEquals(population, actual);
            assertEquals(2, actual.size());
            assertEquals(i3.getAssessment(), actual.get(0).getAssessment());
            assertEquals(i2.getAssessment(), actual.get(1).getAssessment());
        }
    }
}