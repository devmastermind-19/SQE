package com.sqe.assignment;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

/**
 * Unit tests for the Calculator class.
 * This file contains tests for all methods, split by responsibility,
 * and includes configurations for ordering and grouping (Task 4).
 */
// TASK 4: Enables test ordering based on the @Order annotation.
@TestMethodOrder(OrderAnnotation.class)
class CalculatorTest {

    private Calculator calculator;

    @BeforeEach
    void setUp() {
        // This method runs before each test, ensuring a fresh Calculator instance.
        calculator = new Calculator();
    }

    // =================================================================
    //  Tests by Wijdan Hyder (add and subtract)
    // =================================================================

    @Test
    @Order(1) // This test will run first.
    @Tag("fast") // Standard, quick-running tests.
    @DisplayName("Add: Two positive numbers")
    void testAdd_whenBothInputsArePositive_shouldReturnCorrectSum() {
        assertEquals(5, calculator.add(2, 3));
    }

    @Test
    @Order(2) // This test will run second.
    @Tag("fast")
    @DisplayName("Add: Two negative numbers")
    void testAdd_whenBothInputsAreNegative_shouldReturnCorrectSum() {
        assertEquals(-10, calculator.add(-7, -3));
    }
    
    @Test
    @Tag("fast")
    @DisplayName("Add: A positive and a negative number")
    void testAdd_whenOneInputIsPositiveAndOneIsNegative_shouldReturnCorrectSum() {
        assertEquals(5, calculator.add(10, -5));
    }

    @Test
    @Tag("fast")
    @DisplayName("Add: Adding zero to a number")
    void testAdd_whenOneInputIsZero_shouldReturnTheNumber() {
        assertEquals(10, calculator.add(10, 0));
    }

    @Test
    @Tag("slow") // Boundary and exception tests are grouped as 'slow'.
    @DisplayName("Add: Adding to Integer.MAX_VALUE (Boundary Test)")
    void testAdd_whenAddingToMaxInteger_shouldOverflow() {
        assertEquals(Integer.MIN_VALUE, calculator.add(Integer.MAX_VALUE, 1));
    }

    @Test
    @Tag("fast")
    @DisplayName("Subtract: A smaller number from a larger one")
    void testSubtract_whenSubtractingSmallerFromLarger_shouldReturnPositive() {
        assertEquals(8, calculator.subtract(10, 2));
    }

    @Test
    @Tag("fast")
    @DisplayName("Subtract: A number from itself")
    void testSubtract_whenSubtractingNumberFromItself_shouldReturnZero() {
        assertEquals(0, calculator.subtract(5, 5));
    }

    @Test
    @Tag("fast")
    @DisplayName("Subtract: A negative number from a positive number")
    void testSubtract_whenSubtractingNegativeFromPositive_shouldBeLikeAddition() {
        assertEquals(15, calculator.subtract(10, -5));
    }

    // =================================================================
    //  Tests by Ali Naqi (multiply and divide)
    // =================================================================

    @Test
    @Tag("fast")
    @DisplayName("Multiply: Two positive numbers")
    void testMultiply_whenBothInputsArePositive_shouldReturnCorrectProduct() {
        assertEquals(20, calculator.multiply(4, 5));
    }

    @Test
    @Tag("fast")
    @DisplayName("Multiply: A positive and a negative number")
    void testMultiply_whenOneInputIsNegative_shouldReturnNegativeProduct() {
        assertEquals(-15, calculator.multiply(3, -5));
    }

    @Test
    @Tag("fast")
    @DisplayName("Multiply: Two negative numbers")
    void testMultiply_whenBothInputsAreNegative_shouldReturnPositiveProduct() {
        assertEquals(25, calculator.multiply(-5, -5));
    }

    @Test
    @Tag("fast")
    @DisplayName("Multiply: Multiplying by zero")
    void testMultiply_whenOneInputIsZero_shouldReturnZero() {
        assertEquals(0, calculator.multiply(100, 0));
    }

    @Test
    @Tag("slow")
    @DisplayName("Multiply: Multiplying by one (Boundary Test)")
    void testMultiply_whenMultiplyingByOne_shouldReturnTheNumber() {
        assertEquals(42, calculator.multiply(42, 1));
    }

    @Test
    @Tag("fast")
    @DisplayName("Divide: Standard division of two positive numbers")
    void testDivide_whenStandardDivision_shouldReturnCorrectQuotient() {
        assertEquals(2.5, calculator.divide(5, 2));
    }

    @Test
    @Tag("fast")
    @DisplayName("Divide: Dividing zero by a number")
    void testDivide_whenNumeratorIsZero_shouldReturnZero() {
        assertEquals(0.0, calculator.divide(0, 10));
    }

    @Test
    @Tag("slow")
    @DisplayName("Divide: Dividing a number by itself (Boundary Test)")
    void testDivide_whenDividingByItself_shouldReturnOne() {
        assertEquals(1.0, calculator.divide(7, 7));
    }

    @Test
    @Tag("slow")
    @DisplayName("Divide: Division by zero (CRITICAL BOUNDARY TEST)")
    void testDivide_whenDenominatorIsZero_shouldThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> {
            calculator.divide(10, 0);
        });
    }

    // =================================================================
    //  Task 3: Data-Driven Tests
    // =================================================================

    @ParameterizedTest(name = "Add (from CSV): {0} + {1} = {2}")
    @Tag("data-driven") // Parameterized tests form their own group.
    @CsvFileSource(resources = "/add_test_data.csv", numLinesToSkip = 1)
    @DisplayName("Add: Data-driven tests from CSV file")
    void testAdd_withDataFromCsv(int a, int b, int expected) {
        // This test is contributed by Wijdan Hyder
        assertEquals(expected, calculator.add(a, b));
    }

    @ParameterizedTest(name = "Subtract (from CSV): {0} - {1} = {2}")
    @Tag("data-driven")
    @CsvFileSource(resources = "/subtract_test_data.csv", numLinesToSkip = 1)
    @DisplayName("Subtract: Data-driven tests from CSV file")
    void testSubtract_withDataFromCsv(int a, int b, int expected) {
        // This test is contributed by Ali Naqi
        assertEquals(expected, calculator.subtract(a, b));
    }
}

