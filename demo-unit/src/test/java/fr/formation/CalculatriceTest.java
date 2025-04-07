package fr.formation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class CalculatriceTest {
    private Calculatrice calculatrice = new Calculatrice();
    
    @Test
    void firstTest() {
        Assertions.assertTrue(true);
    }


    @Test
    void shouldReturnTenWithTowAndEigth() {
        // Given
        int a = 2;
        int b = 8;

        // When
        int result = calculatrice.additionner(a, b);

        // Then
        Assertions.assertEquals(10, result);
    }

    @ParameterizedTest
    @CsvSource({ "4,9,13", "-8,8,0", "4,-10,-6" })
    void shouldReturnCorrectValues(int a, int b, int expected) {
        // Given

        // When
        int result = calculatrice.additionner(a, b);

        // Then
        Assertions.assertEquals(expected, result);
    }
}
