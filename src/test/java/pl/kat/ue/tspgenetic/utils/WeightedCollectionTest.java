package pl.kat.ue.tspgenetic.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.util.LinkedHashMap;
import java.util.Map;

class WeightedCollectionTest {

    static Map<String, Integer> weights = new LinkedHashMap<>();

    @BeforeAll
    static void beforeAll() {
        weights.put("a", 4);
        weights.put("b", 3);
        weights.put("c", 2);
        weights.put("d", 1);
    }

    @ParameterizedTest
    @CsvSource({"0.1,a", "0.44,b", "0.8,c", "0.95,d"})
    void shouldCreateWeightedCollection(double mockDouble, String expected) {
        //GIVEN
        WeightedCollection<String> collection = new WeightedCollection<>();
        weights.forEach((key, value) -> collection.add(value, key));

        try (MockedStatic<Random> mocked = Mockito.mockStatic(Random.class)) {

            //WHEN
            mocked.when(Random::nextDouble).thenReturn(mockDouble);
            String actual = collection.next();

            //THEN
            Assertions.assertEquals(expected, actual);
        }
    }
}