package ch.tbz.rechner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CalculatorTest {

    private Calculator calculator;

    // Wird vor jedem einzelnen Test neu ausgeführt, damit die Tests unabhängig sind
    @BeforeEach
    void setUp() {
        calculator = new Calculator();
    }

    @Test
    @DisplayName("Addition zweier positiver Zahlen")
    void testAdd() {
        assertEquals(8.0, calculator.add(5, 3));
    }

    @Test
    @DisplayName("Addition mit einer negativen Zahl")
    void testAddNegative() {
        assertEquals(-2.0, calculator.add(3, -5));
    }

    @Test
    @DisplayName("Subtraktion zweier Zahlen")
    void testSubtract() {
        assertEquals(2.0, calculator.subtract(5, 3));
    }

    @Test
    @DisplayName("Multiplikation zweier Zahlen")
    void testMultiply() {
        assertEquals(15.0, calculator.multiply(5, 3));
    }

    @Test
    @DisplayName("Multiplikation mit null ergibt null")
    void testMultiplyByZero() {
        assertEquals(0.0, calculator.multiply(5, 0));
    }

    @Test
    @DisplayName("Division zweier Zahlen")
    void testDivide() {
        assertEquals(2.0, calculator.divide(6, 3));
    }

    @Test
    @DisplayName("Division durch null wirft eine Exception")
    void testDivideByZero() {
        assertThrows(IllegalArgumentException.class, () -> calculator.divide(6, 0));
    }
}