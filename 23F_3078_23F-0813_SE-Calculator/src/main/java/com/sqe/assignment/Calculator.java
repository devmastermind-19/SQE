package com.sqe.assignment;

/**
 * A simple calculator class that performs basic arithmetic operations:
 * add, subtract, multiply, and divide.
 */
public class Calculator {

    /**
     * Adds two integers.
     * @param a the first integer
     * @param b the second integer
     * @return the sum of a and b
     */
    public int add(int a, int b) {
        // Handles standard addition and overflow (as tested)
        return a + b;
    }

    /**
     * Subtracts the second integer from the first.
     * @param a the first integer (minuend)
     * @param b the second integer (subtrahend)
     * @return the difference of a and b
     */
    public int subtract(int a, int b) {
        return a - b;
    }

    /**
     * Multiplies two integers.
     * @param a the first integer
     * @param b the second integer
     * @return the product of a and b
     */
    public int multiply(int a, int b) {
        return a * b;
    }

    /**
     * Divides the first integer by the second, returning a double.
     * @param a the first integer (dividend)
     * @param b the second integer (divisor)
     * @return the quotient as a double
     * @throws IllegalArgumentException if the divisor (b) is zero
     */
    public double divide(int a, int b) {
        if (b == 0) {
            // This matches the test case "testDivide_whenDenominatorIsZero_shouldThrowIllegalArgumentException"
            throw new IllegalArgumentException("Cannot divide by zero");
        }
        // We must cast one operand to double to ensure floating-point
        // division, as required by the test "testDivide_whenStandardDivision_shouldReturnCorrectQuotient" (5 / 2 = 2.5)
        return (double) a / b;
    }
}