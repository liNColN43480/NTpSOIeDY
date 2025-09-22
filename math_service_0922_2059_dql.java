// 代码生成时间: 2025-09-22 20:59:36
 * It also handles exceptions and provides clarity and maintainability through documentation and structure.
 */

package com.example.math;

import io.micronaut.core.annotation.Introspected;
import javax.validation.constraints.NotNull;

@Introspected
public class MathService {

    // Adds two numbers.
    public double add(@NotNull double operand1, @NotNull double operand2) {
        return operand1 + operand2;
    }

    // Subtracts the second number from the first.
    public double subtract(@NotNull double operand1, @NotNull double operand2) {
        return operand1 - operand2;
    }

    // Multiplies two numbers.
    public double multiply(@NotNull double operand1, @NotNull double operand2) {
        return operand1 * operand2;
    }

    // Divides the first number by the second.
    public double divide(@NotNull double operand1, @NotNull double operand2) throws ArithmeticException {
        try {
            return operand1 / operand2;
        } catch (ArithmeticException e) {
            throw new ArithmeticException("Cannot divide by zero.");
        }
    }

    // Mainly for testing the functionality.
    public static void main(String[] args) {
        MathService service = new MathService();

        System.out.println("Addition result: " + service.add(10, 5));
        System.out.println("Subtraction result: " + service.subtract(10, 5));
        System.out.println("Multiplication result: " + service.multiply(10, 5));
        try {
            System.out.println("Division result: " + service.divide(10, 5));
            System.out.println("Division by zero result: " + service.divide(10, 0));
        } catch (ArithmeticException e) {
            System.err.println(e.getMessage());
        }
    }
}