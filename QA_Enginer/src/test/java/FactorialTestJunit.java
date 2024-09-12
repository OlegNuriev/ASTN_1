import Lesson_12_junit_5.Factorial;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FactorialTestJunit {

        @Test
        void testCalculateFactorialPositiveNumber() {
            assertEquals(120, Factorial.calculateFactorial(5));
        }

        @Test
        void testCalculateFactorialZero() {
            assertEquals(1, Factorial.calculateFactorial(0));
        }

        @Test
        void testCalculateFactorialOne() {
            assertEquals(1, Factorial.calculateFactorial(1));
        }

        @Test
        void testCalculateFactorialNegativeNumber() {
            Exception exception = assertThrows(IllegalArgumentException.class, () -> Factorial.calculateFactorial(-5));
            assertEquals("Факториал отрицательного числа не существует", exception.getMessage());
        }
}
