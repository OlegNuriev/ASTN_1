package Lesson_12_testng;

public class Main {
    public static void main(String[] args) {
        int number = 2;
        Factorial factorial = new Factorial();
        long result = factorial.calculateFactorial(number);
        System.out.println("Факториал числа " + number + " равен " + result);
    }
}