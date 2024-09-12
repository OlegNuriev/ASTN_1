import Lesson_12_testng.Factorial;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertThrows;

public class FactorialTestNg {
    @Test
    public void testCalculateFactorialPositiveNumber() {
        assertEquals(Factorial.calculateFactorial(5), 120);
    }

    @Test
    public void testCalculateFactorialNegative() {
        assertEquals(Factorial.calculateFactorial(5), 12);
    }

    @Test
    public void testCalculateFactorialZero() {
        assertEquals(Factorial.calculateFactorial(0), 1);
    }

    @Test
    public void testCalculateFactorialOne() {
        assertEquals(Factorial.calculateFactorial(1), 1);
    }

    @Test
    public void testCalculateFactorialNegativeNumber() {
        assertThrows(IllegalArgumentException.class, () -> Factorial.calculateFactorial(-5));
    }
}
