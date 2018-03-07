/**
 * The factorial class calculates the factorial value of the integer value
 * that has been passed into main.
 */
public class Factorial {

    /**
     * The main method converts the string of the number into an integer and
     * then calls the factorial methods (using recursive and iterative
     * calculations) and prints them correspondingly.
     * @param args receives a string from shell to calculate the factorial of.
     */
    public static void main(String[] args) {
        long userNumber = Long.parseLong(args[0]);
        if (userNumber < 0) {
            System.out.print("Cannot calculate factorial for a negative "
                    + "number. Exiting...");
            return;
        }
        System.out.println("recursive: " + factorialRecursive(userNumber));
        System.out.println("iterative: " + factorialIter(userNumber));

    }

    /**
     * The method calculates the factorial value of the passed integer using
     * a recursive method.
     * @param n the integer we'll calculate the factorial value of.
     * @return returns the factorial value that was calculated.
     */
    public static long factorialRecursive(long n) {
        if (n == 0 || n == 1) {
            return 1;
        } else {
            return factorialRecursive(n - 1) * n;
        }
    }

    /**
     * The method calculates the factorial value of the passed integer using
     * an iterative method.
     * @param n the integer we'll calculate the factorial value of.
     * @return returns the factorial value that was calculated.
     */
    public static long factorialIter(long n) {
        long factSum = 1;
        if (n == 0 || n == 1) {
            return 1;
        } else {
            for (long i = 2; i <= n; i++) {
                factSum *= i;
            }
            return factSum;
        }
    }
}