package Lesson_12_junit_5;

public class Factorial {
    public static int calculateFactorial(int number) {
        if (number < 0) {
            throw new IllegalArgumentException("Факториал отрицательного числа не существует");
        }

        if (number == 0) {
            return 1;
        }

        int result = 1;
        for (int i = 1; i <= number; i++) {
            result *= i;
        }

        return result;
    }
}
